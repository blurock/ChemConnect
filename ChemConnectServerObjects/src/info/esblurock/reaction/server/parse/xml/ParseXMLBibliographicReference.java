package info.esblurock.reaction.server.parse.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

public class ParseXMLBibliographicReference {
	
	
	

	public ParseXMLBibliographicReference() {
		
	}
	
	public String parse(String text) throws IOException {
		BibliographicLinkSAXHandler handler = new BibliographicLinkSAXHandler();
		try {
    	//Create a "parser factory" for creating SAX parsers
        SAXParserFactory spfac = SAXParserFactory.newInstance();

        //Now use the parser factory to create a SAXParser object
				SAXParser sp = spfac.newSAXParser();
				InputStream in = IOUtils.toInputStream(text, "UTF-8");
				sp.parse(in,handler);
				
			} catch (ParserConfigurationException | SAXException e) {
				throw new IOException("Error in parsing text");
			}
			

			
			return handler.getReference();
	}
	
}
