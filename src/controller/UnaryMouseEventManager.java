package controller;

import view.implementation.ControlTower;
import view.implementation.UnaryGraphicExpression;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * <b>UnaryMouseEventManager est la classe représentant les évènements pouvant être effectuer à la souris sur un unary graphic expression.</b>
 * Elle est étendu par la classe MouseEventManager
 * @see MouseEventManager
 *
 * Un évènement à la souris sur une expression unaire est caractérisé par les informations suivantes : 
 * <ul>
 * <li>Une expression primaire</li>
 * <li>Timer pour le double clique</li>
 * <li></li>
 * </ul>
 * @author florian Campanella, Thomas Rambaldi
 */
public class UnaryMouseEventManager extends MouseEventManager{

	/**
	 * L'expression unaire
	 */
	private UnaryGraphicExpression unaryExpression;
	
	/**
	 * Timer pour gérer le double clique
	 */
	private Timeline doubleClickTimeline;
	
	/**
	 * 
	 */
	private ControlTower tower;
	
	/**
	 * Constructeur de la classe MouseEventManager
	 * @param node opérateur de l'expression
	 * @param unaryExpression l'expression unaire
	 * @param tower : la control tower
	 */
	public UnaryMouseEventManager(Node node, UnaryGraphicExpression unaryExpression, ControlTower tower) {
		super(node);
		this.unaryExpression = unaryExpression;
		this.tower = tower;
		doubleClickTimeline = null;
	}

	/**
	 * Evènement de la souris quand celle-ci entre dans une expression unaire
	 */
	@Override
	public void onMouseEntered() {
		node.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
            	// not implemented methode
            }
        });		
	}
	
	/**
	 *  Evènement de la souris quand celle-ci sort de l'expression unaire
	 */
	@Override
	public void onMouseExited() {
		node.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	// not implemented methode
            }
        });
	}
	
	/**
	 * Permet de gérer le double clique
	 */
	public void completeClickEvent () {
		tower.processSimpleLeftClick(unaryExpression);
		doubleClickTimeline = null;
	}
	

	/**
	 * Evènement de la souris quand le clique gauche est effectué sur l'opérateur de l'expression unaire
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
							  tower.processDoubleLeftClick(unaryExpression);
							  doubleClickTimeline.stop();
							  doubleClickTimeline = null;
						  }
			          }
			            
			        }else if (event.getButton().equals(MouseButton.SECONDARY)){ // click droit
			        	if(event.getClickCount() == 1){
			        		tower.processRigthClick(unaryExpression);
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
}
