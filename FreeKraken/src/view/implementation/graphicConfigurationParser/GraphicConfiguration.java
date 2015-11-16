package view.implementation.graphicConfigurationParser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.Expression;
import model.Rule;
import view.GraphicExpressionFactory;
import view.implementation.BinaryGraphicExpression;
import view.implementation.ControlTower;
import view.implementation.GraphicExpression;
import view.implementation.PrimaryGraphicExpression;
import view.implementation.StringGraphicOperator;

public class GraphicConfiguration implements GraphicExpressionFactory {
	
	public static final String config_file_path = "bin/essais.gconfig";
	
	private Map<String, GraphicExpressionConfiguration > configurations;
	private ControlTower tower;

	public GraphicConfiguration(ControlTower tower) {
		configurations = new HashMap<String, GraphicExpressionConfiguration >();
		this.tower = tower;
	}
	
	public void addConfiguration(String string, GraphicExpressionConfiguration value) {
		configurations.put(string, value);
	}
	
	public GraphicExpressionConfiguration getConfiguration(String string) {
		return configurations.get(string);
	}
	
	@Override
	public Object generateBinaryExpression(Expression expression, String type, Object first, Object second) {
		return getConfiguration(type).generateBinaryExpression(expression, (GraphicExpression) first, (GraphicExpression) second);
	}

	@Override
	public Object generateUnaryExpression(Expression expression, String type, Object sub) {
		return getConfiguration(type).generateUnaryExpression(expression, (GraphicExpression) sub);
	}

	@Override
	public Object generatePrimaryExpression(Expression expression, String type, String name) {
		PrimaryGraphicExpression graphicExpression = new PrimaryGraphicExpression(expression, tower);
		graphicExpression.setExpression(name);
		return graphicExpression;
	}
	
	@Override
	public void init() {
		File config_file = new File(config_file_path);
		
		try {
			GraphicConfigurationParser.read(config_file, tower);
		} catch (ParseException e) {
			System.out.println("Erreur. Le fichier de configuration contient une erreur syntaxique.");
			e.printStackTrace();
		}
		
		System.out.println(configurations.size() + " configuration(s) graphique(s) ont �t� charg�es.");
	}

	@Override
	public Object generateRuleExpression(Rule rule) {
		return new BinaryGraphicExpression(null,
								(GraphicExpression) rule.getInputModel().generateExpression(),
								(GraphicExpression) rule.getResultModel().generateExpression(),
								new StringGraphicOperator("=>"),
								BinaryGraphicExpression.Orientation.HORIZONTAL, tower);
	}

}