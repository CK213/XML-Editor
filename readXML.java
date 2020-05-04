	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */
	import java.io.*;
	import javax.xml.parsers.*;
	import org.w3c.dom.*;
	/**
	 *
	 * @author cherrenku
	 */
	public class readXML {

	private static String getTagValue(String sTag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    return nValue.getNodeValue();
	}

	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    File is = new File(xml);
	    return builder.parse(is);
	}
	    
	  public static void read() throws Exception {
	        Document document;
	        String fileName = "C:\\\\\\\\Users\\\\\\\\cherrenku\\\\\\\\Documents\\\\\\\\AST-year1_semA-notes\\\\\\\\Java-groupproject\\\\\\\\AST10106_ProjectSampleData.xml";
	        document = loadXMLFromString(fileName);

	        //re edit
	        //first get the element from column 
	        NodeList ColumnNodes = document.getElementsByTagName("columns");
	        NodeList A = ColumnNodes.item(0).getChildNodes();
	        Node temptest = ColumnNodes.item(0).getFirstChild();
	        int nodeindex = (A.getLength()/2);
	        System.out.println(nodeindex);
	        String [] ColName = new String[nodeindex];

	        for (int i = 1; i < nodeindex; i++) {
	           temptest = temptest.getNextSibling();
	           ColName [i] = temptest.getNodeName();
	            //test the element Name
	            System.out.println(ColName[i]);
	            temptest = temptest.getNextSibling();
	        }

	        NodeList DataNodeList = document.getElementsByTagName("record");
	        int NumOfRec = DataNodeList.getLength();
	        String[][] DataRec = new String[NumOfRec][nodeindex];
	        System.out.println(NumOfRec);

	        for(int K = 0;K < NumOfRec ; K++){
	            
	            System.out.println("Record "+ (K+1) );
	            NodeList b = DataNodeList.item(K).getChildNodes();
	            Node tempB = DataNodeList.item(K).getFirstChild();

	            for (int H = 1; H < nodeindex; H++) {
	                tempB = tempB.getNextSibling();
	                DataRec [K][H] = tempB.getTextContent();
	                 //test the element Name
	                System.out.println(ColName[H] + " : " + DataRec [K][H]);
	                tempB = tempB.getNextSibling();
	            }
	            System.out.println();

	        }

	      
	        // This for Reference 
	        Node temptest1 = ColumnNodes.item(0).getFirstChild().getNextSibling().getNextSibling().getNextSibling();
	        System.out.println(temptest1.getNodeName() + " 0a0 " + temptest1.getNodeValue() + " 0a0 " + temptest1.getTextContent());
	        //then print line 

	}
	}  
