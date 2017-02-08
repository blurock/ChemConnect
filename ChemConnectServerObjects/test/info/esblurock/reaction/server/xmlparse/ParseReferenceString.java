package info.esblurock.reaction.server.xmlparse;

import static org.junit.Assert.*;

import org.apache.commons.io.IOUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

import info.esblurock.reaction.server.parse.xml.ParseXMLBibliographicReference;


public class ParseReferenceString {

	@Test
	public void test() {
		
        try {
	        File sample = new File("test/resources/x00000001.xml");
	        FileReader reader = new FileReader(sample);
	        BufferedReader breader = new BufferedReader(reader);
	        String text = read(breader);
	        breader.close();
	        reader.close();

	        System.out.println(text);
	        
	        ParseXMLBibliographicReference parse = new ParseXMLBibliographicReference();
			
			
			System.out.println("Reference: " + parse.parse(text));
			
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
