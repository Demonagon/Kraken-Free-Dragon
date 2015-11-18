package view.implementation;

import javafx.scene.control.Label;
import model.Expression;
import controller.DragAndDropManager;
import controller.MouseEventManager;
import controller.PrimaryDragAndDropManager;
import controller.PrimaryMouseEventManager;

/**
 * 
 * @author Florian Campanella, Nicolas Leotier, Thomas Rambaldi
 * 
 * Cr�e un �l�ment de base d'une expression, un litt�ral.
 *
 */
public class PrimaryGraphicExpression extends GraphicExpression {
	
	Expression model;
	private Label expr;
	private DragAndDropManager DADmanager;
	private MouseEventManager MEmanager;
	
	
	
	/**
	 * constructeur par defaut
	 * construit un lit�ral (expression primaire) avec une taille est une couleur par defaut
	 * l'expression primaire construite capte les evenements survols et click de souris
	 */
	public PrimaryGraphicExpression(Expression model, ControlTower tower) {
		this(model, tower, true);
	}
	
	public PrimaryGraphicExpression(Expression model, ControlTower tower, boolean isclickable) {
		init(model, tower);
		if( isclickable )
			initEvent(tower);
	}
	
	public void init(Expression model, ControlTower tower) {
		this.model = model;
		expr = new Label("Default");
		
		expr.getStyleClass().add("defaultFont");
		
		DADmanager = null;
		MEmanager = null;
		
		// on l'ajoute toujours au noeud
		this.getChildren().add(expr);
	}
	
	public void initEvent(ControlTower tower) {
		DADmanager = new PrimaryDragAndDropManager(this, expr, this, tower);
		MEmanager = new PrimaryMouseEventManager(this, expr, this, tower);
		MEmanager.onMouseEvent();
		DADmanager.onDragAndDropEvent();
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
	public Label getText() {
		return expr;
	}

	@Override
	public Expression getExpression() {
		return model;
	}
	
}
