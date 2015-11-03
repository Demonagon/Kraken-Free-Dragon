package view;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class ChoiceContextMenu extends ContextMenu {
	public ChoiceContextMenu() {
		MenuItem item1 = new MenuItem("About");
		MenuItem item2 = new MenuItem("Preferences");
		
		getItems().addAll(item1, item2);
	}
}
