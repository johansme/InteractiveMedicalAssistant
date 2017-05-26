import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import net.sf.clipsrules.jni.*;


public class InteractiveMedicalAssistant {

	private JFrame frmCompsciInteractiveMedical;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InteractiveMedicalAssistant window = new InteractiveMedicalAssistant();
					window.frmCompsciInteractiveMedical.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InteractiveMedicalAssistant() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCompsciInteractiveMedical = new JFrame();
		frmCompsciInteractiveMedical.setTitle("COMPSCI765: Interactive Medical Assistant");
		frmCompsciInteractiveMedical.setBounds(100, 100, 450, 300);
		frmCompsciInteractiveMedical.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCompsciInteractiveMedical.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 261);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		frmCompsciInteractiveMedical.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(frmCompsciInteractiveMedical, "Run!");
				//************************TESTBLOCK*************************//
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
			
				JOptionPane.showMessageDialog(frmCompsciInteractiveMedical,pvl);
				clips.eval("(list-defrules)");
				
				clips.run();
				
				//clips.reset();
				//clips.run();
				//clips.destroy();
				
				//clips.eval();
				
				
				//************************TESTBLOCK*************************//
			}
		});
		btnNewButton.setBounds(175, 192, 89, 23);
		frmCompsciInteractiveMedical.getContentPane().add(btnNewButton);
	}
}
