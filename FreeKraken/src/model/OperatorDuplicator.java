package model;

import javafx.scene.shape.Shape;
import view.StringGraphicOperator;

public class OperatorDuplicator {

	public static Shape copyOperator(Shape operator) {
		if( operator instanceof StringGraphicOperator )
			return ( (StringGraphicOperator) operator).copy();
		return null;
	}

}
