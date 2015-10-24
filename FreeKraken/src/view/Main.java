package view;


import view.BinaryGraphicExpression.Orientation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

public class Main extends Application {
    
	
	
	@Override
    public void start(Stage primaryStage) {
    	//=================== Partie de test A SUPPRIMER DANS L'AVENIR   =============================================
        StackPane center = new StackPane();
    	BorderPane root = new BorderPane();
    	PrimaryGraphicExpression lit = new PrimaryGraphicExpression();
    	PrimaryGraphicExpression lit2 = new PrimaryGraphicExpression();
    	PrimaryGraphicExpression lit3 = new PrimaryGraphicExpression();
    	
    	Shape operator = new Text("+");
    	Shape div = new Text("-");
    	((Text) operator).getStyleClass().add("defaultFont");
    	
    	Shape shapeOpen= new Text("sqrt(");
    	Shape shapeClose = new Text(")");
    	((Text) shapeClose).getStyleClass().add("defaultFont");
    	((Text) shapeOpen).getStyleClass().add("defaultFont");
    	Orientation orientation = Orientation.HORIZONTAL;
    	((Text) div).getStyleClass().add("defaultFont");;
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

    	//création de la scéne et de ses propriétés par defaut
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