import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

// all file reading related methods here.
  // It is very important to balance the methods between here and the main class.


public class CMD {
	
	FileReader fr;
	FileWriter fw;
	
	public void readFile(String file) {
		   try {
		     fr = new FileReader(file);
		     BufferedReader br = new BufferedReader(fr);
		     String line = br.readLine();
		     while (line != null) {
		        System.out.println(line);
		        line = br.readLine();
		    }
		      System.out.println("Routine Complete");
		    } catch (IOException e) {
		      System.out.println("Error Reading File");
		    }
	}
	
	
	public void writeFile (String file) {
		try {
			fw = new FileWriter (file);
		} catch(IOException e) {
			System.out.println("File IO Error");
		}
	}
	
	static final String PARAS_STRING = "{}";
	static final String response = "commands";
	
	BufferedReader br;
	  
	  public CMD(String fileName) {	
	    try {
	      br = new BufferedReader(new FileReader(fileName));
	    } catch (IOException e) {
	      System.out.println("Error Opening File");
	    }
	    try {
	      char head = (char) br.read(); //reads the first char
	      if (head != '{') {
	        System.out.println("Unexpected Start of JSON");
	      }
	    } catch (IOException e) {
	      System.out.println("Error Reading File");
	    }        
	  }

	  public String getNextCommand() {
	    try {
	      String current = "";
	      boolean open = false;
	      char c = (char) br.read();
	      while (c > 0) {
//	    	  System.out.println("c is >>>> " + c);
	        if (c == '{') {
	          current = "";
	          open = true;
	        } else if (c == '}') {
	          if (open) return current;
	          else return null;
	        } else {
	          current += c;
	        }
	        c = (char) br.read();
	      }
	    } catch (IOException e) {
	      System.out.println("Error Reading File");
	    }
	    return null;
	  }
	  
	  public String getWord (String str) {
		  try {
			  String current = str;
			  boolean wordEnded = false;
			  while (!wordEnded) {
				  
			  }
			  char c = (char) br.read();
			  while (c > 0) {
				  if (c == '"') {
					  current = "";
					  wordEnded = false;
				  } else if (c == '"' && current.charAt(0) == '"') {
					  wordEnded = true;
					  return current;
				  } else {
					  current += c;
					  System.out.println(current);
				  }
				  c = (char) br.read();
			  }
		  } catch (IOException e) {
			  System.out.println("Error Reading File");
		  }
		  return null;
	  }
}
