package view;

import controller.*;
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
	private DragAndDropManager DADmanager;
	private MouseEventManager MEmanager;
	
	
	
	/**
	 * constructeur par defaut
	 * construit un litéral (expression primaire) avec une taille est une couleur par defaut
	 * l'expression primaire construite capte les evenements survols et click de souris
	 */
	public PrimaryGraphicExpression() {
		expr = new Text("Default");
		DADmanager = new PrimaryDragAndDropManager(this, expr);
		MEmanager = new PrimaryMouseEventManager(this, expr);
		
		expr.getStyleClass().add("defaultFont");
		
		//event
		MEmanager.onMouseEvent();
		DADmanager.onDragAndDropEvent();
		
		// on l'ajoute toujours au noeud
		this.getChildren().add(expr);
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
