package info.esblurock.reaction.server.parse.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BibliographicLinkSAXHandler extends DefaultHandler {
	String bibliographicLinkS = "bibliographyLink";
	String preferredKeyS = "preferredKey";
	
	String reference;
	
    public static void main(String[] args) throws IOException, SAXException,
    ParserConfigurationException {
    	//Create a "parser factory" for creating SAX parsers
        SAXParserFactory spfac = SAXParserFactory.newInstance();
        //Now use the parser factory to create a SAXParser object
        SAXParser sp = spfac.newSAXParser();
    }

    String temp;
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
     if (qName.equalsIgnoreCase(bibliographicLinkS)) {
            reference = attributes.getValue(preferredKeyS);
     }
}
	public String getReference() {
		return reference;
	}


}
