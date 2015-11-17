package view.implementation;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import model.Expression;
import model.KrakenTree;
import model.Rule;
import javafx.scene.layout.StackPane;

public class ChoiceContextMenu extends ContextMenu {
	
	List<Rule> rules;
	Expression expression;
	KrakenTree tree;
	StackPane pane;
	
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
	
	public ChoiceContextMenu(List<Rule> rules, Expression expression, KrakenTree tree, StackPane pane) {
		this.rules = rules;
		this.expression = expression;
		this.tree = tree;
		this.pane = pane;
		
		//tree.applicRule(expression, rules.get(0));
		
		List<MenuItem> items = generateItemsMenu();
		
		getItems().addAll(items);
	}
	
	public void refreshWindow() {
		pane.getChildren().clear();
		pane.getChildren().add((GraphicExpression) (tree.getRoot().generateExpression()) );
	}
	
	public List<MenuItem> generateItemsMenu()
	{
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		
		for(Rule rule : rules)
		{
			MenuItem item = new MenuItem();
			
			GraphicExpression gExpression = (GraphicExpression) (rule.generateExpression());
//			gExpression.get
			item.setGraphic(gExpression);
			item.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	tree.applicRule(expression, rule);
			    	refreshWindow();
			    }
			});
			
			items.add(item);
		}
		
		return items;
	}
}
