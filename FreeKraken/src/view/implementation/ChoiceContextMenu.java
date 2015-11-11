package view.implementation;

import java.util.List;

import model.Rule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class ChoiceContextMenu extends ContextMenu {
	
	List<Rule> rules;
	GraphicExpression expression;
	
	public ChoiceContextMenu() {
		MenuItem item1 = new MenuItem("Multiplication");
		MenuItem item2 = new MenuItem("Division");
		
		item1.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		        System.out.println("Bonjour les tentacules, alors on veut multiplier ?");
		    }
		});
		
		item2.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		        System.out.println("Bonjour les tentacules, alors on veut diviser ? BEN NIQUE TA MERE");
		    }
		});
		
		getItems().addAll(item1, item2);
	}
	
	public ChoiceContextMenu(List<Rule> rules, GraphicExpression expression) {
		this.rules = rules;
		this.expression = expression;
		
		MenuItem item1 = new MenuItem("Multiplication");
		MenuItem item2 = new MenuItem("Division");
		
		getItems().addAll(item1, item2);
	}
}
