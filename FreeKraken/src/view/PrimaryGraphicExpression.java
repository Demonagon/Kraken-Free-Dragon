package view;

import controller.*;
import javafx.scene.text.Text;
import model.Expression;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Florian Campanella, Nicolas Leotier, Thomas Rambaldi
 * 
 * Cr�e un �l�ment de base d'une expression, un litt�ral.
 *
 */
public class PrimaryGraphicExpression extends GraphicExpression {
	
	private Label expr;
	private DragAndDropManager DADmanager;
	private MouseEventManager MEmanager;
	
	
	
	/**
	 * constructeur par defaut
	 * construit un lit�ral (expression primaire) avec une taille est une couleur par defaut
	 * l'expression primaire construite capte les evenements survols et click de souris
	 */
	public PrimaryGraphicExpression() {
		expr = new Label("Default");
		DADmanager = new PrimaryDragAndDropManager(this, expr);
		MEmanager = new PrimaryMouseEventManager(this, expr);
		
		// ContextMenu
    	final ContextMenu contextMenu = new ChoiceContextMenu();
    	expr.setContextMenu(contextMenu);
    	expr.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
    	        contextMenu.show(expr, Side.BOTTOM, 0, 0);
			}
    	});
		
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
	public Label getText() {
		return expr;
	}

	@Override
	public Expression getExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
