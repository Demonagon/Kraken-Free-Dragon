package controller;

import model.BinaryExpression;
import model.KrakenTree;
import model.PrimaryExpression;
import model.UnaryExpression;
import rest.factories.RestServerFactory;
import view.latexConfigurationParser.LatexConfiguration;

/**
 * Main class
 * {@link Application} is a singleton
 * @author Julien Prudhomme
 *
 */
public class Application {
	private static Application instance = null;
	public KrakenTree tree;
	public String latex;
	
	public Application(KrakenTree tree) {
		
		if(instance != null) {
			throw new RuntimeException("Application can be instanciate only once !");
		}
		this.tree = tree;
		this.latex = "$$"+tree.getRoot().generateExpression("")+"$$";
		instance = this;
		run();
	}
	
	public static Application getInstance()	{
		return instance;
	}
	
	private void run() {
		RestServerFactory.createAndStartDefaultRestServer();
	}
	
	public static void main(String[] args) {
		
		PrimaryExpression expr_A = new PrimaryExpression("LITTERAL", "a");
		PrimaryExpression expr_B = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_C = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_D = new PrimaryExpression("NOMBRE", "3");
		
		// ( a/2 ) * 2 = 3
		BinaryExpression divide_A_B = new BinaryExpression("DIVIDE", expr_A.cloneExpression(), expr_B.cloneExpression());
		UnaryExpression parenthese_A_div_B = new UnaryExpression("PARENTHESIS", divide_A_B.cloneExpression());
		BinaryExpression fois_AB_C = new BinaryExpression("FOIS", parenthese_A_div_B.cloneExpression(), expr_C.cloneExpression());
		BinaryExpression egal_ABC_D = new BinaryExpression("EGAL", fois_AB_C.cloneExpression(), expr_D.cloneExpression());
		
		LatexConfiguration config = new LatexConfiguration();	
		
		KrakenTree tree = new KrakenTree(config);

		tree.setRoot(egal_ABC_D);
		
		new Application(tree);
	}
}
