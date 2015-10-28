package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * 
 * @author florian Campanella, Thomas Rambaldi
 *
 * implemente les methodes de MouseEvent pour le primary graphic expression
 */
public class PrimaryMouseEventManager extends MouseEventManager {

	Text expr;
	
	
	
	/**
	 * constructor 
	 * @param group Type Group
	 * @param expr Type Text
	 */
	public PrimaryMouseEventManager(Group group, Text expr) {
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
            	expr.setFill(Color.BLACK);
            }
        });
	}
	
	@Override
	public void onMousePressed() {
		 group.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				 	// on enlève 1/8 à la hauteur et la largeur pour avoir quelque chose qui ressemble à la vrai valeur
	            	final double width =  getText().getLayoutBounds().getWidth() - (getText().getLayoutBounds().getWidth()/8);;
	            	final double height = getText().getLayoutBounds().getHeight() - (getText().getLayoutBounds().getHeight() * 3/8);
	            	
	            	// on inverse le Y pour avoir une valeur positive
	            	final double cursorY = - event.getY();
	            	final double cursorX = event.getX();
	            	
	            	// le nombre de pixel à partir du bord qui décrit la zone détecté
	            	final int pixelDetectionAccuracy = 10;
	            	String position = "Coin";
	            	
	            	// détecter le position de la souris au moment du drop
	            	// la valeur de retour devra surement être stocké dans un enum mais
	            	// je ne connais pas Java assez bien, on y réfléchira
	            	
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
	public Text getText() {
		return expr;
	}
	
}
