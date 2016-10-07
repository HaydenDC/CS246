import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
 
 
class TeachXML {
    public static void main(String[] args) {
        new TeachXML().run();
    }
    
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
            
            
            // 
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
                }
                System.out.print("\n");
            }
        }
    }
}