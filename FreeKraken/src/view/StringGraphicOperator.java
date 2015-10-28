package view;

import javafx.scene.text.Text;

public class StringGraphicOperator extends Text {
	
	public StringGraphicOperator(String msg) {
		super(msg);
	}
	
	public StringGraphicOperator copy() {
		return new StringGraphicOperator(getText());
	}
}
