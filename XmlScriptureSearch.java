/*****************************************************************
* Hayden Carlson
* This assignment is supposed to parse through an XML document
* and find the topics in each journal entry.
******************************************************************/



import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
 



class XmlScriptureSearch {
   
	public static void main(String args[]){

	}


	class TeachXML {
    
        new TeachXML().run();
    }
    
    //Read the XML file
    public void run() {
        readFile();    
    }
    
    public void readFile() {
        File file = new File("sessionSpeaker.txt");
        try {
            // build the document dom
            DocumentBuilderFactory dom = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dom.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
            
            // Display the XML file
            display(doc);
 
        }
        catch (Exception e) {
            System.out.println("HELP ME!");
        }
    }
    
    public void display(Document doc) {
        // get the sessions tags
        NodeList nodesList = doc.getElementsByTagName("session");
        for (int i = 0; i < nodesList.getLength(); i++) {
            Node singleNode = nodesList.item(i);
            if (singleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) singleNode;
                System.out.println(element.getAttribute("name"));
                System.out.println("-------------------");
                NodeList childNodes = singleNode.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node singleChildNode = childNodes.item(j);
                    if (singleChildNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element childNodeElement = (Element) singleChildNode;
                        System.out.println("\t" + childNodeElement.getAttribute("name"));
                    
                }
                System.out.print("\n");
            }
        }
    }
}


class Finder {
   
		String filename = args[0];
		List<Topic>finderTopics = new ArrayList<Topic>();
		
	   try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		   String line;
			
			//Make a list of the tokens to find
			Topic tTopic;
			
			//Use tokenizer to separate tokens and add them to the "finderTopics" list
			StringTokenizer stk;
			while((line = br.readLine()) != null) {	
                tTopic = new Topic();
				stk = new StringTokenizer(line, ":,");

				tTopic.keyword = stk.nextToken();
				
				while(stk.hasMoreTokens()) {
					tTopic.words.add(stk.nextToken());
				}
				
				finderTopics.add(tTopic);
			
			}
		}

		//Catch any file reading errors
		catch(FileNotFoundException ex) {
			System.out.println("File Not Found");
		}
		catch(IOException ex) {
			System.out.println("Couldn't Read File");
		}


        File scriptures = new File(args[1]);
        
        for(File f : scriptures.listFiles()) {
            boolean[] keyWordsF = new boolean[finderTopics.size()];
            
            //Read through the scriptures using another BufferedReader
            try(BufferedReader br2 = new BufferedReader(new FileReader(f.getPath()))) {
                 String line2;

                 //Read through scriptures
                 	while((line2 = br2.readLine()) != null) {
                 	    String[] words = line2.split(" ");
                 	    for(String w : words){
                 	        for (int j = 0; j < finderTopics.size(); j++) {
                                Topic cTopic = finderTopics.get(j);
            					
            					//Compare the words in the scriptures with the tokens for the "finder" words
                                for (int i = 0; i < cTopic.words.size(); i++) {
                                    if (cTopic.words.get(i).equalsIgnoreCase(w)) {
                                        keyWordsF[j] = true;
                                    }  
                                }
		                   }
                 	    }
                    }
                 //Display the results
                System.out.print(f.getName() + ": ");
                for (int i = 0; i < keyWordsF.length; i++) {
                    if (keyWordsF[i]) {
                        System.out.print(finderTopics.get(i).keyword + " ");
                    }
                }

                System.out.println();
             }

             //Catch any file reading errors
        	catch(FileNotFoundException ex) {
	   		System.out.println("File Not Found");
		   }
		   catch(IOException ex) {
			   System.out.println("Couldn't Read File");
		   }
        }
} 

//Topic class. This defines the "topic" list
class Topic {

	String keyword;
	List<String> words = new ArrayList<String>();
}

public interface Annotation{
	String getDisplayText();
}