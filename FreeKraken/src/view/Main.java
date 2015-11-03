package view;


import controller.DragAndDropMemory;
import view.BinaryGraphicExpression.Orientation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.Node;


public class Main extends Application {
	
	
	@Override
    public void start(Stage primaryStage) {
    	DragAndDropMemory.memory = new DragAndDropMemory();
    	//=================== Partie de test A SUPPRIMER DANS L'AVENIR   =============================================
        StackPane center = new StackPane();
    	BorderPane root = new BorderPane();
    	PrimaryGraphicExpression lit = new PrimaryGraphicExpression();
    	PrimaryGraphicExpression lit2 = new PrimaryGraphicExpression();
    	PrimaryGraphicExpression lit3 = new PrimaryGraphicExpression();
    	
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
    	
    	BinaryGraphicExpression bin1 = new BinaryGraphicExpression(lit, lit2, operator, orientation);
    	UnaryGraphicExpression lol = new UnaryGraphicExpression(bin1, shapeOpen, shapeClose, orientation);
    	
    	BinaryGraphicExpression bin2 = new BinaryGraphicExpression(lol, lit3, div, orientation1);

    	
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
    }
    


	public static void main(String[] args) {
        launch(args);
    }
}