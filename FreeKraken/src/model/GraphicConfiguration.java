package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.graphicConfigurationParser.GraphicConfigurationParser;
import model.graphicConfigurationParser.GraphicExpressionConfiguration;
import model.graphicConfigurationParser.ParseException;
import javafx.scene.shape.Shape;

public class GraphicConfiguration {
	
	public static final String config_file_path = "bin/essais.gconfig"; 
	
	private Map<String, GraphicExpressionConfiguration > configurations;

	public GraphicConfiguration() {
		configurations = new HashMap<String, GraphicExpressionConfiguration >();
	}
	
	public void addConfiguration(String string, GraphicExpressionConfiguration value) {
		configurations.put(string, value);
	}
	
	public GraphicExpressionConfiguration getConfiguration(String string) {
		return configurations.get(string);
	}
	
	public void init() {
		File config_file = new File(config_file_path);
		
		try {
			GraphicConfigurationParser.read(config_file);
		} catch (ParseException e) {
			System.out.println("Erreur. Le fichier de configuration contient une erreur syntaxique.");
			e.printStackTrace();
		}
		
		System.out.println(configurations.size() + " configuration(s) graphique(s) ont été chargées.");
	}

}
