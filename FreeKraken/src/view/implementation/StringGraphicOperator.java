package view.implementation;

import javafx.scene.control.Label;

public class StringGraphicOperator extends Label {
	
	public StringGraphicOperator(String msg) {
		super(msg);
	}
	
	public StringGraphicOperator copy() {
		return new StringGraphicOperator(getText());
	}
}
