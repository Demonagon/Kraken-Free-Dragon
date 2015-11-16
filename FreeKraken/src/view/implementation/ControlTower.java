package view.implementation;

import java.util.LinkedList;
import java.util.List;

import model.Expression;
import model.KrakenTree;

public class ControlTower {

	public static ControlTower tower;
	private KrakenTree tree;
	
	public ControlTower(KrakenTree tree) {
		this.tree = tree;
	}

	public void processSimpleLeftClick(GraphicExpression expression) {
		tree.processInput("left_click" ,expression.getExpression());
	}

	public void processDoubleLeftClick(GraphicExpression expression) {
		tree.processInput("double_left_click" ,expression.getExpression());
	}

	public void processRigthClick(GraphicExpression expression) {
		tree.processInput("rigth_click" ,expression.getExpression());
	}
	
	public void processDragAndDrop(GraphicExpression firstExpression, GraphicExpression secondExpression) {
		List<Expression> expressionList = new LinkedList<Expression>();
		expressionList.add(firstExpression.getExpression());
		expressionList.add(secondExpression.getExpression());
		
		tree.processInput("drag_and_drop" ,expressionList);
	}

}
