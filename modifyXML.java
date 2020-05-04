	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */

/*	import java.io.File;
	import java.io.IOException;
	import java.util.Scanner;

	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.transform.OutputKeys;
	import javax.xml.transform.Transformer;
	import javax.xml.transform.TransformerException;
	import javax.xml.transform.TransformerFactory;
	import javax.xml.transform.dom.DOMSource;
	import javax.xml.transform.stream.StreamResult;

	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;
	import org.xml.sax.SAXException;

	public class modifyXML {

	    public static void main(String[] args) {
	        String filePath = "C:\\\\\\\\Users\\\\\\\\cherrenku\\\\\\\\Documents\\\\\\\\AST-year1_semA-notes\\\\\\\\Java-groupproject\\\\\\\\AST10106_ProjectSampleData.xml";
	        File xmlFile = new File(filePath);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
	        try {
	            dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            
	            //update attribute value
	            updateAttributeValue(doc);
	            
	            //delete element
	            deleteElement(doc);
	            
	            //add new element
	            addElement(doc);
	            
	            //write the updated document to file or console
	            doc.getDocumentElement().normalize();
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File("AST10106_ProjectSampleData_updated.xml"));
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.transform(source, result);
	            System.out.println("XML file updated successfully");
	            
	        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {}
	    }

	    private static void addElement(Document doc) {
	        NodeList columns = doc.getElementsByTagName("columns");
	        Element emp = null;
	        
	        Scanner reader = new Scanner(System.in);
	        System.out.println("Enter a column name you want to add: ");
	        String colname = reader.next();
	        
	        System.out.println("Enter the variables (name) you want to add: ");
	        String varname = reader.next();
	        
	        //Add new column and loop for whole list
	        for(int i=0; i<columns.getLength();i++){
	            emp = (Element) columns.item(i);
	            Element NewName = doc.createElement(colname);
	            NewName.appendChild(doc.createTextNode(varname));
	            emp.appendChild(NewName);
	        }
	    }

	    private static void deleteElement(Document doc) {
	        NodeList columns = doc.getElementsByTagName("columns");
	        Element emp = null;
	        
	        Scanner reader = new Scanner(System.in);
	        System.out.println("Enter the column name you want to delete: ");
	        String delname = reader.next();
	        
	        //loop for each employee
	        for(int i=0; i<columns.getLength();i++){
	            emp = (Element) columns.item(i);
	            Node columnNode = emp.getElementsByTagName(delname).item(0);
	            emp.removeChild(columnNode);
	        }
	        
	    }

	    private static void updateAttributeValue(Document doc) {
	        NodeList columns = doc.getElementsByTagName("columns");
	        Element emp = null;
	        
	        Scanner reader = new Scanner(System.in);
	        System.out.println("Please input the product ID that you want to change it's value: ");
	        int upID = reader.nextInt();
	        
	        System.out.println("Enter the column you want to change: ");
	        String upcolumn = reader.next();
	        
	        System.out.println("Enter the value you want to change: ");
	        String upvalue = reader.next();
	        
	        //loop for each employee
	        for(int i=0; i<columns.getLength();i++){
	            emp = (Element) columns.item(i);
	            String valueChange = emp.getElementsByTagName("columns").item(0).getFirstChild().getNodeValue();
	            if(valueChange.equals(upID)){
	                //prefix id attribute with M
	                emp.setAttribute(upcolumn, upvalue+emp.getAttribute(upcolumn));
	            }
	        }
	 }
	}
}
*/
import java.io.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class modifyXML {
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    File is = new File(xml);
	    return builder.parse(is);
	}

    public static void main(String argv[]) throws Exception {

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
/*	        String [] ColName = new String[nodeindex];

            Node startdate = doc.getElementsByTagName("startdate").item(0);

            // I am not doing any thing with it just for showing you
            String currentStartdate = startdate.getNodeValue();

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date today = Calendar.getInstance().getTime();

            startdate.setTextContent(df.format(today));
*/
            // write the content into XML file
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
