package view.implementation;

import java.util.List;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import model.Expression;
import model.KrakenTree;
import model.Rule;

public class ChoiceContextMenu extends ContextMenu {
	
	List<Rule> rules;
	Expression expression;
	KrakenTree tree;
	
	/*
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
	*/
	
	public ChoiceContextMenu(List<Rule> rules, Expression expression, KrakenTree tree) {
		this.rules = rules;
		this.expression = expression;
		this.tree = tree;
		
		MenuItem item1 = new MenuItem("Multiplication");
		item1.setGraphic((GraphicExpression)(rules.get(0).generateExpression()));
		MenuItem item2 = new MenuItem("Division");
		
		getItems().addAll(item1, item2);
	}
}
