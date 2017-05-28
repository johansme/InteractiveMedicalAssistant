import net.sf.clipsrules.jni.*;

public class InteractiveController {
	//state values are : init(on system startup), question(when asking question), advice(when advising user of action)
	
	private String state;
	
	//Model of user
	
	private String age;
	private String gender;
	private String symptom;
	private String diagnose;
	
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
		clips.reset();

		//clips.loadFromResource("/jni/kbs/bcengine.cpl");

		clips.loadFromResource("/kbs/kb.clp");
		//clips.reset();

		try{
			
		    //InputStream input = getClass().getResourceAsStream("/kbs/animal.clp");
		    //JOptionPane.showMessageDialog(frmCompsciInteractiveMedical,input.available());

		}
		catch(Exception e)
		{
			//JOptionPane.showMessageDialog(frmCompsciInteractiveMedical, e.toString());
		}

		PrimitiveValue pvl = clips.eval("(list-deffacts)");		
		clips.eval("(list-defrules)");
		clips.eval("(list-deffacts)");
		clips.eval("(assert(disease(symptom1 cough)(symptom2 sneeze)(symptom3 fever)))");
		clips.eval("(run)");
		
		//clips.run();
		
		return " ";

		//clips.reset();
		//clips.run();
		//clips.destroy();

		//clips.eval();

		//**********************TESTBLOCK*************************/		
	}
}

