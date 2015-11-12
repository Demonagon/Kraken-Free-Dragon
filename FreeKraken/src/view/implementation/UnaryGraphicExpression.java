package view.implementation;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Expression;
import view.implementation.BinaryGraphicExpression.Orientation;

/**
 * 
 * @author Thomas
 *
 */
public class UnaryGraphicExpression extends GraphicExpression{
	
	Expression model;
	Node decoOpen, decoClose;
	GraphicExpression expression;
	Orientation orientation;
	
	
	public UnaryGraphicExpression(Expression model,
									GraphicExpression expression, 
									Node decoOpen,
									Node decoClose,
									Orientation orientation) {
		this.model = model;
		this.expression = expression;
		this.decoOpen = decoOpen;
		this.decoClose = decoClose;
		this.orientation = orientation;
		
		//event
		onMouseEntered();
		onMouseExited();
		onMousePressed();
		onMouseReleased();
		
		constructionSousExpressionWithDeco();
	}
	
	public BorderPane constructionSousExpressionWithDeco(){
		BorderPane border = new BorderPane();

		if (Orientation.HORIZONTAL == this.orientation) {
			HBox hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(5);
			hbox.getChildren().addAll(decoOpen, expression, decoClose);
			border.setCenter(hbox);
			this.getChildren().add(border);
		}
		if (Orientation.VERTICAL == this.orientation) {
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(5);
			vbox.getChildren().addAll(decoOpen, expression, decoClose);
			border.setCenter(vbox);
			this.getChildren().add(border);
		}
		return border;
	}
	
	/*		Getters		*/
	public Node getDecoOpen() {
		return decoOpen;
	}
	
	public Node getDecoClose() {
		return decoClose;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	@Override
	public Expression getExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*		Setters		*/
	public void setDecoOpen(Node decoOpen) {
		this.decoOpen = decoOpen;
	}

	public void setDecoClose(Node decoClose) {
		this.decoClose = decoClose;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void setExpression(GraphicExpression expression) {
		this.expression = expression;
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
	 * l'evenement quand la souris sort du texte apr�s l'avoir survol�e
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
}
