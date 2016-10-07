/*************************************************************************
* Hayden Carlson 
* Assignment: Collections and Interfaces
* This program will take two files. One with words that identify a topic
* and one with big text files. The program will go through big text files 
# and present the topics in each big text file
*************************************************************************/

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.File;

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
