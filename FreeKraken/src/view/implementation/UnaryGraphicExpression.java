package view.implementation;

import controller.DragAndDropManager;
import controller.MouseEventManager;
import controller.UnaryDragAndDropManager;
import controller.UnaryMouseEventManager;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Expression;
import view.implementation.BinaryGraphicExpression.Orientation;


/**
 * <b> UnaryGraphicExpression est la classe représentant les opérateurs unaire. </b>
 * Elle est étendu par la classe GraphicExpression
 * @see GraphicExpression
 *
 * Une expression unaire est caractérisé par les information suivante : 
 * <ul>
 * <li>Une expressions</li> 
 * <li>Un opérateur unaire gauche</li>
 * <li>Un opérateur unaire droit</li>
 * <li>L'orientation de l'expression</li>
 * <li>La possibilité de faire du drag and drop sur un opérateur unaire</li>
 * <li>La possibilité de cliquer sur un opérateur unaire</li>
 * </ul>
 *
 * @author florian Campanella, Thomas Rambaldi
 */
public class UnaryGraphicExpression extends GraphicExpression{
	
	/**
	 * 
	 * @see Expression
	 */
	Expression model;
	
	/**
	 * Opérateur unaire pouvant se trouver à droite et/ou à gauche.
	 * Cette opérateur peut être modifier
	 * @see UnaryGraphicExpression#getDecoOpen()
	 * @see UnaryGraphicExpression#getDecoClose()
	 * @see UnaryGraphicExpression#setDecoOpen(Node)
	 * @see UnaryGraphicExpression#setDecoClose(Node)
	 */
	Node decoOpen, decoClose;
	
	/**
	 * 
	 * @see GraphicExpression
	 */
	GraphicExpression expression;
	
	/**
	 * Définit l'orientation de l'expression (horizontalement ou verticalement)
	 * @see BinaryGraphicExpression.Orientation
	 * Cette orientation peut-être changé
	 * @see UnaryGraphicExpression#setOrientation(Orientation)
	 * @see UnaryGraphicExpression#getOrientation()
	 */
	Orientation orientation;
	
	/**
	 * 
	 * @see DragAndDropManager
	 */
	private DragAndDropManager dADmanagerOpen;
	
	/**
	 * 
	 * @see MouseEventManager
	 */
	private MouseEventManager mEmanagerOpen;
	
	/**
	 * Constructeur UnagryGraphicExpression faisant appel à un autre constructeur.
	 * @see UnaryGraphicExpression#UnaryGraphicExpression(Expression, GraphicExpression, Node, Node, Orientation, ControlTower, boolean)
	 * @param model
	 * @param expression 
	 * @param decoOpen opérateur unaire gauche
	 * @param decoClose opérateur unaire droit
	 * @param orientation orientation de l'expression
	 * @param tower
	 */
	public UnaryGraphicExpression(Expression model,
									GraphicExpression expression, 
									Node decoOpen,
									Node decoClose,
									Orientation orientation,
									ControlTower tower) {
		this(model, expression, decoOpen, decoClose, orientation, tower, true);
	}
	
	/**
	 * Constructeur UnagryGraphicExpression
	 * @param model
	 * @param expression 
	 * @param decoOpen opérateur unaire gauche
	 * @param decoClose opérateur unaire droit
	 * @param orientation orientation de l'expression
	 * @param tower
	 * @param isClickable definit si l'on peut cliquer sur une expression unaire
	 */
	public UnaryGraphicExpression  (Expression model,
									GraphicExpression expression, 
									Node decoOpen,
									Node decoClose,
									Orientation orientation,
									ControlTower tower,
									boolean isClickable) {
		this.model = model;
		this.expression = expression;
		this.decoOpen = decoOpen;
		this.decoClose = decoClose;
		this.orientation = orientation;
		
		if( isClickable ) {
			dADmanagerOpen = new UnaryDragAndDropManager(this, decoOpen, decoClose, this, tower);
			mEmanagerOpen = new UnaryMouseEventManager(decoOpen,this, tower);
			
			//event
			mEmanagerOpen.onMouseEvent();
			dADmanagerOpen.onDragAndDropEvent();
		}
		else {
			dADmanagerOpen = null;
			mEmanagerOpen = null;
		}
		
		constructionSousExpressionWithDeco();
	}
	
	/**
	 * Construction de l'expression en fonction de l'orientation.
	 * @return l'expression construite horizontalement ou verticalement
	 */
	public BorderPane constructionSousExpressionWithDeco(){
		BorderPane border = new BorderPane();

		if (Orientation.HORIZONTAL == this.orientation) {
			HBox hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(5);
			hbox.getChildren().addAll(decoOpen, expression, decoClose);
			border.setCenter(hbox);
			this.getChildren().add(border);
		}
		if (Orientation.VERTICAL == this.orientation) {
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(5);
			vbox.getChildren().addAll(decoOpen, expression, decoClose);
			border.setCenter(vbox);
			this.getChildren().add(border);
		}
		return border;
	}
	
	/**
	 * Retourne l'opérateur unaire gauche
	 * @return L'operateur gauche sous forme de Node
	 */
	public Node getDecoOpen() {
		return decoOpen;
	}
	
	/**
	 * Retourne l'opérateur unaire droit
	 * @return L'operateur droit sous forme de Node
	 */
	public Node getDecoClose() {
		return decoClose;
	}
	
	/**
	 * Retourne l'orientation de l'expression
	 * @return L'orientation de l'expression verticalement ou horizontalement
	 */
	public Orientation getOrientation() {
		return orientation;
	}
	
	/**
	 * Retourne l'expression
	 * @return L'expression sous forme d'Expression
	 */
	@Override
	public Expression getExpression() {
		return model;
	}
	
	/**
	 * Permet la modification de l'opérateur unaire gauche
	 * @param decoOpen Nouveau opérateur unaire gauche
	 */
	public void setDecoOpen(Node decoOpen) {
		this.decoOpen = decoOpen;
	}

	/**
	 * Permet la modification de l'opérateur unaire droit
	 * @param decoClose Nouveau opérateur unaire droit
	 */
	public void setDecoClose(Node decoClose) {
		this.decoClose = decoClose;
	}

	/**
	 * Permet la modification de l'orientation de l'expression
	 * @param orientation La nouvelle orientation
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * Permet la modification de l'expression
	 * @param expression La nouvelle expression
	 */
	public void setExpression(GraphicExpression expression) {
		this.expression = expression;
	}
	
}
