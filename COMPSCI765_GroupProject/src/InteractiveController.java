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
	private List<String> has_symptom;
	private List<String> has_not_symptom;
	private String diagnose;

	public InteractiveController()
	{
		this.state="init";
		this.age = 0;
		this.gender = "";
		this.diagnose = "";
		this.has_symptom = new ArrayList<String>();
		this.has_not_symptom = new ArrayList<String>();
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
	public List<String> getSymptom() {
		return symptom;
	}
	public void setSymptom(List<String> symptom) {
		this.symptom = symptom;
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
			clips_interface();
			return "What symptom do you have?";
		}
		if(this.state.equals("question"))
		{
			//as state is question, expecting a response to the most recent question asked
			return(update_evaluate_model(response));
		}
		return this.state;
	}
	public String update_evaluate_model(String response_update)
	{
		// This method updates the existing model with the response
		// evaluates whether the model is complete
		// interfaces with clips to obtain facts
		List<String> disease = new ArrayList<String>();
		
		boolean found = false;
		for(String str : this.knowledge)
		{
			if(str.contains(response_update))
			{
				if(!(str.substring(0, str.indexOf(" "))).equals(response_update))
				{
					disease.add(str.substring(0, str.indexOf(" ")));	
					found = true;
				}
			}
		}
		
		if(found)
		{
			found = false;
			//find the most common symptoms in the list of diseases found
			//use this to filter list
			//iterate through model to eliminate diseases
			//ask question to refine model
			
			//if found, its a valid symptom hence its added to user model
			(this.getSymptom()).add(response_update);
			//symptom added to model
			int count = 0;
			List<String> current_symptom = new ArrayList<String>();
			
			for(String st : this.knowledge)
			{
				for(String each_st : this.getSymptom())
				{
					if(st.contains(each_st))
					{
						if(!(current_symptom.contains(each_st)))
						{
							current_symptom.add(each_st);
						}
						count++;
					}
				}
				String [] total = (st.substring(st.indexOf(" ")+1, st.length())).split(" ");

				if(total.length==count)
				{
					return "Match Found ! " + st;
				}
				count = 0;
			}

			
			//evaluate model to see if it is completed

			//ask question to refine model
			String send_symptoms="";
			for(String str1 : current_symptom)
			{
				send_symptoms+=str1+" ";
			}
			return "Symptom found, we currently have : " + send_symptoms;
			
		}
		else
		{
			return "Unfortunately the symptom was not found, please try again . . .";
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

