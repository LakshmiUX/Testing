

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class FactList{
	

	private ArrayList<Fact> factList;
	private Random randomGen;

	public FactList()
	{
		this.factList = new ArrayList<Fact>();
		randomGen = new Random (System.currentTimeMillis());
	}

	public Fact set(Fact f)
	{
		factList.add(f);
		return f;
	}

	public int getSize(){
		return factList.size();
	}

	public Fact get(int i){
		return (Fact) factList.get (i);
	}

    public FactList search (String searchString, int mode)
	{
	FactList fl = new FactList();
	for (int i = 0; i < factList.size(); i++)
	{
	Fact fact = factList.get(i);
	if (mode == FactSearchMode.AUTHOR_VAL && 
	fact.getAuthor().toLowerCase().indexOf(searchString.toLowerCase()) != -1){  
	fl.set(fact);
	} else if (mode == FactSearchMode.TEXT_VAL && 
		fact.getFactText().toLowerCase().indexOf(searchString.toLowerCase()) != -1){  
	fl.set(fact);
	} else if (mode == FactSearchMode.TYPE_VAL && 
	fact.getFactType().toLowerCase().indexOf(searchString.toLowerCase()) != -1){  
	fl.set(fact);
	} else if ((mode == FactSearchMode.ALL_VAL) &&
	(fact.getAuthor().toLowerCase().indexOf (searchString.toLowerCase()) != -1  ||
	fact.getFactText().toLowerCase().indexOf (searchString.toLowerCase()) != -1 ||
		fact.getFactType().toLowerCase().indexOf (searchString.toLowerCase()) != -1)){  
	fl.set(fact);
	}
	}
	System.out.println(fl); 
	return fl;
	}
			

	public Fact getRandom(){
		return factList.get(randomGen.nextInt(factList.size()));
	}


	private static Fact getfact(Node node) {
        // XMLReaderDOM domReader = new XMLReaderDOM();
        Fact fact = new Fact();{
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            
            fact.setFactText(getTagValue("fact-text", element));
            fact.setAuthor(getTagValue("author", element));
            fact.setFactType(getTagValue("fact-type", element));
            
        }
        return fact;}
    }
	
    private static String getTagValue(String tag, Element element) 
    {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);{
        return node.getNodeValue();}
    }
    
    public static void main(String[] args) throws TransformerException {
        String filePath = "C:\\Users\\Lakshmi\\UTD-COURSES\\SE4367-Testing\\facts(1)\\facts\\data\\facts.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            System.out.println("Please enter a fact: ");
    		Scanner a = new Scanner(System.in);
    		String b = a.nextLine(); 
    		if(b.length() == 0)
    		{
    			System.out.println("Please enter a fact to be added.");
    			System.exit(0);
    		}
    		if(b.length() > 100)
    		{
    			System.out.println("Your response exceeds the limit of 100 characters. Please enter a valid input.");
    		}
            Element documentElement = doc.getDocumentElement();
            Element bb = doc.createElement("fact-text");
            bb.setTextContent(b);
            
            System.out.println("Please enter the name of the author: ");
    		Scanner c = new Scanner(System.in);
    		String d = a.nextLine();
            Element dd = doc.createElement("author");
            dd.setTextContent(d);
            
        	System.out.println("Please enter the fact type: ");
    		Scanner e = new Scanner(System.in);
    		String f = a.nextLine();
            Element ee = doc.createElement("fact-type");
            ee.setTextContent(f);
            
            Element nodeElement = doc.createElement("fact");
            nodeElement.appendChild(bb);
            nodeElement.appendChild(dd);
            nodeElement.appendChild(ee);
            
            documentElement.appendChild(nodeElement);
            doc.replaceChild(documentElement, documentElement);
           
            
            System.out.println("These are the contents of the file:"); 
            System.out.println("Keyword: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("fact");
            
            
         
            
            // now XML is loaded as Document in memory, lets convert it to Object List
            List < Fact > factList = new ArrayList < Fact > ();

            for (int i = 0; i < nodeList.getLength(); i++) {
                factList.add(getfact(nodeList.item(i)));
            }
            
            for (Fact emp: factList) {
                System.out.println(emp.toString());
            }
            
            
            System.out.println("\n1. Enter 1 if you wish to perform a new search.\n2. Enter 2 if you wish to exit.\n3. Enter 3 if you wish to see your search history"); 
            Scanner pp = new Scanner(System.in);
    		int mm = pp.nextInt();
    		
    		
            if(mm == 1)
            {
              
                NodeList nList = doc.getElementsByTagName("fact");
                for (int temp = 0; temp < nList.getLength(); temp++) 
                {

                    Node nNode = nList.item(temp);
                    System.out.println("Please enter a keyword to search: "); 
                	Scanner jj = new Scanner(System.in);
              		String ll = jj.nextLine();
              		System.out.println("Please pick one of the following choices: \n1: Search by text \n2: Search by author \n3: Search by type\n4: Search by random"); 
              		Scanner ww = new Scanner(System.in);
              		int oo = ww.nextInt();
              		Fact obj = new Fact();
          			FactList objTwo = new FactList();
              		if(oo == 1)
                	{
              			
              			System.out.println(objTwo.search(b, 1)); 		
            			System.out.println("The program has finished running successfully.");
            			System.exit(0);
        					
        			}
              		else if(oo == 2)
                  	{
              			
              			System.out.print(objTwo.search(b, 2)); 		
            			//System.out.println("Search Result: " + obj.getAuthor()); 	
            			System.out.println("The program has finished running successfully.");
            			System.exit(0);
                  	}
              		else if(oo == 3)
                  	{
              			
              			System.out.print(objTwo.search(b, 3)); 		
            			//System.out.println("Search Result: " + obj.getFactType()); 
            			System.out.println("The program has finished running successfully.");
            			System.exit(0);
                  	}
              		else if(oo == 4)
                  	{
              			
              			System.out.print(objTwo.search(b, 4)); 		
            			//System.out.println("Search Result: " + obj.getRandom()); 
            			System.out.println("The program has finished running successfully.");
            			System.exit(0);
                  	}

       
             break; 
                }
            }
            
           else if(mm == 2)
            {
            	System.out.println("You have exited the program successfully."); 
            	System.exit(0);
            }
            else if(mm ==3)
            {
            	System.out.println("This is your search history: "); 
    			FactList objTwo = new FactList();
      			System.out.println(objTwo.search(b, 1)); 
      			System.out.println(objTwo.search(b, 2)); 
      			System.out.println(objTwo.search(b, 3)); 
            	System.out.println("The search history was successfully printed.");
    			//Print search history
            	ArrayList<String> searchList = new ArrayList(); 
    			String searchTmp = "";
    	
    			for (int i = 0; i < searchList.size(); i++)
    			{  
    			searchTmp = searchList.get(i);
    			System.out.println(searchTmp + " "); 
            	
            }
            
            
    		
            } 
        }
            
           catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    

}

}
