package model.graphicConfigurationParser;

import view.BinaryGraphicExpression;
import view.StringGraphicOperator;
import javafx.scene.shape.Shape;

public class OperatorParser {

	public static BinaryGraphicExpression.Orientation parseOrientation(String string) {
		if ( string == null ) return null;
		if ( string.charAt(0) == 'h' || string.charAt(0) == 'H' ) return BinaryGraphicExpression.Orientation.HORIZONTAL;
		if ( string.charAt(0) == 'v' || string.charAt(0) == 'V' ) return BinaryGraphicExpression.Orientation.VERTICAL;
		else return null;
	}
	
	public static Shape parseOperator(String string) {
		if ( string == null ) return null;
		return new StringGraphicOperator( getUsefullPart(string) );
	}
	
	public static String getUsefullPart(String string) {
		return string.substring(1, string.length() - 1);
	}
}
