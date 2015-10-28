package controller;

import javafx.scene.Group;



public abstract class MouseEventManager {
	
	
	Group group;
	
	
	public MouseEventManager(Group group) {
			this.group = group;
	}

	
	/**
	 * l'evenement quand la souris survols sur le texte
	 * A partir d'une feuille de style css
	 */
	public abstract void onMouseEntered();
	
	
	/**
	 * l'evenement quand la souris sort du texte aprés l'avoir survolée
	 */
	public abstract void onMouseExited();
	
	
	/**
	 * l'evenement au click
	 */
	public abstract void onMousePressed();
	
	
	/**
	 * l'evenement au relachement du click
	 */
	public abstract void onMouseReleased();
	
	
	public void onMouseEvent() {
		onMouseEntered();
		onMouseExited();
		onMousePressed();
		onMouseReleased();
	}

}
