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
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;


public class InteractiveMedicalAssistant {

	private JFrame frmCompsciInteractiveMedical;
	private JTextField textField;
	private InteractiveController IC = new InteractiveController();
	TextArea textArea = new TextArea();
	
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
		frmCompsciInteractiveMedical.setBounds(100, 100, 765, 365);
		frmCompsciInteractiveMedical.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCompsciInteractiveMedical.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 261);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		frmCompsciInteractiveMedical.getContentPane().add(panel);
		
		// IC state is init
		textArea.setText(IC.interactive_action(""));
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			if(IC.getstate().equals("question"))
			{
				textArea.setText(IC.interactive_action(textField.getText()));
			}
			else if(true)
			{
				textArea.setText(IC.interactive_action(textField.getText()));
				btnNewButton.setEnabled(false);
			}
		}
		});
		btnNewButton.setBounds(309, 239, 129, 44);
		frmCompsciInteractiveMedical.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(650, 292, 89, 23);
		frmCompsciInteractiveMedical.getContentPane().add(btnNewButton_1);
		
		
		textArea.setEditable(false);
		textArea.setBounds(104, 44, 547, 122);
		frmCompsciInteractiveMedical.getContentPane().add(textArea);
		
		textField = new JTextField();
		textField.setBounds(104, 195, 547, 20);
		frmCompsciInteractiveMedical.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblResponse = new JLabel("Response:");
		lblResponse.setBounds(38, 198, 60, 14);
		frmCompsciInteractiveMedical.getContentPane().add(lblResponse);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textField.setText("");
				btnNewButton.setEnabled(true);
			}
		});
		btnNewButton_2.setBounds(551, 292, 89, 23);
		frmCompsciInteractiveMedical.getContentPane().add(btnNewButton_2);
	}
}
