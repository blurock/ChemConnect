package info.esblurock.reaction.server.xmlparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import info.esblurock.reaction.server.parse.xml.ParseXMLBibliographicReference;
import info.esblurock.reaction.server.parse.xml.ProcessReSpecThImpl;


public class ParseReferenceString {

	@Test
	public void test() {
		
        try {
	        File sample = new File("test/resources/x00003001.xml");
	        FileReader reader = new FileReader(sample);
	        BufferedReader breader = new BufferedReader(reader);
	        String text = read(breader);
	        breader.close();
	        reader.close();

	        System.out.println(text);
	        
	        ParseXMLBibliographicReference parse = new ParseXMLBibliographicReference();
			
			
			System.out.println("Reference: " + parse.parse(text));
			
			ProcessReSpecThImpl fullparse = new ProcessReSpecThImpl();
			System.out.println("Full Parse:\n " + fullparse.parseReSpecThXML(text));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	  String read(BufferedReader reader) throws IOException {
		  String outputString;
		     StringBuilder tLine         = new StringBuilder();
		           String line = reader.readLine();
		           while(line != null) {
		               tLine.append(line);
		               tLine.append("\n");
		            line = reader.readLine();
		           }
		        return tLine.toString();
		  }

}
