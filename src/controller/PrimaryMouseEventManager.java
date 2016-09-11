package controller;

import view.implementation.ControlTower;
import view.implementation.PrimaryGraphicExpression;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

/**
 * <b>PrimaryMouseEventManager est la classe représentant les évènements pouvant être effectuer à la souris sur un primary graphic expression.</b>
 * Elle est étendu par la classe MouseEventManager
 * @see MouseEventManager
 *
 * Un évènement à la souris sur un littéral est caractérisé par les informations suivantes : 
 * <ul>
 * <li></li>
 * <li>Timer pour le double clique</li>
 * <li></li>
 * <li>Une expression primaire</li>
 * </ul>
 * @author florian Campanella, Thomas Rambaldi
 */
public class PrimaryMouseEventManager extends MouseEventManager {

	/**
	 * Le littéral
	 */
	Label expr;
	
	/**
	 * Timer pour gérer le double clique
	 */
	private Timeline doubleClickTimeline;
	
	/**
	 * 
	 */
	private ControlTower tower;
	
	/**
	 * L'expression primaire
	 */
	private PrimaryGraphicExpression primary;
	
	
	/**
	 * Constructeur de la classe PrimaryMouseEventManager 
	 * @param group : le group
	 * @param expr : l'expression
	 * @param primary : expression primaire
	 * @param tower : la control tower
	 */
	public PrimaryMouseEventManager(Group group, Label expr, PrimaryGraphicExpression primary, ControlTower tower) {
		super(group);
		this.expr = expr;
		this.tower = tower;
		this.primary = primary;
		doubleClickTimeline = null;
	}

	/**
	 * Evènement de la souris quand celle-ci entre dans une expression primaire
	 */
	@Override
	public void onMouseEntered() {
		node.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
            	expr.getStyleClass().add("primaryGraphicExpr_OnMouseEntered");
            }
        });
	}
	
	/**
	 *  Evènement de la souris quand celle-ci sort de l'expression primaire
	*/
	@Override
	public void onMouseExited() {
		node.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	expr.setTextFill(Color.BLACK);
            }
        });
	}
	

	/**
	 * Permet de gérer le double clique
	 */
	public void completeClickEvent () {
		tower.processSimpleLeftClick(primary);
		doubleClickTimeline = null;
	}
	
	
	/**
	 * Evènement de la souris quand le clique gauche est effectué sur l'opérateur de l'expression primaire
	 */
	@Override
	public void onMousePressed() {
		 node.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				  if(event.getButton().equals(MouseButton.PRIMARY)){ // click gauche
					  if (doubleClickTimeline == null) { doubleClickTimeline = new Timeline(new KeyFrame(
							  Duration.millis(MouseEventManager.double_clic_time),
						        ae -> completeClickEvent()));
						doubleClickTimeline.play();
					  }
					  if(event.getClickCount() == 2){ // double click
						  if (doubleClickTimeline != null) {
							  doubleClickTimeline.stop();
							  doubleClickTimeline = null;
						  }
						  tower.processDoubleLeftClick(primary);
					  }
			            
			        }else if (event.getButton().equals(MouseButton.SECONDARY)){ // click droit
			        	if(event.getClickCount() == 1){
			        		tower.processRigthClick(primary);
			        	}
			        }
	            }
	        });
	}
	
	/**
	 * Evènement de la souris quand le clique gauche est relaché
	 */
	@Override
	public void onMouseReleased() {
		 node.setOnMouseReleased(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent event){
	            	// not implemented methode
	            }
	        });
	}
	
	/**
	 * Retourne le littéral
	 * @return le littéral sous forme de label
	 */
	public Label getText() {
		return expr;
	}
	
}
