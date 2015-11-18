package controller;

import view.implementation.BinaryGraphicExpression;
import view.implementation.ControlTower;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.util.Duration;


public class BinaryMouseEventManager extends MouseEventManager{

	private BinaryGraphicExpression parentExpression;
	private Timeline doubleClickTimeline;
	private ControlTower tower;
	
	
	public BinaryMouseEventManager(Node node, BinaryGraphicExpression parentExpression, ControlTower tower) {
		super(node);
		this.parentExpression = parentExpression;
		this.tower = tower;
		doubleClickTimeline = null;
	}

	@Override
	public void onMouseEntered() {
		node.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
//            	System.out.println("souris sur binary expre");
            }
        });
		
	}

	@Override
	public void onMouseExited() {
		node.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	// not implemented methode
            }
        });
	}

	public void completeClickEvent () {
		tower.processSimpleLeftClick(parentExpression);
		doubleClickTimeline = null;
	}
	
	
	@Override
	public void onMousePressed() {
		 node.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				  if(event.getButton().equals(MouseButton.PRIMARY)){ // click gauche
					  if (doubleClickTimeline == null) { 
						  doubleClickTimeline = new Timeline(new KeyFrame(
							  Duration.millis(MouseEventManager.double_clic_time),
						        ae -> completeClickEvent()));
						doubleClickTimeline.play();
					  }
					  if(event.getClickCount() == 2){ // double click
						  if (doubleClickTimeline != null) {
							  tower.processDoubleLeftClick(parentExpression);
							  doubleClickTimeline.stop();
							  doubleClickTimeline = null;
						  }
			          }
			            
			        }else if (event.getButton().equals(MouseButton.SECONDARY)){ // click droit
			        	if(event.getClickCount() == 1){
			        		tower.processRigthClick(parentExpression);
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


}
