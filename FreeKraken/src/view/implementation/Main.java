package view.implementation;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
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
import view.implementation.BinaryGraphicExpression.Orientation;
import view.implementation.graphicConfigurationParser.GraphicConfiguration;
import controller.DragAndDropMemory;


public class Main extends Application {
	
	
	@Override
    public void start(Stage primaryStage) {
    	DragAndDropMemory.memory = new DragAndDropMemory();
    	//=================== Partie de test A SUPPRIMER DANS L'AVENIR   =============================================
        StackPane center = new StackPane();
    	BorderPane root = new BorderPane();
    	PrimaryGraphicExpression lit = new PrimaryGraphicExpression(null);
    	PrimaryGraphicExpression lit2 = new PrimaryGraphicExpression(null);
    	PrimaryGraphicExpression lit3 = new PrimaryGraphicExpression(null);
    	
    	Node operator = new Label("+");
    	Node div = new Label("-");
    	((Label) operator).getStyleClass().add("defaultFont");
    	
    	Node shapeOpen= new Label("sqrt(");
    	Node shapeClose = new Label(")");
    	((Label) shapeClose).getStyleClass().add("defaultFont");
    	((Label) shapeOpen).getStyleClass().add("defaultFont");
    	Orientation orientation = Orientation.HORIZONTAL;
    	((Label) div).getStyleClass().add("defaultFont");;
    	Orientation orientation1 = Orientation.VERTICAL;
    	
    	lit.setExpression("a");
    	lit2.setExpression("b");
    	lit3.setExpression("c");
    	
    	BinaryGraphicExpression bin1 = new BinaryGraphicExpression(null, lit, lit2, operator, orientation);
    	UnaryGraphicExpression lol = new UnaryGraphicExpression(null, bin1, shapeOpen, shapeClose, orientation);
    	
    	BinaryGraphicExpression bin2 = new BinaryGraphicExpression(null, lol, lit3, div, orientation1);

    	
    	//=====================================================================================================

    	
    	// ajout des noeuds dans l'arbre du group
    	center.getChildren().add(bin2);
    	root.setCenter(center);

    	//cr�ation de la sc�ne et de ses propri�t�s par defaut
    	Scene scene = new Scene(root, 750, 500, Color.LIGHTGRAY);
    	
    	primaryStage.widthProperty().addListener(e -> {
            if(primaryStage.getWidth() < 600) {
            	scene.getStylesheets().clear();
            	scene.getStylesheets().add(getClass().getResource("cssSmall.fxml").toExternalForm()); 
            }else {
            	scene.getStylesheets().clear();
            	scene.getStylesheets().add(getClass().getResource("cssLarge.fxml").toExternalForm()); 
            }
        });
    	
        primaryStage.setTitle("New DragonBox");
        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true); // mettre en plein ecran responsive
        primaryStage.setMinHeight(200); // taille min de la fenetre
        primaryStage.setMinWidth(200);
        primaryStage.show();
        
        KrakenTree tree = new KrakenTree(new GraphicConfiguration());

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
		
		// ContextMenu
    	final ContextMenu contextMenu = new ChoiceContextMenu(rules, null, tree, null);
    	
    	contextMenu.show(primaryStage);
    }
    


	public static void main(String[] args) {
        launch(args);
    }
}