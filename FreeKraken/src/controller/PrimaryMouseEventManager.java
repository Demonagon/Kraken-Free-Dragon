package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


/**
 * 
 * @author florian Campanella, Thomas Rambaldi
 *
 * implemente les methodes de MouseEvent pour le primary graphic expression
 */
public class PrimaryMouseEventManager extends MouseEventManager {

	Label expr;
	
	
	
	/**
	 * constructor 
	 * @param group Type Group
	 * @param expr Type Text
	 */
	public PrimaryMouseEventManager(Group group, Label expr) {
		super(group);
		this.expr = expr;
	}


	@Override
	public void onMouseEntered() {
		group.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
            	expr.getStyleClass().add("primaryGraphicExpr_OnMouseEntered");
        
            }
        });
	}
	
	@Override
	public void onMouseExited() {
		group.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	expr.setTextFill(Color.BLACK);
            }
        });
	}
	
	@Override
	public void onMousePressed() {
		 group.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				 
	            }
	        });
	}
	
	@Override
	public void onMouseReleased() {
		 group.setOnMouseReleased(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent event){

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
