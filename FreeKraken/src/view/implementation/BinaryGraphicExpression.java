package view.implementation;

import controller.BinaryDragAndDropManager;
import controller.BinaryMouseEventManager;
import controller.DragAndDropManager;
import controller.MouseEventManager;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Expression;

/**
 * 
 * @author Florian Campanella, Thomas Rambaldi, Nicolas Léotier
 * 
 * Implémente la représentation graphique des expressions binaires
 *
 */
public class BinaryGraphicExpression extends GraphicExpression {

	public enum Orientation {
		VERTICAL, HORIZONTAL
	}
	
	Expression model;
	GraphicExpression firstExpression;
	GraphicExpression secondExpression;
	Node operator;
	Orientation orientation;
	private DragAndDropManager dADmanager;
	private MouseEventManager mEmanager;
	
	
	
	/**
	 * 
	 * @param model
	 * @param firstExpression
	 * @param secondExpression
	 * @param operator
	 * @param orientation
	 * @param tower
	 * 
	 */
	public BinaryGraphicExpression (Expression model,
									GraphicExpression firstExpression, 
									GraphicExpression secondExpression, 
									Node operator,
									Orientation orientation,
									ControlTower tower) {
		this(model, firstExpression, secondExpression, operator, orientation, tower, true);
	}
	
	/**
	 * 
	 * @param model
	 * @param firstExpression
	 * @param secondExpression
	 * @param operator
	 * @param orientation
	 * @param tower
	 * @param isClickable
	 * 
	 */
	public BinaryGraphicExpression (Expression model,
									GraphicExpression firstExpression, 
									GraphicExpression secondExpression, 
									Node operator,
									Orientation orientation,
									ControlTower tower,
									boolean isClickable) {
		this.model = model;
		this.firstExpression 	= firstExpression;
		this.secondExpression 	= secondExpression;
		this.operator 			= operator;
		this.orientation 		= orientation;
		
		if( isClickable) {
			dADmanager = new BinaryDragAndDropManager(this, this,tower);
			mEmanager = new BinaryMouseEventManager(operator, this, tower);
	    	//event
			mEmanager.onMouseEvent();
			dADmanager.onDragAndDropEvent();
		}
		else {
			dADmanager = null;
			mEmanager = null;
		}
		
		structOfExpression();
		
	}

	
	/**
	 * 
	 * Construit une expression selon une orientation
	 * @return borderPane contenant l'expression avec la bonne orientation
	 */
	public BorderPane structOfExpression(){
		BorderPane border = new BorderPane();
		
		if (Orientation.HORIZONTAL == this.orientation) {
			HBox hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(5);
			hbox.getChildren().addAll(firstExpression, operator, secondExpression);
			border.setCenter(hbox);
			this.getChildren().add(border);
		}else {
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(0);
			vbox.getChildren().addAll(firstExpression, operator, secondExpression);
			border.setCenter(vbox);
			this.getChildren().add(border);
		}
		return border;
	}
	
	/**
	 * @return la première partie de l'expression
	 */
	public Expression getFirstExpression() {
		return firstExpression.getExpression();
	}
	
	/**
	 * @return la seconde partie de l'expression
	 */
	public Expression getSecondExpression() {
		return secondExpression.getExpression();
	}
	
	/**
	 * @return l'expression entière
	 */
	@Override
	public Expression getExpression() {
		return model;
	}
}
