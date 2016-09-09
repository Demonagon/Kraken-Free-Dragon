package view.implementation.graphicConfigurationParser;

import view.implementation.BinaryGraphicExpression;
import view.implementation.StringGraphicOperator;
import javafx.scene.Node;

public class OperatorParser {

	public static BinaryGraphicExpression.Orientation parseOrientation(String string) {
		if ( string == null ) return null;
		if ( string.charAt(0) == 'h' || string.charAt(0) == 'H' ) return BinaryGraphicExpression.Orientation.HORIZONTAL;
		if ( string.charAt(0) == 'v' || string.charAt(0) == 'V' ) return BinaryGraphicExpression.Orientation.VERTICAL;
		else return null;
	}
	
	public static Node parseOperator(String string) {
		if ( string == null ) return null;
		return new StringGraphicOperator( getUsefullPart(string) );
	}
	
	public static String getUsefullPart(String string) {
		return string.substring(1, string.length() - 1);
	}
}
