import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import net.sf.clipsrules.jni.*;

public class InteractiveController {
	//state values are : init(on system startup), question(when asking question), advice(when advising user of action)
	
	private String state;
	private List<String> knowledge;
	
	//Model of user
	
	private String age;
	private String gender;
	private String symptom;
	private String diagnose;
	
	public List<String> getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(List<String> knowledge) {
		this.knowledge = knowledge;
	}
	
	public InteractiveController()
	{
		this.state="init";
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
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
			return "What is the Initial Question to be Asked?";
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
		
		if(true)
		{
			
		}
		
		return response_update;
	}
	public String clips_interface()
	{
		
		//************************TESTBLOCK***************
		// CLIPSJNI.dll must be in system32 or OS equivalent
		Environment clips = new Environment();
		//clips.reset();

		
		clips.loadFromResource("/kbs/kb.clp");

		clips.reset();

		clips.run();
		
		//clips.assertString("(myTemp (one asd) (second jhg))");
	    //clips.assertString("(myTemp (one bvc) (second kjh))");
		
		String evalStr = "(find-all-facts ((?f state-list)) TRUE)";
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
		
		
		

		

				
		return " ";
		

		//clips.reset();
		//clips.run();
		//clips.destroy();

		//clips.eval();

		//**********************TESTBLOCK*************************/		
	}
}

