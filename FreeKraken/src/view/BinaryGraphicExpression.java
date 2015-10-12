package view;

import javafx.scene.shape.Shape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Expression;
import javafx.scene.layout.*;


public class BinaryGraphicExpression extends GraphicExpression{

	public enum Orientation {
		VERTICAL, HORIZONTAL
	}
	GraphicExpression firstExpression;
	GraphicExpression secondExpression;
	Shape operator;
	Orientation orientation;
	
	

	public BinaryGraphicExpression (GraphicExpression firstExpression, 
									GraphicExpression secondExpression, 
									Shape operator,
									Orientation orientation) {
		
		this.firstExpression 	= firstExpression;
		this.secondExpression 	= secondExpression;
		this.operator 			= operator;
		this.orientation 		= orientation;
	}

	
	public BorderPane structOfExpression(Orientation orientation){
		BorderPane border = new BorderPane();
		
		if (Orientation.HORIZONTAL == orientation) {
			HBox hbox = new HBox();
			hbox.setSpacing(5);
			hbox.getChildren().addAll(firstExpression, operator, secondExpression);
			border.setCenter(hbox);
			this.getChildren().add(border);
		}else {
			VBox vbox = new VBox();
			vbox.setSpacing(0);
			vbox.getChildren().addAll(firstExpression, operator, secondExpression);
			border.setCenter(vbox);
			this.getChildren().add(border);
		}
		return border;
	}
	
	
	/**
	 * l'evenement quand la souris survols sur le texte
	 */
	private void onMouseEntered() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	//TODO
            }
        });
	}
	
	
	/**
	 * l'evenement quand la souris sort du texte aprés l'avoir survolée
	 */
	private void onMouseExited() {
		this.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	//TODO
            }
        });
	}
	
	
	/**
	 * l'evenement au click
	 */
	private void onMousePressed() {
		 this.setOnMousePressed(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){
	              // TODO
	            }
	        });
	}
	
	
	/**
	 * l'evenement au relachement du click
	 */
	private void onMouseReleased() {
		 this.setOnMouseReleased(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){
	            	//TODO
	            }
	        });
	}




	@Override
	public Expression getExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
