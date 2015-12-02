package view.implementation;

import javafx.scene.control.Label;

/**
 * 
 * @author Florian Campanella, Thomas Rambaldi, Nicolas Léotier
 * Etend Label, permet la copie d'un opérateur graphique
 *
 */
public class StringGraphicOperator extends Label {
	
	/**
	 * @param msg
	 * récupère le texte d'un opérateur graphique
	 */
	public StringGraphicOperator(String msg) {
		super(msg);
	}
	
	/**
	 * @return la copie d'un opérateur graphique
	 */
	public StringGraphicOperator copy() {
		return new StringGraphicOperator(getText());
	}
}
