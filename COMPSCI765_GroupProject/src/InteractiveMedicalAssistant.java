import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import net.sf.clipsrules.jni.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;


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
		frmCompsciInteractiveMedical.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			    //fileChooser.setCurrentDirectory(new File("X:\\"));
			    //fileChooser.setSelectedFile(new File("README.html"));
			    int result = fileChooser.showOpenDialog(null);
			    if (result == JFileChooser.APPROVE_OPTION)
		        {
		            String filename = fileChooser.getSelectedFile().getPath();
		            JOptionPane.showMessageDialog(null, "Reading: " + filename);
		            
		            List<String> data = new ArrayList<String>();
		            
		            try
		            {
			            FileReader read = new FileReader(filename);
			            BufferedReader bf = new BufferedReader(read);
			            String line="";
			            while((line = bf.readLine())!=null)
			            {
			            	data.add(line);
			            }
			            bf.close();
			            
			            for(String temp : data)
			            {
			            	System.out.println(temp);
			            }
		            	
		            }
		            catch (Exception excep)
		            {

		            }
		        }
			}
		});
		frmCompsciInteractiveMedical.setTitle("COMPSCI765: Interactive Medical Assistant");
		frmCompsciInteractiveMedical.setBounds(100, 100, 765, 365);
		frmCompsciInteractiveMedical.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCompsciInteractiveMedical.getContentPane().setLayout(null);
		
		// IC state is init
		textArea.setText(IC.interactive_action(""));
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			if(IC.getState().equals("question"))
			{
				textArea.setText(IC.interactive_action(textField.getText()));
			}
			else if(IC.getState().equals("advise"))
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
