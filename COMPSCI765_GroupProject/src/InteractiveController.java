import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
			this.state = "question";
			//response value is blank from UI
			//clips_interface();
			return "What symptom do you have?";
		}
		if(this.state.equals("question"))
		{
			//as state is question
			return(evaluate_model(response));
		}
		
		//*************************************//
		if(this.state.equals("yes"))
		{
			this.has_symptom.add(this.symptom_add_remove);
			return(evaluate_model(response));
		}
		if(this.state.equals("no"))
		{
			this.has_not_symptom.add(this.symptom_add_remove);
			return(evaluate_model(response));
		}		
		//*************************************//
		
		return this.state;
	}
	public String evaluate_model(String response_update)
	{
		List<String> disease = new ArrayList<String>();
		boolean found = false;
		//****************ADD*******************//
		for(String str : this.knowledge)
		{
			for(String has_str : this.has_symptom)
			{
				if(str.contains(has_str))
				{
					disease.add(str.substring(0, str.indexOf(":")));
				}
			}
		}
		//****************ADD*******************//

		//****************REMOVE****************//
		for(String str : this.knowledge)
		{
			for(String has_not_str : this.has_not_symptom)
			{
				if(str.contains(has_not_str))
				{
					while(disease.remove(str.substring(0, str.indexOf(":")))){};
				}
			}
		}
		//****************REMOVE****************//

		//****************CHECK****************//
		if(disease.size()==1)
		{
			String disease_line="";
			String disease_name="";
			disease_line = disease.get(0);
			disease_name = disease_line.substring(0,disease_line.indexOf(":"));
			String symptom_line = disease_line.substring(disease_line.indexOf(":"),disease_line.length()-1);
			
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
				return disease_name;
			}
			symptom_hits=0;
		}
		//****************CHECK****************//
		
		
		// No match above so proceeding with asking more information
		
		List<String> all_symptoms = new ArrayList<String>();

		
		disease.size();// has all the possible disease lines

		//*** get all symptoms in all disease lines
		//*** remove has_symtoms and has_not_symptoms
		//*** creates unknown symptom list
		//*** information gain on unknown symptom list
		for(String disease_line : disease)
		{
			String symp = disease_line.substring(0,disease_line.indexOf(":"));
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
			}
			existing = false;
			
			all_symptoms.size(); //now has all uniq symptoms
			
			//information gain here
		}
		
		return "";
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

