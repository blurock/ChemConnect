package info.esblurock.reaction.server.initialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import info.esblurock.reaction.data.rdf.KeywordRDF;
import info.esblurock.reaction.data.store.StoreObject;

public class InitializeRDFYaml extends YamlFileInterpreterBase {
	@SuppressWarnings("rawtypes")
	public void interpret( Map map) {
		System.out.println("InitializeRDFYaml");
		StoreObject store = new StoreObject("Administration","", "0");
		HashMap rdfmap = (HashMap) map.get("RDF");
		Set<String> keys = rdfmap.keySet();
		for(String predicateS : keys) {
			Object obj = rdfmap.get(predicateS);
			if(obj != null) {
				ArrayList lst = (ArrayList) obj;
				for(Object o : lst) {
					HashMap valuemap = (HashMap) o;
					Set<String> subjectkeys = valuemap.keySet();
					for(String subjectS: subjectkeys) {
						String objectS = (String) valuemap.get(subjectS);
						System.out.println("S: " + subjectS + "\t P: " + predicateS + "\t O: " + objectS);
						store.setKeyword(subjectS);
						store.storeStringRDF(predicateS, objectS);
					}
				}				
			} else {
				System.out.println("null: " + predicateS);
			}
		}
		store.finish();
	}

}
