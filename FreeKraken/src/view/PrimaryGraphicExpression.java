package view;

import model.Expression;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Florian Campanella
 *
 */
public class PrimaryGraphicExpression extends GraphicExpression {
	
	private Text expr;
	
	
	/**
	 * constructeur par defaut
	 * construit un lit�ral (expression primaire) avec une taille est une couleur par defaut
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
		
		// on l'ajoute toujours au noeud
		this.getChildren().add(expr);
	}
	
	
	/**
	 * constructeur avec choix du positionnement, le texte debutera au centre du rectangle don�e dans les params 
	 * - l'element est positionner par rapport a l'angle inferieur gauche
	 * (capte le survol et le click de souris) 
	 * @param pHeigth hauteur (elle sera diviser par 2)
	 * @param pWidth largeur (elle sera diviser par 2)
	 * @param pFont taille de la police d'ecriture 
	 */
	public PrimaryGraphicExpression (int posX, int posY, int pFont){
		expr = new Text(posX, posY, "Default"); // (x, y, "texte")
		expr.setFont(Font.font("Verdana", pFont));
		expr.getStyleClass().add("defaultFont");
		
		//event
		onMouseEntered();
		onMouseExited();
		onMousePressed();
		onMouseReleased();
		
		// on ajoute notre noeud � l'arbre
		this.getChildren().add(expr);
	}

	
	/**
	 * l'evenement quand la souris survols sur le texte
	 */
	private void onMouseEntered() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
//            	expr.getStyleClass().add("primaryGraphicExpr_OnMouseEntered");
            	expr.setFill(Color.RED);
            }
        });
	}
	
	
	/**
	 * l'evenement quand la souris sort du texte apr�s l'avoir survol�e
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
	            	/* d�but modification Nicolas pour la d�tection des bords en vue du DnD */
	            	
					/*expr.setFill(Color.YELLOW);
					Reflection r = new Reflection();
					r.setFraction(0.7f);
					expr.setFont(Font.font(null, FontWeight.BOLD, 36));
					expr.setEffect(r);*/
	            	
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
	            	
	            	String dim = 
	            			  "(width: "   + width             + ", height: "  + height             + ")";
	            	
	            	String coord =
	            	          "(x: "       + cursorX      + ", y: "       + cursorY       + ")";
	            	
	            	System.out.println(dim);
	            	System.out.println(coord);
	            	System.out.println(position);
	            	
	            	/* fin modification Nicolas */
	            }
	        });
	}
	
	
	/**
	 * l'evenement au relachement du click
	 */
	private void onMouseReleased() {
		 this.setOnMouseReleased(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){
	            	expr.setTranslateX(expr.getY()-10);
	            	expr.setEffect(getEffect());
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
