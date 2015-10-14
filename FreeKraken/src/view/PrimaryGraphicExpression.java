package view;

import model.Expression;
import javafx.event.EventHandler;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * 
 * @author florian
 *
 */
public class PrimaryGraphicExpression extends GraphicExpression {
	
	private Text expr;
	
	
	/**
	 * constructeur par defaut
	 * construit un litéral (expression primaire) avec une taille est une couleur par defaut
	 * l'expression primaire construite capte les evenements survols et click de souris
	 */
	public PrimaryGraphicExpression() {
		expr = new Text("Default");
		expr.setFont(Font.font("Verdana", 50));
		expr.setFill(Color.BLACK);
	    
		//event
		onMouseEntered();
		onMouseExited();
		onMousePressed();
		onMouseReleased();
		
		// on l'ajoute toujours au noeud
		this.getChildren().add(expr);
	}
	
	
	/**
	 * constructeur avec choix du positionnement, le texte debutera au centre du rectangle donée dans les params 
	 * - l'element est positionner par rapport a l'angle inferieur gauche
	 * (capte le survol et le click de souris) 
	 * @param pHeigth hauteur (elle sera diviser par 2)
	 * @param pWidth largeur (elle sera diviser par 2)
	 * @param pFont taille de la police d'ecriture 
	 */
	public PrimaryGraphicExpression (int posX, int posY, int pFont){
		expr = new Text(posX, posY, "Default"); // (x, y, "texte")
		expr.setFont(Font.font("Verdana", pFont));
		expr.setFill(Color.BLACK);
		
		//event
		onMouseEntered();
		onMouseExited();
		onMousePressed();
		onMouseReleased();
		
		// on ajoute notre noeud à l'arbre
		this.getChildren().add(expr);
	}

	
	/**
	 * l'evenement quand la souris survols sur le texte
	 */
	private void onMouseEntered() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	expr.setFill(Color.BLUEVIOLET);
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
	            public void handle(MouseEvent me){
	               expr.setFill(Color.YELLOW);
	               Reflection r = new Reflection();
	               r.setFraction(0.7f);
	               expr.setFont(Font.font(null, FontWeight.BOLD, 36));
	               expr.setEffect(r);
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
