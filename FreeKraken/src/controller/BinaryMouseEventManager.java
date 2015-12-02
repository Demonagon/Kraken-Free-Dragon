package controller;

import view.implementation.BinaryGraphicExpression;
import view.implementation.ControlTower;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * <b>BinaryMouseEventManager est la classe représentant les évènements pouvant être effectuer à la souris sur un binary graphic expression.</b>
 * Elle est étendu par la classe MouseEventManager
 * @see MouseEventManager
 *
 * Un évènement à la souris sur une expression binaire est caractérisé par les informations suivantes : 
 * <ul>
 * <li>Une expression binaire</li>
 * <li>Un timer pour gérer le double clique</li>
 * <li></li>
 * </ul>
 *
 * @author florian Campanella, Thomas Rambaldi
 *
 */
public class BinaryMouseEventManager extends MouseEventManager{

	/**
	 * L'expression binaire
	 */
	private BinaryGraphicExpression parentExpression;
	
	/**
	 * Timer pour gérer le double clique
	 */
	private Timeline doubleClickTimeline;
	
	/**
	 * 
	 */
	private ControlTower tower;
	
	/**
	 * Constructeur de la classe BinaryMouseEventManager
	 * @param node : opérateur de l'expression binaire
	 * @param parentExpression : expression binaire
	 * @param tower : la control tower
	 */
	public BinaryMouseEventManager(Node node, BinaryGraphicExpression parentExpression, ControlTower tower) {
		super(node);
		this.parentExpression = parentExpression;
		this.tower = tower;
		doubleClickTimeline = null;
	}

	/**
	 * Evènement de la souris quand celle-ci entre dans une expression binaire
	 */
	@Override
	public void onMouseEntered() {
		node.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
//            	System.out.println("souris sur binary expre");
            }
        });
		
	}

	/**
	 * Evènement de la souris quand celle-ci sort de l'expression binaire
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
		tower.processSimpleLeftClick(parentExpression);
		doubleClickTimeline = null;
	}
	
	/**
	 * Evènement de la souris quand le clique gauche est effectué sur l'opérateur de l'expression binaire
	 */
	@Override
	public void onMousePressed() {
		 node.setOnMousePressed(new EventHandler<MouseEvent>(){
			 public void handle(MouseEvent event){
				  if(event.getButton().equals(MouseButton.PRIMARY)){ // click gauche
					  if (doubleClickTimeline == null) { 
						  doubleClickTimeline = new Timeline(new KeyFrame(
							  Duration.millis(MouseEventManager.double_clic_time),
						        ae -> completeClickEvent()));
						doubleClickTimeline.play();
					  }
					  if(event.getClickCount() == 2){ // double click
						  if (doubleClickTimeline != null) {
							  tower.processDoubleLeftClick(parentExpression);
							  doubleClickTimeline.stop();
							  doubleClickTimeline = null;
						  }
			          }
			            
			        }else if (event.getButton().equals(MouseButton.SECONDARY)){ // click droit
			        	if(event.getClickCount() == 1){
			        		tower.processRigthClick(parentExpression);
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
