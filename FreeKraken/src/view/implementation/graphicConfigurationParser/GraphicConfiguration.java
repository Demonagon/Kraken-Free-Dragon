package view.implementation.graphicConfigurationParser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.BinaryExpression;
import model.Expression;
import model.PrimaryExpression;
import model.Rule;
import model.UnaryExpression;
import view.GraphicExpressionFactory;
import view.implementation.BinaryGraphicExpression;
import view.implementation.ControlTower;
import view.implementation.GraphicExpression;
import view.implementation.OperatorDuplicator;
import view.implementation.PrimaryGraphicExpression;
import view.implementation.StringGraphicOperator;
import view.implementation.UnaryGraphicExpression;

public class GraphicConfiguration implements GraphicExpressionFactory {
	
	public static final String config_file_path = "bin/essais.gconfig";
	
	private Map<String, GraphicExpressionConfiguration > configurations;
	private ControlTower tower;

	public GraphicConfiguration() {
		configurations = new HashMap<String, GraphicExpressionConfiguration >();
	}

	public void setControlTower (ControlTower tower){
		this.tower = tower;
		for(GraphicExpressionConfiguration conf : configurations.values() ) {
			conf.setControlTower(tower);
		}
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
		
		System.out.println(configurations.size() + " configuration(s) graphique(s) ont été chargées.");
	}

	@Override
	public Object generateRuleExpression(Rule rule) {
		return new BinaryGraphicExpression(null,
								generateStaticExpression(rule.getInputModel()),
								generateStaticExpression(rule.getResultModel()),
								new StringGraphicOperator("=>"),
								BinaryGraphicExpression.Orientation.HORIZONTAL, tower, false);
	}
	
	public GraphicExpression generateStaticExpression(Expression expression) {
		if( expression instanceof UnaryExpression ) {
			UnaryExpression uexpression = (UnaryExpression) expression;
			return generateStaticUnaryExpression(expression, uexpression.getType(), generateStaticExpression(uexpression.subExpression()) );
		}
		if( expression instanceof BinaryExpression ) {
			BinaryExpression bexpression = (BinaryExpression) expression;
			return generateStaticBinaryExpression(expression, bexpression.getType(),
												  generateStaticExpression(bexpression.firstExpression()),
												  generateStaticExpression(bexpression.secondExpression()));
			
		}
		else {
			PrimaryExpression pexpression = (PrimaryExpression) expression;
			return generateStaticPrimaryExpression(expression, pexpression.getType(), pexpression.getName());
		}
	}
	
	public GraphicExpression generateStaticPrimaryExpression(Expression expression, String type, String name) {
		PrimaryGraphicExpression graphicExpression = new PrimaryGraphicExpression(expression, tower, false);
		graphicExpression.setExpression(name);
		return graphicExpression;
	}
	
	public GraphicExpression generateStaticBinaryExpression(Expression expression, String type, Object first, Object second) {
		return getConfiguration(type).generateBinaryExpression(expression, (GraphicExpression) first, (GraphicExpression) second);
	}

	public GraphicExpression generateStaticUnaryExpression(Expression expression, String type, Object sub) {
		return getConfiguration(type).generateUnaryExpression(expression, (GraphicExpression) sub);
	}

}