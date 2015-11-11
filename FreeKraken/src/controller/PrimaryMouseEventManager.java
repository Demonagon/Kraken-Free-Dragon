package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

/**
 * 
 * @author florian Campanella, Thomas Rambaldi
 *
 * implemente les methodes de MouseEvent pour le primary graphic expression
 */
public class PrimaryMouseEventManager extends MouseEventManager {

	Label expr;
	Timeline doubleClickTimeline;
	
	
	/**
	 * constructor 
	 * @param group Type Group
	 * @param expr Type Text
	 */
	public PrimaryMouseEventManager(Group group, Label expr) {
		super(group);
		this.expr = expr;
		doubleClickTimeline = null;
	}


	@Override
	public void onMouseEntered() {
		node.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
            	expr.getStyleClass().add("primaryGraphicExpr_OnMouseEntered");
            	System.out.println("survol avec souris de: " + expr.getText());
            }
        });
	}
	
	@Override
	public void onMouseExited() {
		node.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	expr.setTextFill(Color.BLACK);
            }
        });
	}
	
	
	public void completeClickEvent () {
		System.out.println("click simple gauche: " + expr.getText());
		doubleClickTimeline = null;
	}
	
	
	@Override
	public void onMousePressed() {
		 node.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				  if(event.getButton().equals(MouseButton.PRIMARY)){ // click gauche
					  if (doubleClickTimeline == null) { doubleClickTimeline = new Timeline(new KeyFrame(
							  Duration.millis(500),
						        ae -> completeClickEvent()));
						doubleClickTimeline.play();
					  }
					  if(event.getClickCount() == 2){ // double click
						  if (doubleClickTimeline != null) {
							  doubleClickTimeline.stop();
							  doubleClickTimeline = null;
						  }
			              System.out.println("Double clicked gauche: "+ expr.getText());
			          }
			            
			        }else if (event.getButton().equals(MouseButton.SECONDARY)){ // click droit
			        	if(event.getClickCount() == 1){
			            	System.out.println("click simple droit: " + expr.getText());
			            }
			        }
	            }
	        });
	}
	
	
	@Override
	public void onMouseReleased() {
		 node.setOnMouseReleased(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent event){
	            	// not implemented methode
	            }
	        });
	}

	
	/**
	 * getteur de la variable expr
	 * @return type text
	 */
	public Label getText() {
		return expr;
	}
	
}
