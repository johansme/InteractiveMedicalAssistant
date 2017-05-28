import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.PrimitiveValue;

public class InteractiveController {
	
	public String interactive_action(String response)
	{

		return "";
	
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
