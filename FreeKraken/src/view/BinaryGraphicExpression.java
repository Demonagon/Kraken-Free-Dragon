package view;

import javafx.scene.shape.Shape;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Expression;
import javafx.scene.layout.*;

/**
 * 
 * @author florian, nico, thomas
 *
 */
public class BinaryGraphicExpression extends GraphicExpression{

	public enum Orientation {
		VERTICAL, HORIZONTAL
	}
	GraphicExpression firstExpression;
	GraphicExpression secondExpression;
	Shape operator;
	Orientation orientation;
	
	
	/**
	 * Construteur par defaut, Construit une expression 
	 * @param firstExpression la premiére expression type -> expression 
	 * @param secondExpression la seconde expression type -> expression
	 * @param operator un opérateur 
	 * @param orientation l'orientation de l'expression (vertical / horizontal)
	 */
	public BinaryGraphicExpression (GraphicExpression firstExpression, 
									GraphicExpression secondExpression, 
									Shape operator,
									Orientation orientation) {
		
		this.firstExpression 	= firstExpression;
		this.secondExpression 	= secondExpression;
		this.operator 			= operator;
		this.orientation 		= orientation;
		
		structOfExpression();
	}

	
	/**
	 * Structure l'expression selon son orientation
	 * ex:
	 * 		horizontal pour une addition ( a + b)
	 * 		vertical pour une fraction ( a/b )
	 * L'expression est encapsulé dans un hBox (horizontal) ou un vBox (vertical)
	 * afin que les eléments de l'expression soit toujours aligné et centré
	 * @return l'expression structuré selon son orientation
	 */
	public BorderPane structOfExpression(){
		BorderPane border = new BorderPane();
		
		if (Orientation.HORIZONTAL == this.orientation) {
			HBox hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(5);
			hbox.getChildren().addAll(firstExpression, operator, secondExpression);
			border.setCenter(hbox);
			this.getChildren().add(border);
		}else {
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
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
