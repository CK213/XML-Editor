import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument.AbstractElement;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.Scanner;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.text.*;
import java.util.*;
import org.xml.sax.SAXException;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Frame1 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel MainPanel;
	private JPanel MenuPanel;
	private JPanel AnimationPanel;
	private JButton greenTrail;
	private JButton purpleTrail;
	private JSeparator animationMenuDivider;
	private JSlider rangeSlider;
	private JTextArea textArea;
	private String colname;
	private String varname;

	private static String getTagValue(String sTag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    return nValue.getNodeValue();
	}
	
	/**
	 * Launch the application.
	 */
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    File is = new File(xml);
	    return builder.parse(is);
	}
	
	public Frame1(){
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(652, 520);
    setLocation(100, 100);

    JButton read = new JButton("read the XML file");
    read.addActionListener(this);
    getContentPane().add(read, BorderLayout.EAST);
    read.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
    	  try {
				readXML();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      }
    });
    
    JButton modify = new JButton("modify the XML file");
    modify.addActionListener(this);
    getContentPane().add(modify, BorderLayout.WEST);
    modify.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
    	  try {
				modifyXML();
				//Create the frame.
				JFrame frame = new JFrame("Modifying");
				frame.setVisible(true);
				//set frame size
				frame.setSize(500,200);
				//What happens when the frame closes?
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JPanel panel = new JPanel();
				frame.add(panel);
				JButton addelement = new JButton("add new element");
				panel.add(addelement);
				addelement.addActionListener (new Addelement());
				
				JButton updateelement = new JButton("update element");
				panel.add(updateelement);
				updateelement.addActionListener (new Updateelement());

				JButton deleteelement = new JButton("delete element");
				panel.add(deleteelement);
				deleteelement.addActionListener (new Deleteelement());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      }
    });
/*    public AbstractElement(){
    	super("Enter a column name you want to add: ");
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.Center, 15, 15));
    	southPanel.add(submit);
    	Box box = Box. createVerticalBox();
    	box.add(Box.createVerticalStrut(100));
    	box.add(centerPanel);
    	box.add(Box.createVerticalStruct(200));
    	box.add(southPanel);
    	add(box);
    }
    
    class Addelement implements ActionListener{
    	public void actionPerformed (ActionEvent e){
 	        Scanner reader = new Scanner(System.in);
 	        textArea.append("Enter a column name you want to add: ");
 	        colname = userInput.getText();
 	        
 	        textArea.append("Enter the variables (name) you want to add: ");
 	        varname = userInput.getText();
 	        
 	        //Add new column and loop for whole list
 	        for(int i=0; i<columns.getLength();i++){
 	            emp = (Element) columns.item(i);
 	            Element NewName = doc.createElement(colname);
 	            NewName.appendChild(doc.createTextNode(varname));
 	            emp.appendChild(NewName);
 	        }
    	}
    }
   */
    JLabel lblYouWantTo = DefaultComponentFactory.getInstance().createTitle("You want to read or modify the XML file?");
    getContentPane().add(lblYouWantTo, BorderLayout.NORTH);
    textArea = new JTextArea();
    textArea.setEnabled(false);
    textArea.setEditable(false);
    getContentPane().add(textArea, BorderLayout.CENTER);

    setVisible(true);
}
	public static void main(String[] args) {
		new Frame1();
	}

	/**
	 * Create the frame.
	 */
	@Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("read")) {
        	try {
				readXML();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        if (command.equals("modify"))
			try {
				modifyXML();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    }

	public void readXML() throws Exception {

		        Document document;
		        String fileName = "C:\\\\\\\\Users\\\\\\\\cherrenku\\\\\\\\Documents\\\\\\\\AST-year1_semA-notes\\\\\\\\Java-groupproject\\\\\\\\AST10106_ProjectSampleData.xml";
		        document = loadXMLFromString(fileName);

		        //re edit 
		        //first get the element from column 
		        NodeList ColumnNodes = document.getElementsByTagName("columns");
		        NodeList A = ColumnNodes.item(0).getChildNodes();
		        Node temptest = ColumnNodes.item(0).getFirstChild();
		        int nodeindex = (A.getLength()/2);
		        textArea.append(Integer.toString(nodeindex));
		        String [] ColName = new String[nodeindex];

		        for (int i = 1; i < nodeindex; i++) {
		           temptest = temptest.getNextSibling();
		           ColName [i] = temptest.getNodeName();
		            //test the element Name
		            //System.out.println(ColName[i]);
		           textArea.append(ColName[i]);
		             
		           temptest = temptest.getNextSibling();
		        }

		        NodeList DataNodeList = document.getElementsByTagName("record");
		        int NumOfRec = DataNodeList.getLength();
		        String[][] DataRec = new String[NumOfRec][nodeindex];
		        textArea.append(Integer.toString(NumOfRec));

		        for(int K = 0;K < NumOfRec ; K++){
		            
		        	textArea.append("Record "+ (K+1) );
		            NodeList b = DataNodeList.item(K).getChildNodes();
		            Node tempB = DataNodeList.item(K).getFirstChild();

		            for (int H = 1; H < nodeindex; H++) {
		                tempB = tempB.getNextSibling();
		                DataRec [K][H] = tempB.getTextContent();
		                 //test the element Name
		                textArea.append(ColName[H] + " : " + DataRec [K][H]);
		                tempB = tempB.getNextSibling();
		            }
		            System.out.println();
		            textArea.append("\n");
		        }		      
		        // This for Reference 
		        Node temptest1 = ColumnNodes.item(0).getFirstChild().getNextSibling().getNextSibling().getNextSibling();
		        textArea.append(temptest1.getNodeName() + " 0a0 " + temptest1.getNodeValue() + " 0a0 " + temptest1.getTextContent());
		        //then print line 
//				return null;
	}

	    public void modifyXML() throws Exception {

	        try {
	        	Document doc;
		        String fileName = "C:\\\\\\\\Users\\\\\\\\cherrenku\\\\\\\\Documents\\\\\\\\AST-year1_semA-notes\\\\\\\\Java-groupproject\\\\\\\\AST10106_ProjectSampleData.xml";
		        doc = loadXMLFromString(fileName);

	            // Get the root element
	            Node first= doc.getFirstChild();
		        NodeList ColumnNodes = doc.getElementsByTagName("columns");
		        NodeList A = ColumnNodes.item(0).getChildNodes();
		        Node temptest = ColumnNodes.item(0).getFirstChild();
		        int nodeindex = (A.getLength()/2);
		        System.out.println(nodeindex);
		        TransformerFactory transformerFactory = TransformerFactory
	                    .newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(fileName));
	            transformer.transform(source, result);

	            System.out.println("Done");

	        } catch (ParserConfigurationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (TransformerException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SAXException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}