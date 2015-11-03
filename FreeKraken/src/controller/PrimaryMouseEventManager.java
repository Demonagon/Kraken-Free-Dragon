package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Label;


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
				 	// on enl�ve 1/8 � la hauteur et la largeur pour avoir quelque chose qui ressemble � la vrai valeur
	            	final double width =  getText().getLayoutBounds().getWidth() - (getText().getLayoutBounds().getWidth()/8);;
	            	final double height = getText().getLayoutBounds().getHeight() - (getText().getLayoutBounds().getHeight() * 3/8);
	            	
	            	// on inverse le Y pour avoir une valeur positive
	            	final double cursorY = - event.getY();
	            	final double cursorX = event.getX();
	            	
	            	// le nombre de pixel � partir du bord qui d�crit la zone d�tect�
	            	final int pixelDetectionAccuracy = 10;
	            	String position = "Coin";
	            	
	            	// d�tecter le position de la souris au moment du drop
	            	// la valeur de retour devra surement �tre stock� dans un enum mais
	            	// je ne connais pas Java assez bien, on y r�fl�chira
	            	
	            	if(cursorX < pixelDetectionAccuracy
	            	&& cursorY > pixelDetectionAccuracy
	            	&& cursorY < height - pixelDetectionAccuracy)
	            			position = "LEFT";
	            	else if(cursorX > width - pixelDetectionAccuracy
	            		 && cursorY > pixelDetectionAccuracy
	            		 && cursorY < height - pixelDetectionAccuracy)
	            			position = "RIGHT";
	            	else if(cursorX > pixelDetectionAccuracy
	            		 && cursorX < width - pixelDetectionAccuracy
	            		 && cursorY > height - pixelDetectionAccuracy)
	            			position = "TOP";
	            	else if(cursorX > pixelDetectionAccuracy
	            		 && cursorX < width - pixelDetectionAccuracy
	            		 && cursorY < pixelDetectionAccuracy)
	            			position = "BOTTOM";
	            	
	            	String dim 		= "(width: " + width + ", height: " + height + ")";
	            	
	            	String coord 	= "(x: " + cursorX + ", y: " + cursorY + ")";
	            	
	            	System.out.println(dim);
	            	System.out.println(coord); // TODO DEL this lines
	            	System.out.println(position);
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
