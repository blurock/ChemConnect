package info.esblurock.reaction.server.initialize;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import org.junit.Test;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import info.esblurock.reaction.data.initialization.InitializationFile;
import info.esblurock.reaction.server.initialization.InitializeOrganizationsYaml;

public class ReadOrganizationYaml {

	@Test
	public void test() {
		System.out.println("Test");
		String fileS = "resources/contact/OrganizationInitialization.yaml";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileS);
		if(in != null) {
		InitializationFile filetowrite = new InitializationFile(fileS);
		
		Reader targetReader = new InputStreamReader(in);
		YamlReader reader = new YamlReader(targetReader);
		Map map;
			Object object;
			try {
				object = reader.read();
			map = (Map) object;
		InitializeOrganizationsYaml yaml = new InitializeOrganizationsYaml();
		yaml.interpret(map);
			} catch (YamlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}else {
		System.out.println("couldn't finde file");
	}
	} 
}
