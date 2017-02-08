package info.esblurock.reaction.data.xml;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import org.junit.Test;

import info.esblurock.reaction.data.chemical.respect.ParseReSpecTHXML;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;

public class ParseReferenceString {

	@Test
	public void test() {
		
        try {
	        //File sample = new File("test/resources/x00000001.xml");
	        File sample = new File("test/resources/g00000001_x.xml");
	        FileReader reader = new FileReader(sample);
	        BufferedReader breader = new BufferedReader(reader);
	        String text = read(breader);
	        breader.close();
	        reader.close();

	        System.out.println(text);
	        
	        ParseReSpecTHXML parse = new ParseReSpecTHXML();
	        ReSpecTHXMLFileBase parsed = parse.parse(text);
			System.out.println("Parsed:\n" + parsed.toString());			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	  String read(BufferedReader reader) throws IOException {
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
