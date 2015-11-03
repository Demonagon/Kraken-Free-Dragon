package model;

import javafx.scene.shape.Shape;
import view.StringGraphicOperator;
import javafx.scene.Node;

public class OperatorDuplicator {

	public static Node copyOperator(Node operator) {
		if( operator instanceof StringGraphicOperator )
			return ( (StringGraphicOperator) operator).copy();
		return null;
	}

}
