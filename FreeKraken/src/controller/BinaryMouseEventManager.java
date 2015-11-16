package controller;

import view.implementation.BinaryGraphicExpression;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.util.Duration;


public class BinaryMouseEventManager extends MouseEventManager{

	BinaryGraphicExpression parentExpression;
	private Timeline doubleClickTimeline;
	
	
	public BinaryMouseEventManager(Node node, BinaryGraphicExpression parentExpression) {
		super(node);
		this.parentExpression = parentExpression;
		doubleClickTimeline = null;
	}

	@Override
	public void onMouseEntered() {
		node.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
            	System.out.println("souris sur binary expre");
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
		System.out.println("click simple gauche: ");
		doubleClickTimeline = null;
	}
	
	
	@Override
	public void onMousePressed() {
		 node.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				  if(event.getButton().equals(MouseButton.PRIMARY)){ // click gauche
					  if (doubleClickTimeline == null) { 
						  doubleClickTimeline = new Timeline(new KeyFrame(
							  Duration.millis(500),
						        ae -> completeClickEvent()));
						doubleClickTimeline.play();
					  }
					  if(event.getClickCount() == 2){ // double click
						  if (doubleClickTimeline != null) {
							  System.out.println("double click gauche");
							  doubleClickTimeline.stop();
							  doubleClickTimeline = null;
						  }
			          }
			            
			        }else if (event.getButton().equals(MouseButton.SECONDARY)){ // click droit
			        	if(event.getClickCount() == 1){
			        		System.out.println("simple click droit");
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
