package controller;

import view.implementation.ControlTower;
import view.implementation.PrimaryGraphicExpression;
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
	private Timeline doubleClickTimeline;
	private ControlTower tower;
	private PrimaryGraphicExpression primary;
	
	
	/**
	 * constructor 
	 * @param group Type Group
	 * @param expr Type Text
	 */
	public PrimaryMouseEventManager(Group group, Label expr, PrimaryGraphicExpression primary, ControlTower tower) {
		super(group);
		this.expr = expr;
		this.tower = tower;
		this.primary = primary;
		doubleClickTimeline = null;
	}


	@Override
	public void onMouseEntered() {
		node.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
            	expr.getStyleClass().add("primaryGraphicExpr_OnMouseEntered");
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
		tower.processSimpleLeftClick(primary);
		doubleClickTimeline = null;
	}
	
	
	@Override
	public void onMousePressed() {
		 node.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				  if(event.getButton().equals(MouseButton.PRIMARY)){ // click gauche
					  if (doubleClickTimeline == null) { doubleClickTimeline = new Timeline(new KeyFrame(
							  Duration.millis(MouseEventManager.double_clic_time),
						        ae -> completeClickEvent()));
						doubleClickTimeline.play();
					  }
					  if(event.getClickCount() == 2){ // double click
						  if (doubleClickTimeline != null) {
							  doubleClickTimeline.stop();
							  doubleClickTimeline = null;
						  }
						  tower.processDoubleLeftClick(primary);
					  }
			            
			        }else if (event.getButton().equals(MouseButton.SECONDARY)){ // click droit
			        	if(event.getClickCount() == 1){
			        		tower.processRigthClick(primary);
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
