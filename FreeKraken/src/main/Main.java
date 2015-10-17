package main;


import model.BinaryExpression;
import model.PrimaryExpression;
import view.BinaryGraphicExpression;
import view.BinaryGraphicExpression.Orientation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

public class Main extends Application {
    
	@Override
    public void start(Stage primaryStage) {
    	// test
        StackPane center = new StackPane();
    	BorderPane root = new BorderPane();
    	
    	Shape plus = new Text("+");
    	Shape fois = new Text("x");
    	
    	PrimaryExpression a = new PrimaryExpression("LITTERAL", "a");
    	PrimaryExpression b = new PrimaryExpression("LITTERAL", "b");;
    	PrimaryExpression c = new PrimaryExpression("LITTERAL", "c");
    	
    	BinaryExpression fois_1 = new BinaryExpression(a, b, fois, BinaryGraphicExpression.Orientation.HORIZONTAL);
    	BinaryExpression plus_1 = new BinaryExpression(fois_1, c, plus, BinaryGraphicExpression.Orientation.HORIZONTAL);
    	
    	
    	// ajout des noeuds dans l'arbre du group
    	center.getChildren().add( plus_1.generateExpression() );
    	root.setCenter(center);
    	
    	Scene scene = new Scene(root, 750, 500, Color.LIGHTGRAY);
    	
        //definition de la fenetre d'affichage
        primaryStage.setTitle("Free Kraken");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    // sert juste a lancer l'application
    public static void main(String[] args) {
        launch(args);
    }
}