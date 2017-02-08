package info.esblurock.reaction.server.parse.xml;

import java.io.IOException;

import info.esblurock.reaction.client.async.ProcessReSpecTh;
import info.esblurock.reaction.data.chemical.respect.ParseReSpecTHXML;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.server.ServerBase;

public class ProcessReSpecThImpl extends ServerBase implements ProcessReSpecTh {

	public ReSpecTHXMLFileBase parseReSpecThXML(String xml) throws IOException {
        ParseReSpecTHXML parse = new ParseReSpecTHXML();
        ReSpecTHXMLFileBase parsed = parse.parse(xml);
        return parsed;
	}
}
