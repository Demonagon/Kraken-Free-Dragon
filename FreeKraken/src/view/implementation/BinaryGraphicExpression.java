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
 * @author florian, nico, thomas
 *
 */
public class BinaryGraphicExpression extends GraphicExpression{

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
	 * Construteur par defaut, Construit une expression 
	 * @param firstExpression la premi�re expression type -> expression 
	 * @param secondExpression la seconde expression type -> expression
	 * @param operator un op�rateur 
	 * @param orientation l'orientation de l'expression (vertical / horizontal)
	 */
	public BinaryGraphicExpression (Expression model,
									GraphicExpression firstExpression, 
									GraphicExpression secondExpression, 
									Node operator,
									Orientation orientation,
									ControlTower tower) {
		
		this.model = model;
		this.firstExpression 	= firstExpression;
		this.secondExpression 	= secondExpression;
		this.operator 			= operator;
		this.orientation 		= orientation;
		dADmanager = new BinaryDragAndDropManager(this, this,tower);
		mEmanager = new BinaryMouseEventManager(operator, this, tower);
    	//event
		mEmanager.onMouseEvent();
		dADmanager.onDragAndDropEvent();		
		
		structOfExpression();
	}

	
	/**
	 * Structure l'expression selon son orientation
	 * ex:
	 * 		horizontal pour une addition ( a + b)
	 * 		vertical pour une fraction ( a/b )
	 * L'expression est encapsul� dans un hBox (horizontal) ou un vBox (vertical)
	 * afin que les el�ments de l'expression soit toujours align� et centr�
	 * @return l'expression structur� selon son orientation
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
	
	
	@Override
	public Expression getExpression() {
		return model;
	}
}
