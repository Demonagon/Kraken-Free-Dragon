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


/**
 * 
 * @author Nicolas Léotier
 * 
 * Cette classe étend la classe ContextMenu de JavaFX
 * Elle construit un menu pop-up dans le cas ou les clics de souris ne sont pas suffisant pour appliqué toute les règles possibles sur un élément
 *
 */
public class ChoiceContextMenu extends ContextMenu {
	
	/**
	 * 
	 */
	List<Rule> rules;
	
	/**
	 * 
	 */
	Expression expression;
	
	/**
	 * 
	 */
	KrakenTree tree;
	
	/**
	 * 
	 */
	StackPane pane;
	
	/**
	 * Constructeur de la classe ChoiceContextMenu
	 * 
	 * @param rules 		type:List<Rule>
	 * @param expression 	type:Expression
	 * @param tree 			type:KrakenTree
	 * @param pane 			type:StackPane
	 */
	public ChoiceContextMenu(List<Rule> rules, Expression expression, KrakenTree tree, StackPane pane) {
		this.rules = rules;
		this.expression = expression;
		this.tree = tree;
		this.pane = pane;
		
		List<MenuItem> items = generateItemsMenu();
		
		getItems().addAll(items);
	}
	
	/**
	 * Permet de rafraichir la fenêtre, afin d'afficher le menu
	 */
	public void refreshWindow() {
		pane.getChildren().clear();
		pane.getChildren().add((GraphicExpression) (tree.getRoot().generateExpression()) );
	}
	
	/**
	 * Génère les items du menu à partir d'une liste d'expressions graphiques
	 */
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
