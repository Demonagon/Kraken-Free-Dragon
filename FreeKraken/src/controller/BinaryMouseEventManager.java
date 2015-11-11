package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;



public class BinaryMouseEventManager extends MouseEventManager{

	Label binary;
	
	
	public BinaryMouseEventManager(Group group, Label expr) {
		super(group);
		this.binary = binary;
	}

	@Override
	public void onMouseEntered() {
		group.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
            	System.out.println("souri sur binary expre");
            }
        });
		
	}

	@Override
	public void onMouseExited() {
		group.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            }
        });
	}

	@Override
	public void onMousePressed() {
		group.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				 
				  if(event.getButton().equals(MouseButton.PRIMARY)){ // click gauche
					  if(event.getClickCount() == 2){ // double click
			           
					  }else if(event.getClickCount() == 1){ // simple click

					  }
			            
			        }else if (event.getButton().equals(MouseButton.SECONDARY)){ // click droit
			        	if(event.getClickCount() == 1){

			        	}
			        }
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


}
