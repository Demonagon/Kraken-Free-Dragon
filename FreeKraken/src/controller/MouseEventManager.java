package controller;

import javafx.scene.Node;

/**
 * @author florian Campanella, Thomas Rambaldi
 * <b>MouseEventManager est la classe représentant les évènements pouvant être effectuer à la souris.</b>
 *
 * Un évènement à la souris est caractérisé par les informations suivantes : 
 * <ul>
 * <li>Une timer</li>
 * <li></li>
 * </ul>
 *
 */
public abstract class MouseEventManager {
	
	/**
	 * Définit le temps qu'il doit y avoir avant que le clique soit pris en compte.
	 * Permet de gérer le double clique
	 */
	public final static int double_clic_time = 350;
	
	/**
	 * 
	 */
	Node node;
	
	/**
	 * Constructeur de la classe MouseEventManager
	 * @param node : le noeud cible
	 */
	public MouseEventManager(Node node) {
			this.node = node;
	}

	
	/**
	 * l'évènement quand la souris survols sur le texte
	 * A partir d'une feuille de style css
	 */
	public abstract void onMouseEntered();
	
	
	/**
	 * l'évènement quand la souris sort du texte après l'avoir survolée
	 */
	public abstract void onMouseExited();
	
	
	/**
	 * l'évènement au clique gauche
	 */
	public abstract void onMousePressed();
	
	
	/**
	 * l'évènement au relachement du clique
	 */
	public abstract void onMouseReleased();
	
	/**
	 * Permet l'appel de toutes les fonctions de l'évènement de la souris
	 */
	public void onMouseEvent() {
		onMouseEntered();
		onMouseExited();
		onMousePressed();
		onMouseReleased();
	}

}
