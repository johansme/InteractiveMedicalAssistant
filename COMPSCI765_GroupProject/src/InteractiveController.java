import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import net.sf.clipsrules.jni.*;

public class InteractiveController {
	//state values are : init(on system startup), question(when asking question), advice(when advising user of action)
	
	private String state;
	private List<String> knowledge;
	
	//Model of user
	
	private int age;
	private String gender;
	private String diagnose;
	private List<String> has_symptom;
	private List<String> has_not_symptom;
	private String symptom_add_remove;
	
	public InteractiveController()
	{
		this.state="init";
		this.age = 0;
		this.gender = "";
		this.diagnose = "";
		this.has_symptom = new ArrayList<String>();
		this.has_not_symptom = new ArrayList<String>();
		this.symptom_add_remove = "";
	}
	public String getSymptom_add_remove() {
		return symptom_add_remove;
	}
	public void setSymptom_add_remove(String symptom_add_remove) {
		this.symptom_add_remove = symptom_add_remove;
	}
	public List<String> getHas_symptom() {
		return has_symptom;
	}
	public void setHas_symptom(List<String> has_symptom) {
		this.has_symptom = has_symptom;
	}
	public List<String> getHas_not_symptom() {
		return has_not_symptom;
	}
	public void setHas_not_symptom(List<String> has_not_symptom) {
		this.has_not_symptom = has_not_symptom;
	}
	public List<String> getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(List<String> knowledge) {
		this.knowledge = knowledge;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String interactive_action(String response)
	{
		if(this.state.equals("init"))
		{
			//state changed to asked question mode
			this.state = "yes";
			//response value is blank from UI
			//clips_interface();
			return "What symptom do you have?";
		}
		
		//*************************************//
		if(this.state.equals("yes"))
		{
			//!!!!! Check symptom from UI if valid - then add
			this.has_symptom.add(this.symptom_add_remove);
			return(evaluate_model(response));
		}
		if(this.state.equals("no"))
		{
			//!!!!! Check symptom from UI if valid - then add
			this.has_not_symptom.add(this.symptom_add_remove);
			return(evaluate_model(response));
		}		
		//*************************************//
		
		return this.state;
	}
	public String evaluate_model(String response_update)
	{
		List<String> disease = new ArrayList<String>();
		//****************ADD*******************// only if not already added
		for(String str : this.knowledge)
		{
			String [] ksymptoms = (str.substring(str.indexOf(":"),str.length())).split(":");
			for(String has_str : this.has_symptom)
			{
				for(String satomic : ksymptoms)
				{
					if(satomic.equals(has_str)&&!(disease.contains(str.substring(0, str.indexOf(":")))))
					{
						disease.add(str.substring(0, str.indexOf(":")));
					}					
				}
			}
		}
		//****************ADD*******************//
		//****************REMOVE****************//
		for(String str : this.knowledge)
		{
			String [] ksymptoms = (str.substring(str.indexOf(":"),str.length())).split(":");
			for(String has_not_str : this.has_not_symptom)
			{
				for(String satomic : ksymptoms)
				{
					if(satomic.equals(has_not_str))
					{
						while(disease.remove(str.substring(0, str.indexOf(":")))){};
					}
				}
			}
		}
		//****************REMOVE****************//

		//****************CLEAR*****************//
		
		int count = 0;
		List<String> to_remove = new ArrayList<String>();
		
		for(String dsname : disease)
		{
			for(String kbs : this.knowledge)
			{
				if(kbs.substring(0, kbs.indexOf(":")).equals(dsname))
				{

					for(String hs_sym : this.has_symptom)
					{
						//if has all then retain else remove
						String [] s = (kbs.substring(kbs.indexOf(":"),kbs.length())).split(":");
						for(String s1 : s)
						{
							if(s1.equals(hs_sym))
							{
								count++;
							}
						}
					}
				}
			}
			if(!(count==this.has_symptom.size()))
			{
				to_remove.add(dsname);
			}
			count = 0;
		}
		for(String remove : to_remove)
		{
			while(disease.remove(remove)){};
		}
		//****************CLEAR*****************//

		//****************CHECK****************//

		if(disease.size()==1) // match on single
		{
			String disease_line="";
			for(String line : this.knowledge)
			{
				if(line.substring(0, line.indexOf(":")).equals(disease.get(0)))
				{
					disease_line = line;
				}
			}
			
			String disease_name="";
			disease_name = disease_line.substring(0,disease_line.indexOf(":"));
			String symptom_line = disease_line.substring(disease_line.indexOf(":"),disease_line.length());
			
			int symptom_hits = 0;
			for(String has_symptom : this.has_symptom)
			{
				if(symptom_line.contains(has_symptom))
				{
					symptom_hits++;
				}
			}
			if(this.has_symptom.size()==symptom_hits)
			{
				this.state = "advise"; //found match
				return "Possible : "+disease_name;
			}
			symptom_hits=0;
		}
		//****************CHECK****************//
		
		// No match above so proceeding with asking more information
		
		List<String> all_symptoms = new ArrayList<String>();
		
		disease.size();// has all the possible disease names!
		//get all disease lines
		List<String> disease_lines = new ArrayList<String>();

		for(String name : disease)
		{
			for(String kbs : this.knowledge)
			{
				if(kbs.contains(name))
				{
					disease_lines.add(kbs);
				}
			}
		}

		//*** get all symptoms in all disease lines
		//*** remove has_symtoms and has_not_symptoms
		//*** creates unknown symptom list
		//*** information gain on unknown symptom list
		for(String disease_line : disease_lines)
		{
			String symp = disease_line.substring(disease_line.indexOf(":")+1,disease_line.length());
			String[]symptoms = symp.split(":");
			
			boolean existing = false;
			//** if symptom is not in has_symptom and has_not_symptom (new unknown symptoms)
			for(String symp_unit : symptoms)
			{
				for(String hs : this.has_symptom)
				{
					if(symp_unit.equals(hs))
					{
						existing = true;
					}
				}
				for(String hns : this.has_not_symptom)
				{
					if(symp_unit.equals(hns))
					{
						existing = true;
					}
				}
				if(!existing)
				{
					all_symptoms.add(symp_unit);
				}
				existing = false;
			}
			
			all_symptoms.size(); //now has all uniq symptoms
		}
		
		//information gain here
		//then yes and no
		int max = 0;
		String max_symptom = "";

		for(String each_symp : all_symptoms)
		{
			int freq= Collections.frequency(all_symptoms, each_symp);
			if(freq >= max)
			{
				max=freq;
				max_symptom = each_symp;
			}
		}
		
		if(max!=0)
		{
			this.state = "yes/no";
			this.symptom_add_remove = max_symptom;
			return "Are you experiencing the following symptom: " + max_symptom + "? Please use yes or no";
		}
		else
		{
			return "Symptom Combination Does Not Exist in Knowledge Base";
		}
	}
	public String clips_interface()
	{
		
		//CLIPSJNI.dll must be in system32 or OS equivalent
		//Environment clips = new Environment();
		//clips.loadFromResource("/kbs/kb.clp");
		//clips.reset();
		//clips.run();
		//clips.assertString("(myTemp (one asd) (second jhg))");
	    //clips.assertString("(myTemp (one bvc) (second kjh))");
		//String evalStr = "(find-all-facts ((?f state-list)) TRUE)";
		//String currentID = clips.eval(evalStr).get(0).getFactSlot("current").toString();
	    //clips.run();
	    //clips.assertString("(animal-is duck)");
		//clips.assertString("(facts)");
		//MultifieldValue mv = (MultifieldValue) clips.eval("()");
		//PrimitiveValue pvl = clips.eval("(facts)");
		//System.out.println(pvl.toString()+"-");
		//String evalStr = "(find-fact ((?f myTemp)) (eq ?p:one bvc))";
        //MultifieldValue mv = (MultifieldValue) clips.eval("(run)");
		//clips.eval("(facts)");
		//clips.eval("(list-deffacts)");
		//clips.eval("(find-fact)");
		//clips.eval("(run)");
		//clips.eval("(assert (symptom-is fever))");
		//clips.assertString("(symptom-is fever)");
		//clips.eval("(run)");
        //clips.reset();
		//clips.run();
		//clips.destroy();
        //clips.eval();
		return " ";
	}
}

