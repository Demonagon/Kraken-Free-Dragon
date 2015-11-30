package view.implementation;

import javafx.scene.control.Label;
import model.Expression;
import controller.DragAndDropManager;
import controller.MouseEventManager;
import controller.PrimaryDragAndDropManager;
import controller.PrimaryMouseEventManager;

/**
 * 
 * @author Florian Campanella, Thomas Rambaldi, Nicolas Léotier
 * Crée une expression avec un seul opérateur
 *
 */
public class PrimaryGraphicExpression extends GraphicExpression {
	
	Expression model;
	private Label expr;
	private DragAndDropManager DADmanager;
	private MouseEventManager MEmanager;
	
	/**
	 * Constructeur par défaut (élément cliquable par défaut)
	 * @param model
	 * @param tower
	 */
	public PrimaryGraphicExpression(Expression model, ControlTower tower) {
		this(model, tower, true);
	}
	
	/**
	 * Constructeur qui permet de rendre un élément cliquable
	 * @param model
	 * @param tower
	 * @param isclickable
	 */
	public PrimaryGraphicExpression(Expression model, ControlTower tower, boolean isclickable) {
		init(model, tower);
		if( isclickable )
			initEvent(tower);
	}
	
	/**
	 * Initialise une expression
	 * @param model
	 * @param tower
	 */
	public void init(Expression model, ControlTower tower) {
		this.model = model;
		expr = new Label("Default");
		
		expr.getStyleClass().add("defaultFont");
		
		DADmanager = null;
		MEmanager = null;
		
		// on l'ajoute toujours au noeud
		this.getChildren().add(expr);
	}
	
	/**
	 * Initialise un événement
	 * @param tower
	 */
	public void initEvent(ControlTower tower) {
		DADmanager = new PrimaryDragAndDropManager(this, expr, this, tower);
		MEmanager = new PrimaryMouseEventManager(this, expr, this, tower);
		MEmanager.onMouseEvent();
		DADmanager.onDragAndDropEvent();
	}
	
	
	/**
	 * Mise à jour de l'attribut expression
	 * @param pExpression
	 */
	public void setExpression(String pExpression) {
		this.expr.setText(pExpression);
	}
	
	/**
	 * @return une expression
	 */
	public Label getText() {
		return expr;
	}

	@Override
	public Expression getExpression() {
		return model;
	}
	
}
