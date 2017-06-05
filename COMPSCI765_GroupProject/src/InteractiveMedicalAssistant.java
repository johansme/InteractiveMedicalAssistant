import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InteractiveMedicalAssistant {

	private JFrame frmCompsciInteractiveMedical;
	private JTextField textField;
	private InteractiveController IC = new InteractiveController();
	private boolean collect = true;
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
			            read.close();
   
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
				            	
				            	name = name.replace(" ", "-");
				            	
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
		
		try{

			InputStream input = this.getClass().getResourceAsStream("/kbs/CLIPSJNI.dll");

			String current = new java.io.File( "." ).getCanonicalPath();
			IC.setPath(current);
	
			FileOutputStream out = new FileOutputStream(current+"\\CLIPSJNI.dll");

    	    byte[] buf = new byte[2048];
    	    int r;
    	    while(-1 != (r = input.read(buf))) {
    	        out.write(buf, 0, r);
    	    }
    	    
    	    input.close();
    	    out.close();

		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
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
			in.close();
			reader.close();
		}catch (IOException e1) {
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
				else// keep asking till yes or no
				{
					textField.setText("");
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
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				  if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						
						if(IC.getState().equals("yes"))
						{
							if(collect)
							{
								IC.getHas_symptom().add(textField.getText());
								
								int reply = JOptionPane.showConfirmDialog(null, "Would you like to give more symptoms ?", "More Symptoms ?", JOptionPane.YES_NO_OPTION);
								
						        if (reply == JOptionPane.YES_OPTION) {
						        	collect = true;
						        	textField.setText("");
						        }
						        else {
						        	collect=false;
						        	textArea.setText(IC.interactive_action(textField.getText()));
									textField.setText("");
						        }
							}
							else
							{								
								IC.setSymptom_add_remove(textField.getText());
								textArea.setText(IC.interactive_action(textField.getText()));
								textField.setText("");
								
							}
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
							else// keep asking till yes or no
							{
								JOptionPane.showMessageDialog(frmCompsciInteractiveMedical, "Please use yes or no");
								textField.setText("");
							}
					   }					  
				  }				
			}
		});
		
		textField.setBounds(104, 195, 547, 20);
		frmCompsciInteractiveMedical.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblResponse = new JLabel("Response:");
		lblResponse.setBounds(38, 198, 60, 14);
		frmCompsciInteractiveMedical.getContentPane().add(lblResponse);
		
		JButton btnNewButton_2 = new JButton("Help");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextArea textArea = new JTextArea(
						        "abdomen belly\n"+
								"ablation remove\n"+
								"absorb take up fluids, take in\n"+
								"abstain avoid\n"+
								"acuity clearness\n"+
								"acute short-term; sudden onset\n"+
								"adenopathy swollen glands\n"+
								"adjuvant helpful, assisting, added\n"+
								"adverse effect side effect\n"+
								"allergic reaction rash, trouble breathing\n"+
								"alopecia hair loss\n"+
								"ambulate walk, able to walk\n"+
								"amnesia loss of memory; inability to remember\n"+
								"analgesic pain relieving medication\n"+
								"anaphylaxis a severe, life-threatening allergic reaction\n"+
								"anemia low red blood cell count\n"+
								"anesthetize to numb; put to sleep\n"+
								"angina pain from not enough blood to the heart\n"+
								"anorexia loss of appetite\n"+
								"antecubital inside the elbow\n"+
								"antibiotic (antimicrobial) drug that kills germs\n"+
								"antibodies natural chemicals in the body that fight infection\n"+
								"anticonvulsant drug used to prevent seizures\n"+
								"antilipidemic drug that decreases the level of fat in the blood\n"+
								"antiretroviral drug that inhibits viruses\n"+
								"antitussive drug that relieves coughing\n"+
								"anus rectum\n"+
								"arrhythmia or dysrhythmia a change from the normal heartbeat\n"+
								"artery blood vessel\n"+
								"arthralgia joint pain\n"+
								"aspiration inhale into the lungs; to suck in; or removal of fluid through tube or needle\n"+
								"assess to learn about\n"+
								"asthenia loss of energy; weakness\n"+
								"asthma lung disease associated with tightening of the air passages\n"+
								"asymptomatic without symptoms\n"+
								"ataxia unsteady movement\n"+
								"axilla armpit\n"+
								"bacteria germs\n"+
								"benign not cancerous; without serious consequences\n"+
								"bid twice a day\n"+
								"bioavailability the extent to which a drug becomes available to the body\n"+
								"biopsy the removal and examination of a small part of tissue or organ\n"+
								"blood profile series of blood tests\n"+
								"bolus given all at once\n"+
								"bone density bone thickness; hardness of bone\n"+
								"bradycardia slow heart rate\n"+
								"bronchospasm difficulty breathing because the airways have narrowed\n"+
								"confidentiality will not be\n"+
								"compromised other people will not find out\n"+
								"carcinogenic can cause cancer\n"+
								"carcinoma a type of cancer\n"+
								"cardiac heart; involving the heart\n"+
								"cardiac catheterization\n"+
								"a procedure in which a small tube (catheter) is inserted through blood\n"+
								"vessels in the groin and into the heart that uses highlighting fluid to look at\n"+
								"the blood vessels in the heart\n"+
								"cardioversion a procedure that uses electricity to simulate the heart and make it return to\n"+
								"its normal rhythm\n"+
								"catheter flexible plastic tube that is inserted into the ( )\n"+
								"central nervous system the brain and spinal cord\n"+
								"cerebral trauma damage to the brain\n"+
								"cessation stopping\n"+
								"chemotherapy treatment of disease with chemical agents\n"+
								"chronic continues for a long time; long-term\n"+
								"clinical medical care\n"+
								"clinically significant of major importance for treating or evaluating patients\n"+
								"clinical trial an experiment in humans\n"+
								"coerced pressured; forced\n"+
								"cognitive status levels of awareness and thinking\n"+
								"colon large intestines; bowel\n"+
								"colonoscopy procedure to look at the large intestine that uses a special camera at the\n"+
								"end of a long tube that is inserted in the rectum\n"+
								"coma unconscious state\n"+
								"congenital occurring prior to birth, due to parent’s genetic input\n"+
								"conjunctivitis irritation and redness in the membrane covering the eye\n"+
								"consent agreement\n"+
								"contrast material a fluid that is given into the vein or an area of the body to highlight the\n"+
								"blood vessels or body area under x-ray examination\n"+
								"convulsions seizures\n"+
								"coronary heart; involving the heart\n"+
								"creatinine clearance a test of kidney function\n"+
								"computed tomography; (ct)\n"+
								"scan computer enhanced x-ray; special type of x-ray\n"+
								"culture a test for the presence of germs\n"+
								"cumulative total sum (of events, experiences, treatments)\n"+
								"cutaneous of the skin\n"+
								"defecate bowel movement; to pass stools\n"+
								"defibrillation a procedure that uses electricity to stimulate the heart and make it return to\n"+
								"its normal rhythm\n"+
								"de novo new\n"+
								"dermatologic of the skin\n"+
								"deteriorate to get worse; to lose function\n"+
								"diastolic blood pressure the blood pressure when the heart rests between beats; the bottom number\n"+
								"of a blood pressure reading\n"+
								"diplopia double vision\n"+
								"distal away from the center of the body\n"+
								"distensions bloated; swollen; inflated\n"+
								"diuretic drug that causes an increase in urination\n"+
								"double-blind neither you nor your doctor will know what medicine you are taking (this\n"+
								"can be found out easily if necessary)\n"+
								"duodenum beginning of the small intestine that is attached to the stomach\n"+
								"dysfunction not functioning properly\n"+
								"dyspepsia gas; upset stomach\n"+
								"dysplasia abnormal cells\n"+
								"dyspnea hard to breathe; short of breath\n"+
								"ecg, ekg, or\n"+
								"electrocardiogram picture and measurement of the heart rhythm\n"+
								"echocardiogram procedure that uses sound waves to look at the heart; using sound waves\n"+
								"to take pictures of the heart chambers and measure it pumping strength\n"+
								"eeg measurement of electrical activity of the brain\n"+
								"edema swelling\n"+
								"efficacy effectiveness; usefulness\n"+
								"electrode wire\n"+
								"electrolyte imbalance imbalance of salts or chemicals in the blood\n"+
								"electrophysiology study heart rhythm study\n"+
								"elevation of liver function tests evidence of liver or kidney damage\n"+
								"elucidate to make clear; to determine; to find out\n"+
								"embryo unborn baby\n"+
								"emesis vomiting\n"+
								"empiric based on experience\n"+
								"endoscopic exam exam of an internal part of the body with a lighted tube\n"+
								"enema medication given through the rectum that cleans out the bowel\n"+
								"enteral by way of the intestines\n"+
								"enzyme abnormality blood test result that suggests abnormal organ function or injured cells\n"+
								"epidural outside the spinal cord\n"+
								"eradicating getting rid of\n"+
								"exclusion criteria reasons for not being included\n"+
								"excreted made; given off; put off\n"+
								"external outside of the body\n"+
								"extravasate to leak outside of a blood vessel\n"+
								"fast do not eat or drink\n"+
								"fatigue tire\n"+
								"fetus unborn baby\n"+
								"flatulence gas passed through the anus/rectum\n"+
								"fibrillation irregular beat of the heart or other muscle\n"+
								"fibrous having many fibers, like scar tissue\n"+
								"flushing to become red in the face or other part of the body because of rush of\n"+
								"blood to the skin; blushing of the skin\n"+
								"fracture break\n"+
								"gastric stomach\n"+
								"gastrointestinal stomach and intestines\n"+
								"gestational pertaining to pregnancy\n"+
								"glucose sugar\n"+
								"hematocrit amount of red blood cells in the blood\n"+
								"hematoma a bruise; bleeding into the body tissue around a blood vessel (if at the skin\n"+
								"surface, it looks like a bruise)\n"+
								"hemodynamic measurement test to measure blood flow\n"+
								"hemolysis breakdown in red blood cells\n"+
								"heparin lock needle placed with blood thinner to keep blood from clotting inside the\n"+
								"needle or tubing\n"+
								"hepatic liver\n"+
								"hepatitis inflammation of the liver\n"+
								"hepatoma cancer or tumor of the liver\n"+
								"heritable disease a disease which can be passed to children resulting in damage to future\n"+
								"children\n"+
								"histopathologic pertaining to disease of body tissues or cells\n"+
								"history positive in past medical history; that you have ever had\n"+
								"holter monitor a machine, the size of a pocket radio, that records the beats of the heart\n"+
								"hypercalcemia high blood calcium level\n"+
								"hyerkalemia high blood potassium level\n"+
								"hypernatremia high blood sodium level\n"+
								"hypertension high blood pressure\n"+
								"hypocalcemia low blood calcium level\n"+
								"hypokalemia low blood potassium level\n"+
								"hyponatremia low blood sodium level\n"+
								"hypotension low blood pressure\n"+
								"hypoxia low oxygen level in the blood\n"+
								"hysterectomy removal of the womb\n"+
								"iatrogenic caused by a physician or by treatment\n"+
								"idiopathic of unknown cause\n"+
								"immobilization unable to move\n"+
								"immunological relating to the body's ability to fight infection\n"+
								"immunosuppressive drug which suppresses the body’s immune response\n"+
								"immunotherapy drug to help the body’s immune system\n"+
								"impaired function abnormal function\n"+
								"implantation operation to place a device inside the body\n"+
								"incision cut\n"+
								"indicated suggested; necessary\n"+
								"induce cause\n"+
								"induction phase beginning phase or stage of treatment\n"+
								"induration hardening\n"+
								"indwelling remaining in a given location, such as a catheter\n"+
								"inert not active; substance that doesn't affect you\n"+
								"infectious disease disease transmitted from person to person\n"+
								"inflammation swelling\n"+
								"infarct death of tissue because of lack of blood supply\n"+
								"inflation to put air in\n"+
								"infused to drip in; to put in\n"+
								"ingest swallow; eat or drink\n"+
								"inject to put into by way of a needle or other device\n"+
								"insomnia unable to sleep\n"+
								"instilled put into; drip into\n"+
								"intensity degree; amount\n"+
								"interferon agent that acts against viruses\n"+
								"intermittent occurring repeatedly; alternately stopping and starting\n"+
								"intramuscular injection to put into the muscle with a needle\n"+
								"intraperitoneal into the abdominal cavity\n"+
								"intrathecal into the spinal fluid\n"+
								"intravenous in the blood vessel\n"+
								"intravenous infusion to drip into the blood vessel through a plastic tube and needle\n"+
								"intravesical in the bladder\n"+
								"intubate the placement of a tube into the airway\n"+
								"invasive procedure puncture, opening or cutting of the skin\n"+
								"ischemia decreased oxygen in a tissue (usually because of decreased blood flow)\n"+
								"isolated to separate; to close off\n"+
								"lactating making breast milk\n"+
								"laporatomy incision in the abdominal wall to allow a physician to look at the organs\n"+
								"lesion site of the injury; site of the disease\n"+
								"lethargy sleepiness\n"+
								"leukocyte blood cells that fight infection\n"+
								"leukopenia low white blood cell count\n"+
								"libido sexual desire; sex drive\n"+
								"local anesthetic medicine to numb an area of the body\n"+
								"localized restricted to one area\n"+
								"lumbar puncture spinal tap; a needle inserted between the bones of the spine to put in a\n"+
								"drug or take a sample of fluid\n"+
								"lumbosacral lower back\n"+
								"lymphangiography an x-ray of the lymph nodes or tissues after injection of dye in lymph\n"+
								"vessels\n"+
								"lymphocyte a type of white blood cell\n"+
								"lymphoma a cancer of the lymph nodes or tissues\n"+
								"maintenance dose the usual daily dose\n"+
								"malaise a vague feeling of discomfort, feeling bad\n"+
								"malignancy tumor; cancer\n"+
								"manifested showed\n"+
								"medullobastoma type of brain tumor\n"+
								"meningitis infection or irritation around the brain\n"+
								"metabolism process by which food is used to supply energy for the body; the energy\n"+
								"the body uses when it works; the way the body breaks down food or a drug\n"+
								"metastasize spread\n"+
								"(mri) magnetic resonance imaging\n"+
								"procedure that uses magnetic waves to take a picture of various parts of\n"+
								"the body (radiation is not used)\n"+
								"monitor check on; keep track of; watch carefully\n"+
								"morbidity undesired result or complication; serious disease\n"+
								"mortality death or death rate\n"+
								"mucosa the lining inside the ( )\n"+
								"muga procedure using a special camera to look at the heart after radioactive dye\n"+
								"has been put into it through one of the veins\n"+
								"myalgia muscle aches\n"+
								"myocardial infarction heart attack\n"+
								"nasal congestion stuffiness of the nose\n"+
								"nasal nose\n"+
								"nasogastric tube tube from the nose to the stomach\n"+
								"nausea feel sick to the stomach\n"+
								"necrosis death of tissue\n"+
								"neoplasia tumor, may be non-cancerous or cancerous\n"+
								"neurological exam test of brain, spinal cord, and reflexes\n"+
								"neuroblastoma cancer of nerve tissue\n"+
								"neurological pertaining to the nervous system\n"+
								"neutropenia decrease in the main part of the white blood cells\n"+
								"new indication new use\n"+
								"non-invasive not breaking, cutting or entering the skin\n"+
								"nosocomial pneumonia pneumonia acquired in the hospital\n"+
								"ng tube a plastic tube that is passed through the nose or mouth and into the stomach\n"+
								"occlusion closing; obstruction\n"+
								"occult blood test a test for small amounts of blood in the stool\n"+
								"oncology the study of tumors or cancer\n"+
								"open-label a study in which the drug, device, procedure is known to patient and\n"+
								"investigator\n"+
								"ophthalmic pertaining to the eye\n"+
								"optimum, optimal best\n"+
								"oral by mouth\n"+
								"orthopedic pertaining to the bones\n"+
								"osteoarthritis bone and joint pain\n"+
								"osteopetrosis bone disorder characterized by dense bone\n"+
								"osteoporosis bone disorder characterized by loss of bone\n"+
								"ovaries female sex glands; female organs which release eggs\n"+
								"over-the-counter drugs medications that you can buy without a doctor's prescription\n"+
								"overnight fast do not eat or drink after ( ) p.m.\n"+
								"palpitations irregular heart beats that you can feel\n"+
								"parenteral administration by injection\n"+
								"partial seizure seizure\n"+
								"perception view\n"+
								"perforation hole; tear\n"+
								"patency condition of being open\n"+
								"pathogenesis the initial cause of a disease\n"+
								"percutaneous through the skin\n"+
								"perforation puncture, tear of hole\n"+
								"peripheral not central\n"+
								"pet (positron emission\n"+
								"tomography)\n"+
								"special camera that uses radiation to look at the structure and functioning\n"+
								"of parts of the body\n"+
								"pharmacokinetics study of the what the body does to the drug\n"+
								"pharmacological effect of the drug\n"+
								"phlebitis inflammation of a vein\n"+
								"physiologically capable able to function\n"+
								"placebo a pill that does not contain active medicine\n"+
								"plasma blood\n"+
								"platelets small particle in the blood that help with blood clotting\n"+
								"pneumonia lung infection\n"+
								"pneumothorax collapsed lung\n"+
								"polyps abnormal lumps that can sometimes be cancerous\n"+
								"potentiate increasing effect of a drug by administration of another drug at the same time\n"+
								"potentiator agent that helps another agent work better\n"+
								"predictive value expected value\n"+
								"prenatal before birth\n"+
								"prognosis expected course of the disease\n"+
								"prone to lie flat facing down; to lie on the stomach\n"+
								"prophylaxis drug given to prevent disease or infection\n"+
								"prorated compensation less if you do not complete the study\n"+
								"prospective study study following patients forward in time\n"+
								"prosthesis artificial limbs\n"+
								"proximal closer to the center of the body\n"+
								"psychosocial test test of your behavior\n"+
								"pulmonary lung\n"+
								"puncture to make a hole\n"+
								"pyelogram a series of x-rays of the kidneys\n"+
								"qd every day, daily\n"+
								"qid four times a day\n"+
								"quantify to measure\n"+
								"radiation therapy x-ray or cobalt treatment\n"+
								"radioactive isotope a chemical or substance that gives off radiant energy rays similar to x-rays\n"+
								"randomly like picking numbers out of a hat ; by chance\n"+
								"recombinant formation of new combinations of genes\n"+
								"reconstitution putting back together the original parts\n"+
								"recuperate to get better\n"+
								"recur happen again\n"+
								"refractory not responding to treatment\n"+
								"regeneration regrowth of a structure or of lost tissue\n"+
								"regimen pattern of administering treatment\n"+
								"relapse the return or reappearance of disease\n"+
								"reliable method of birth control\n"+
								"[you must indicate what you will accept. ex:]--birth control pills, norplant7\n"+
								"(levonorgestrel), depo-provera7 (medroxyprogesterone), intra-uterine\n"+
								"device (iud)\n"+
								"remission disappearance of evidence of cancer or other disease\n"+
								"renal kidney\n"+
								"render you ineligible make you unable to participate\n"+
								"replicable capable of being duplicated\n"+
								"resect remove or cut out (surgically)\n"+
								"respiratory breathing\n"+
								"retrospective study study looking back over past experience\n"+
								"saline salt water\n"+
								"sarcoma a type of cancer\n"+
								"secretion one of the fluids made by the body\n"+
								"sedation to make drowsy/sleepy\n"+
								"seminoma a type of testes cancer\n"+
								"sequentially in a row\n"+
								"somnolence sleepiness\n"+
								"spirometer instrument to measure the amount of air taken into and exhaled from the lungs\n"+
								"sputum thick saliva; phlegm\n"+
								"staging a determination of the extent of the disease\n"+
								"standard of care the usual treatment for the disease\n"+
								"stenosis narrowing of a duct, tube, or one of the heart valves\n"+
								"stent a metal (or plastic) tube placed inside a blood vessel that keeps it open to\n"+
								"prevent blockage\n"+
								"stomatitis mouth sores; inflammation of the mouth\n"+
								"stratify arrange in groups fro analysis of results\n"+
								"stupor stunned state in which it is difficult to get a response\n"+
								"subclavian under the collarbone\n"+
								"subcutaneous under the skin\n"+
								"superficial near the surface\n"+
								"supine to lie flat facing up; to lie on the back\n"+
								"suture stitches\n"+
								"symptoms signs of disease or illness\n"+
								"symptomatic having symptoms\n"+
								"syncope fainting or lightheadedness\n"+
								"syndrome a condition characterized by a set of symptoms\n"+
								"systolic blood pressure top number of by blood pressure reading\n"+
								"tachycardia fast heart rate\n"+
								"telemetry to monitor the ( ) from a distance\n"+
								"teratogenic can cause malformations in unborn fetuses\n"+
								"testes male sex glands; male organs which produce sperm\n"+
								"therapeutic dose the amount of medication needed to treat the condition\n"+
								"third party payers health insurance; medicare, medicaid\n"+
								"thrombosis blood clotting within blood vessels\n"+
								"tid three times a day\n"+
								"tinnitus ringing in the ears\n"+
								"titration adjusting to the necessary dose\n"+
								"t-lymphocytes type of white blood cells involved in immune reactions\n"+
								"topical applied to the surface of the skin\n"+
								"toxicity side effects or undesirable effects of a drug\n"+
								"trachea windpipe\n"+
								"transdermal through the skin\n"+
								"transiently temporarily\n"+
								"trauma injury; wound\n"+
								"treadmill walking machine often used to determine heart function\n"+
								"tremor shakiness\n"+
								"trial study\n"+
								"triglyceride fat in the blood\n"+
								"tubal ligation having the tubes tied\n"+
								"unable to comply with the study\n"+
								"requirements cannot follow study directions\n"+
								"ureter the tube that carries urine from the kidneys to by bladder\n"+
								"urethra the tube that carries urine from the bladder outside the body\n"+
								"urinalysis urine exam; test of the urine\n"+
								"vaginitis infection in the vagina or birth canal\n"+
								"valvuloplasty plastic repair of a valve, especially of the heart\n"+
								"varices enlarged veins\n"+
								"vasospasm narrowing of blood vessels due to spasm of vessel walls\n"+
								"vector a carrier, usually an inset, that carries and transmits disease-causing germs\n"+
								"vein blood vessel\n"+
								"venipuncture to put a needle into the blood vessel\n"+
								"verbal speak; talk\n"+
								"vertical transmission spread of disease\n"+
								"vertigo a feeling of losing the balance; dizziness\n"+
								"void to make or pass urine\n"+
								"waive to give up\n"+
								"withdraw leave the study; quit\n"		
						);
				JScrollPane scrollPane = new JScrollPane(textArea);  
				textArea.setLineWrap(false);  
				textArea.setWrapStyleWord(true); 
				scrollPane.setPreferredSize(new Dimension(600,500));
				JOptionPane.showMessageDialog(null, scrollPane, "Medical Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_2.setBounds(540, 292, 89, 23);
		frmCompsciInteractiveMedical.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Model");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String hypotheses = "";
				for(String hypothesis : IC.getHypothesis())
				{
					hypotheses += hypothesis + "\n";
				}
				
				String has_symptom = "";
				for(String has_s : IC.getHas_symptom())
				{
					has_symptom += has_s + "\n";
				}
				
				String has_not_symptom = "";
				for(String has_not_s : IC.getHas_not_symptom())
				{
					has_not_symptom += has_not_s + "\n";
				}
				
				JTextArea textArea = new JTextArea(
						"Hypotheses:\n\n"+
						 hypotheses +
					     "\n"+
						"Has Symptoms:\n\n"+
					     has_symptom +
					     "\n"+
						"Has not Symptoms:\n\n"+
					     has_not_symptom +
					     "\n"
				);
		JScrollPane scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(false);  
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension(200,500));
		JOptionPane.showMessageDialog(null, scrollPane, "The Current Model", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnNewButton_3.setBounds(598, 250, 89, 23);
		frmCompsciInteractiveMedical.getContentPane().add(btnNewButton_3);
	}
}
