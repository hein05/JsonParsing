import java.io.FileWriter;
import java.io.IOException;


public class IO {
	 FileWriter fr;
	  public IO (String outputFileName) {
	    try {
	      fr = new FileWriter(outputFileName);
	    } catch (IOException e) {
	      System.out.println("File IO Error");
	      e.printStackTrace();
	    }    
	  }

	  public void OutputTitle(String str) throws IOException {
	    fr.append(str);
	  }
	  
	  public void LogTitle() throws IOException {
		    fr.append("_________ List of Pharsing Actions ___________\n\n");
	  }

	  public void append(String s) throws IOException {
	    fr.append(s);
	  }

	  public void close() throws IOException {
	    fr.append("__________ End of List ____________");
	    fr.flush();
	    fr.close();
	  }
	  
	  public void lineClose () throws IOException {
		  fr.flush();
		  fr.close();
	  }
}
