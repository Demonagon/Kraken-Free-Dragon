package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
 
public class Main extends Application {
    
	
	
    @Override
    public void start(Stage primaryStage) {
        StackPane center = new StackPane();

    	BorderPane root = new BorderPane();
    	PrimaryGraphicExpression lit = new PrimaryGraphicExpression();
    	center.getChildren().add(lit);
    	root.setCenter(center);
    	
    	Scene scene = new Scene(root, 750, 500);

    	
        //definition de la fenetre d'affichage
        primaryStage.setTitle("New DragonBox");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    // sert juste a lancer l'application
    public static void main(String[] args) {
        launch(args);
    }
}