package view.implementation;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.StackPane;
import model.Expression;
import model.KrakenTree;
import model.Pair;
import model.Rule;
import javafx.stage.Stage;

public class ControlTower {

	public static ControlTower tower;
	private KrakenTree tree;
	private ChoiceContextMenu menu;
	private StackPane pane;
	private Stage stage;
	
	public ControlTower(KrakenTree tree, StackPane pane, Stage stage) {
		this.tree = tree;
		this.menu = null;
		this.pane = pane;
		this.stage = stage;
	}
	
	public void refreshWindow() {
		pane.getChildren().clear();
		pane.getChildren().add((GraphicExpression) (tree.getRoot().generateExpression()) );
	}
	
	public void checkContextMenuRuleList (Pair<Expression, List<Rule>> result ) {
		if( result.second.isEmpty() ) return;
		
		if( result.second.size() == 1 ) {
			tree.applicRule(result.first, result.second.get(0));
			refreshWindow();
			return;
		}
		
		menu = new ChoiceContextMenu(result.second, result.first, tree, pane);
		menu.show(stage);
	}

	public void processSimpleLeftClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("left_click" , expression.getExpression());
		checkContextMenuRuleList(result);
	}

	public void processDoubleLeftClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("double_left_click" ,expression.getExpression());
		checkContextMenuRuleList(result);
	}

	public void processRigthClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("rigth_click" ,expression.getExpression());
		checkContextMenuRuleList(result);
	}
	
	public void processDragAndDrop(GraphicExpression firstExpression, GraphicExpression secondExpression) {
		List<Expression> expressionList = new LinkedList<Expression>();
		expressionList.add(firstExpression.getExpression());
		expressionList.add(secondExpression.getExpression());
		
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("drag_and_drop" ,expressionList);
		checkContextMenuRuleList(result);
	}

}
