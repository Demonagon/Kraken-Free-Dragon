package main;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.BinaryExpression;
import model.Configuration;
import model.Expression;
import model.KrakenTree;
import model.PrimaryExpression;
import model.Rule;
import model.UnaryExpression;
import view.implementation.ChoiceContextMenu;
import view.implementation.ControlTower;
import view.implementation.GraphicExpression;
import view.implementation.graphicConfigurationParser.GraphicConfiguration;

public class Main extends Application {
	
	
	/* 01 : Distributivité */
    	
   	public Expression test_01a_expression() {
    	Expression a = new PrimaryExpression("LITTERAL", "a");
    	Expression b = new PrimaryExpression("LITTERAL", "b");
    	Expression c = new PrimaryExpression("NUMBER", "3");
    	Expression a_plus_b = new BinaryExpression("PLUS", a, b);
    	Expression ab_parenhese = new UnaryExpression("PARENTHESIS", a_plus_b);
    	Expression a_plus_b_fois_c = new BinaryExpression("FOIS", ab_parenhese, c);
    	return a_plus_b_fois_c;
   	}
	
	public Expression test_01b_expression() {
		Expression a = new PrimaryExpression("LITTERAL", "a");
		Expression b = new PrimaryExpression("LITTERAL", "b");
		Expression c = new PrimaryExpression("LITTERAL", "c");
		Expression d = new PrimaryExpression("LITTERAL", "d");
		Expression a_plus_b = new BinaryExpression("PLUS", a, b);
		Expression c_plus_d = new BinaryExpression("PLUS", c, d);
		Expression ab_parenthese = new UnaryExpression("PARENTHESIS", a_plus_b);
		Expression cd_parenthese = new UnaryExpression("PARENTHESIS", c_plus_d);
		Expression a_plus_b_fois_c_plus_d = new BinaryExpression("FOIS", ab_parenthese, cd_parenthese);
		return a_plus_b_fois_c_plus_d;
	}

   	public Rule test_01_rule() {
   		Expression expr_A = new PrimaryExpression("EXPRESSION", "A");
   		Expression expr_B = new PrimaryExpression("EXPRESSION", "B");
   		Expression expr_C = new PrimaryExpression("EXPRESSION", "C");
   		Expression expr_A_plus_B = new BinaryExpression("PLUS", expr_A, expr_B);
   		Expression paren_A_plus_B = new UnaryExpression("PARENTHESIS", expr_A_plus_B);
   		Expression A_plus_B_fois_C = new BinaryExpression("FOIS", paren_A_plus_B, expr_C);
   		Expression input_model = A_plus_B_fois_C;
           
   		Expression A_fois_C = new BinaryExpression("FOIS", expr_A, expr_C);
   		Expression B_fois_C = new BinaryExpression("FOIS", expr_B, expr_C);
   		Expression A_fois_C_plus_B_fois_C = new BinaryExpression("PLUS", A_fois_C, B_fois_C);
   		Expression result_model = A_fois_C_plus_B_fois_C;
           
   		return new Rule(input_model, result_model);
   	}
   	
   	/* 02 : simplification de l'addition */
	
	public Expression test_02_expression() {
    	Expression a_1 = new PrimaryExpression("LITTERAL", "b");
    	Expression a_2 = new PrimaryExpression("LITTERAL", "a");
    	Expression a_plus_a = new BinaryExpression("PLUS", a_1, a_2);
    	return a_plus_a;
	}

	public Rule test_02_rule() {
        Expression expr_A_1 = new PrimaryExpression("EXPRESSION", "A");
        Expression expr_A_2 = new PrimaryExpression("EXPRESSION", "A");
        Expression expr_A_plus_A = new BinaryExpression("PLUS", expr_A_1, expr_A_2);
        Expression input_model = expr_A_plus_A;
        
        Expression expr_A = new PrimaryExpression("EXPRESSION", "A");
        Expression expr_1_a = new PrimaryExpression("NUMBER", "1");
        Expression expr_1_b = new PrimaryExpression("NUMBER", "1");
        Expression expr_1_plus_1 = new BinaryExpression("PLUS", expr_1_a, expr_1_b);
        Expression parenthesis = new UnaryExpression("PARENTHESIS", expr_1_plus_1);
        Expression A_fois_2 = new BinaryExpression("FOIS", expr_A, parenthesis);
        Expression result_model = A_fois_2;
        
        return new Rule(input_model, result_model);
	}
    
    
	public static void mainTestRules(String[] args) {
    	Configuration.init(new GraphicConfiguration());
        launch(args);
	}
	
	static List<Rule> applicInput(String input, List<Expression> expressions) {
		System.out.println("les régles possibles é l'input " + input + " sur " + expressions + " sont :");
		List<Rule> rules = KrakenTree.processInput(input, expressions).second;
		System.out.println( rules );
		return rules;
	}
	
	static List<Rule> applicInput(String input, Expression expression) {
		System.out.println("les régles possibles é l'input " + input + " sur " + expression.expressionToString() + " sont :");
		List<Rule> rules = KrakenTree.processInput(input, expression).second;
		System.out.println( rules );
		return rules;
	}
	
	public static void mainTestRulesDeductionMinigame(String[] args) {
		Configuration.init(new GraphicConfiguration());
		
		
		Expression expr_A = new PrimaryExpression("EXPRESSION", "A");
		Expression expr_B = new PrimaryExpression("EXPRESSION", "B");
		Expression zero = new PrimaryExpression("ZERO", "0");
		Expression plus_AB = new BinaryExpression("PLUS", expr_A.cloneExpression(), expr_B.cloneExpression());
		Expression plus_BA = new BinaryExpression("PLUS", expr_B.cloneExpression(), expr_A.cloneExpression());
		Expression plus_A0 = new BinaryExpression("PLUS", expr_A.cloneExpression(), zero.cloneExpression());

		// A + B 	=(drag_and_drop)=> 		B + A
		// A + 0 	=(clic_gauche)=> 		A
		// A 		=(double_clic_gauche)=> A + 0
		Configuration.rules.addRule("drag_and_drop", new Rule(plus_AB.cloneExpression(), plus_BA.cloneExpression()));
		Configuration.rules.addRule("clic_gauche", new Rule(plus_A0.cloneExpression(), expr_A.cloneExpression()));
		Configuration.rules.addRule("double_clic_gauche", new Rule(expr_A.cloneExpression(), plus_A0.cloneExpression()));
		
		BinaryExpression expression = new BinaryExpression("PLUS",
										new PrimaryExpression("NUMBER", "1"), 
										zero.cloneExpression());
		
		int input = -1;
		Scanner scanner = new Scanner(System.in);
		
		while(input != 4) {
			input = -1;
			do {
				System.out.println("La formule est :");
				System.out.println(expression);
				System.out.println("Quel opération appliquer sur la racine :");
				System.out.println("1 : drag_and_drop");
				System.out.println("2 : clic_gauche");
				System.out.println("3 : double_clic_gauche");
				input = scanner.nextInt();
			} while (input <= 0 || input > 3);
			
			
		}
		
		scanner.close();
		
		List<Expression> input_targets = new LinkedList<Expression>();
		input_targets.add(expression.secondExpression());
		input_targets.add(zero);
		
		List<Rule> rules = applicInput("drag_and_drop", expression);
		
		if( rules.size() > 0 ) {
			Rule rule = rules.get(0);
			
			System.out.println("L'application de la premiére régle est :");
			System.out.println( rule.applic(expression) );
		}
	}
	
	public static void mainTestRulesDeduction(String[] args) {
		Configuration.init(new GraphicConfiguration());
		
		
		Expression expr_A = new PrimaryExpression("EXPRESSION", "A");
		Expression expr_B = new PrimaryExpression("EXPRESSION", "B");
		Expression zero = new PrimaryExpression("ZERO", "0");
		Expression plus_AB = new BinaryExpression("PLUS", expr_A.cloneExpression(), expr_B.cloneExpression());
		Expression plus_BA = new BinaryExpression("PLUS", expr_B.cloneExpression(), expr_A.cloneExpression());
		Expression plus_A0 = new BinaryExpression("PLUS", expr_A.cloneExpression(), zero.cloneExpression());

		// A + B 	=(drag_and_drop)=> 		B + A
		// A + 0 	=(clic_gauche)=> 		A
		// A 		=(double_clic_gauche)=> A + 0
		Configuration.rules.addRule("drag_and_drop", new Rule(plus_AB.cloneExpression(), plus_BA.cloneExpression()));
		Configuration.rules.addRule("clic_gauche", new Rule(plus_A0.cloneExpression(), expr_A.cloneExpression()));
		Configuration.rules.addRule("double_clic_gauche", new Rule(expr_A.cloneExpression(), plus_A0.cloneExpression()));
		
		BinaryExpression expression = new BinaryExpression("PLUS",
										new PrimaryExpression("NUMBER", "1"), 
											new BinaryExpression("PLUS",
												new PrimaryExpression("NUMBER", "2"),
													new BinaryExpression("PLUS",
														new PrimaryExpression("NUMBER", "3"), zero)));
		
		List<Expression> input_targets = new LinkedList<Expression>();
		input_targets.add(expression.secondExpression());
		input_targets.add(zero);
		
		List<Rule> rules = applicInput("drag_and_drop", expression);
		
		if( rules.size() > 0 ) {
			Rule rule = rules.get(0);
			
			System.out.println("L'application de la premiére régle est :");
			System.out.println( rule.applic(expression) );
		}
	}
	
	public static void mainTestContextMenu(String[] args) {
	}
    
	@Override
    public void start(Stage primaryStage) {

        KrakenTree tree = new KrakenTree(new GraphicConfiguration());
    	// test
        StackPane center = new StackPane();
    	BorderPane root = new BorderPane();

		Expression expr_A = new PrimaryExpression("EXPRESSION", "A");
		Expression expr_B = new PrimaryExpression("EXPRESSION", "B");
		Expression zero = new PrimaryExpression("ZERO", "0");
		Expression plus_AB = new BinaryExpression("PLUS", expr_A.cloneExpression(), expr_B.cloneExpression());
		Expression plus_BA = new BinaryExpression("PLUS", expr_B.cloneExpression(), expr_A.cloneExpression());
		Expression plus_A0 = new BinaryExpression("PLUS", expr_A.cloneExpression(), zero.cloneExpression());

		// A + B 	=(drag_and_drop)=> 		B + A
		// A + 0 	=(clic_gauche)=> 		A
		// A 		=(double_clic_gauche)=> A + 0
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		rules.add(new Rule(plus_AB.cloneExpression(), plus_BA.cloneExpression()));
		rules.add(new Rule(plus_A0.cloneExpression(), expr_A.cloneExpression()));
		rules.add(new Rule(expr_A.cloneExpression() , plus_A0.cloneExpression()));
    	
    	Expression expression = plus_A0.cloneExpression();
    	tree.setRoot(expression);
    	
    	// ajout des noeuds dans l'arbre du group
    	center.getChildren().add( (GraphicExpression) expression.generateExpression() );
    	root.setCenter(center);
    	
    	Scene scene = new Scene(root, 750, 500, Color.LIGHTGRAY);
    	
        //definition de la fenetre d'affichage
        primaryStage.setTitle("Free Kraken");
        primaryStage.setScene(scene);
        primaryStage.show();
		
		// ContextMenu
    	final ContextMenu contextMenu = new ChoiceContextMenu(rules, expression, tree, center);
    	
    	contextMenu.show(primaryStage);
    }
    
    // sert juste a lancer l'application
    public static void main(String[] args) {
        launch(args);
    }
}