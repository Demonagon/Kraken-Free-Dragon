package view;

import controller.DragAndDropManagment;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Expression;

/**
 * 
 * @author Florian Campanella, Nicolas Leotier, Thomas Rambaldi
 * 
 * Crée un élément de base d'une expression, un littéral.
 *
 */
public class PrimaryGraphicExpression extends GraphicExpression {
	
	private Text expr;
	private PrimaryGraphicExpression thisInstance = this;

	
	
	/**
	 * constructeur par defaut
	 * construit un litéral (expression primaire) avec une taille est une couleur par defaut
	 * l'expression primaire construite capte les evenements survols et click de souris
	 */
	public PrimaryGraphicExpression() {
		expr = new Text("Default");
		expr.getStyleClass().add("defaultFont");
		
		//event
		onMouseEntered();
		onMouseExited();
		onMousePressed();
		onMouseReleased();
		
		new DragAndDropManagment(this, expr).onDragAndDropEvent();
		
		
		// on l'ajoute toujours au noeud
		this.getChildren().add(expr);
	}
	
	

	
	/**
	 * l'evenement quand la souris survols sur le texte
	 * A partir d'une feuille de style css
	 */
	private void onMouseEntered() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
            	expr.getStyleClass().add("primaryGraphicExpr_OnMouseEntered");
        
            }
        });
	}
	
	/**
	 * l'evenement quand la souris sort du texte aprés l'avoir survolée
	 */
	private void onMouseExited() {
		this.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	expr.setFill(Color.BLACK);
            }
        });
	}
	
	/**
	 * l'evenement au click
	 */
	private void onMousePressed() {
		 this.setOnMousePressed(new EventHandler<MouseEvent>(){
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
	
	/**
	 * l'evenement au relachement du click
	 */
	private void onMouseReleased() {
		 this.setOnMouseReleased(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent event){

	            }
	        });
	}
	
	/**
	 * setteurs
	 * @param pExpression
	 */
	public void setExpression(String pExpression) {
		this.expr.setText(pExpression);
	}
	
	/**
	 * getteur de la variable expr
	 * @return type text
	 */
	public Text getText() {
		return expr;
	}

	@Override
	public Expression getExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
