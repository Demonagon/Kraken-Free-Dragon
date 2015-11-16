package view.implementation.graphicConfigurationParser;

import view.implementation.OperatorDuplicator;
import model.Expression;
import model.Pair;
import view.implementation.BinaryGraphicExpression;
import view.implementation.ControlTower;
import view.implementation.GraphicExpression;
import view.implementation.UnaryGraphicExpression;
import javafx.scene.Node;

public class GraphicExpressionConfiguration {
	private Pair<Node, Node> operators;
	private BinaryGraphicExpression.Orientation orientation;
	private ControlTower tower;
	
	public GraphicExpressionConfiguration(BinaryGraphicExpression.Orientation orientation, Pair<Node, Node> operators, ControlTower tower) {
		this.orientation = orientation;
		this.operators = operators;
		this.tower = tower;
	}
	
	public GraphicExpression generateBinaryExpression(Expression expression, GraphicExpression first, GraphicExpression second) {
		return new BinaryGraphicExpression(expression,
										   first,
										   second,
										   OperatorDuplicator.copyOperator(operators.first),
										   orientation,
										   tower);
	}
	
	public GraphicExpression generateUnaryExpression(Expression expression, GraphicExpression sub) {
		return new UnaryGraphicExpression(expression,
										  sub,
										  OperatorDuplicator.copyOperator(operators.first),
										  OperatorDuplicator.copyOperator(operators.second),
										  orientation, 
										  tower);
	}
}
