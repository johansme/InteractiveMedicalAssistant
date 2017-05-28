public class InteractiveController {
	private String state;
	
	public InteractiveController()
	{
		this.state="init";
	}
	public void setstate(String State)
	{
		this.state = State;
	}
	public String getstate()
	{
		return this.state;
	}
	
	public String interactive_action(String response)
	{
		if(this.state.equals("init"))
		{
			return "What is the Initial Question to be Asked?";
		}
		return this.state;
	}
}

/*JOptionPane.showMessageDialog(frmCompsciInteractiveMedical, "Run!");
//************************TESTBLOCK***************
// CLIPSJNI.dll must be in system32 or OS equivalent
Environment clips = new Environment();

//clips.loadFromResource("/jni/kbs/bcengine.cpl");

clips.loadFromResource("/kbs/test.clp");
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

clips.run();

//clips.reset();
//clips.run();
//clips.destroy();

//clips.eval();

**********************TESTBLOCK*************************/
