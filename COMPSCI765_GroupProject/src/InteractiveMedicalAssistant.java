import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;
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
				// if enter here, built-in KB gets overwritten by user input
				IC.clearKnowledge();
				
				JFileChooser fileChooser = new JFileChooser();
			    int result = fileChooser.showOpenDialog(null);
			    if (result == JFileChooser.APPROVE_OPTION)
		        {
		            String filename = fileChooser.getSelectedFile().getPath();
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
			            IC.setKnowledge(data);
			            bf.close();
   
			            try
			            {
			                PrintWriter writer = new PrintWriter("kbs.clp", "UTF-8");
			                List<String> symptom_one = new ArrayList<String>();
				            String[] line_split;
				            String clips_rule="";
				            String symptoms="";
				            String name="";
				            
				            for(String temp : data)
				            {
				            	name = temp.substring(0, temp.indexOf(":"));
				            	
				            	clips_rule="(defrule "+name+" \n";
				            	clips_rule+="(disease-is "+name+")\n";
				            	clips_rule+="=>\n";
				            	
				            	symptoms = temp.substring(temp.indexOf(":")+1, temp.length());
				            	
				            	line_split=symptoms.split(":");
				            	
				            	for(String each_sym : line_split)
				            	{
				            		if(!(symptom_one.contains(each_sym)))
				            		{
				            			symptom_one.add(each_sym);
				            		}
				            	}
				            	
				            	symptoms=symptoms.replaceAll(":", "\" \"");
				            	clips_rule+="(printout t "+symptoms+" crlf))\n";
					            writer.println(clips_rule);
				            	clips_rule="";
				            	symptoms="";
				            	name="";
				                line_split=new String[0]; 
				            }
				            
				            clips_rule="";
				            String disease_list = "";
				            for(String each_symptom : symptom_one)
				            {
					            for(String each_data: data)
					            {
					            	if(each_data.contains(each_symptom))
					            	{
					            		disease_list+=each_data.substring(0, each_data.indexOf(":"))+"\" \"";
					            	}
					            }
					            
				            	clips_rule="(defrule "+each_symptom+" \n";
				            	clips_rule+="(symptom-is "+each_symptom+")\n";
				            	clips_rule+="=>\n";
				            	clips_rule+="(printout t "+disease_list+" crlf))\n";
					            writer.println(clips_rule);
				            	clips_rule="";
				            	disease_list="";
				            }
		                    writer.close();
			            }
				        catch (Exception excepx) {
			            }
                    }
		            catch (Exception excep){
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
		
		InputStream in = getClass().getResourceAsStream("/kbs/kbs"); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		List<String> knowledge = new ArrayList<String>();
		
		String line = "";
		 try {
			while((line = reader.readLine())!=null)
			 {
				knowledge.add(line);
			 }
			IC.setKnowledge(knowledge);
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
  		
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			if(IC.getState().equals("yes"))
			{
				IC.setSymptom_add_remove(textField.getText());
				textArea.setText(IC.interactive_action(textField.getText()));
				textField.setText("");
			}
			else if(IC.getState().equals("advise"))
			{
				textArea.setText(IC.interactive_action(textField.getText()));
				textField.setText("");
				btnNewButton.setEnabled(false);
			}
			else if(IC.getState().equals("yes/no"))
			{
				if(textField.getText().equals("yes")||textField.getText().equals("no"))
				{
					if(textField.getText().equals("yes"))
					{
						IC.setState("yes");
						textArea.setText(IC.interactive_action(textField.getText()));
						textField.setText("");
					}
					else if(textField.getText().equals("no"))
					{
						IC.setState("no");
						textArea.setText(IC.interactive_action(textField.getText()));
						textField.setText("");
					}
				}
				else// keep asking till yes or no is recived
				{
					JOptionPane.showMessageDialog(frmCompsciInteractiveMedical, "Please use yes or no");
				}
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
