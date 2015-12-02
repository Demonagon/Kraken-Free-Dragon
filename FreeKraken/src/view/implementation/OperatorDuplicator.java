package view.implementation;

import javafx.scene.Node;

/**
 * 
 * @author Florian Campanella, Thomas Rambaldi, Nicolas Léotier
 * Duplique un opérateur
 *
 */
public class OperatorDuplicator {

	/**
	 * 
	 * @param operator
	 * @return un clone de l'Operator fournis
	 * 
	 */
	public static Node copyOperator(Node operator) {
		if( operator instanceof StringGraphicOperator )
			return ( (StringGraphicOperator) operator).copy();
		return null;
	}

}
