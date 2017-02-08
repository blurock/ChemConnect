package info.esblurock.reaction.data.chemical.respect;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

public class ParseReSpecTHXML {

	public ParseReSpecTHXML() {
	}

	public ReSpecTHXMLFileBase parse(String text) throws IOException {

		ParseReSpecTHXMLHandler handler = new ParseReSpecTHXMLHandler();
		try {
			// Create a "parser factory" for creating SAX parsers
			SAXParserFactory spfac = SAXParserFactory.newInstance();
			// Now use the parser factory to create a SAXParser object
			SAXParser sp = spfac.newSAXParser();
			InputStream in = IOUtils.toInputStream(text, "UTF-8");
			sp.parse(in, handler);
		} catch (ParserConfigurationException | SAXException e) {
			throw new IOException("Error in parsing text");
		} catch (IOException e) {
			throw new IOException("Error in parsing text");
		}
		return handler.getExperimentBase();
	}
}
