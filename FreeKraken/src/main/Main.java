package main;


import model.BinaryExpression;
import model.Configuration;
import model.Expression;
import model.PrimaryExpression;
import model.Rule;
import model.UnaryExpression;
import view.BinaryGraphicExpression;
import view.StringGraphicOperator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

public class Main extends Application {
	
	
	/* 01 : Distributivité */
    	
   	public Expression test_01_expression() {
    	Expression a = new PrimaryExpression("LITTERAL", "a");
    	Expression b = new PrimaryExpression("LITTERAL", "b");
    	Expression c = new PrimaryExpression("NUMBER", "3");
    	Expression a_plus_b = new BinaryExpression("PLUS", a, b);
    	Expression ab_parenhese = new UnaryExpression("PARENTHESE", a_plus_b);
    	Expression a_plus_b_fois_c = new BinaryExpression("FOIS", ab_parenhese, c);
    	return a_plus_b_fois_c;
   	}

   	public Rule test_01_rule() {
   		Expression expr_A = new PrimaryExpression("EXPRESSION", "A");
   		Expression expr_B = new PrimaryExpression("EXPRESSION", "B");
   		Expression expr_C = new PrimaryExpression("EXPRESSION", "C");
   		Expression expr_A_plus_B = new BinaryExpression("PLUS", expr_A, expr_B);
   		Expression paren_A_plus_B = new UnaryExpression("PARENTHESE", expr_A_plus_B);
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
    
	@Override
    public void start(Stage primaryStage) {
    	// test
        StackPane center = new StackPane();
    	BorderPane root = new BorderPane();
    	
    	Expression expression = test_01_expression();
    	Rule rule = test_01_rule();
        
        System.out.println("Application de la règle");
        System.out.println(rule);
        System.out.println("à la formule");
        System.out.println(expression.expressionToString());
        
        try {
        	expression = rule.applic(expression);
        	System.out.println("Ce qui donne");
        	System.out.println(expression.expressionToString());
        }
        catch(IllegalArgumentException e) {
        	System.out.println("Formule incompatible.");
        }
    	
    	// ajout des noeuds dans l'arbre du group
    	center.getChildren().add( expression.generateExpression() );
    	root.setCenter(center);
    	
    	Scene scene = new Scene(root, 750, 500, Color.LIGHTGRAY);
    	
        //definition de la fenetre d'affichage
        primaryStage.setTitle("Free Kraken");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    // sert juste a lancer l'application
    public static void main(String[] args) {
    	Configuration.init();
        launch(args);
    }
}