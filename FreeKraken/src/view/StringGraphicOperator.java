package view;

import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class StringGraphicOperator extends Label {
	
	public StringGraphicOperator(String msg) {
		super(msg);
	}
	
	public StringGraphicOperator copy() {
		return new StringGraphicOperator(getText());
	}
}
