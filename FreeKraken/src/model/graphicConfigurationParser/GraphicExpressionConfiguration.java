package model.graphicConfigurationParser;

import model.OperatorDuplicator;
import model.Pair;
import view.BinaryGraphicExpression;
import view.GraphicExpression;
import view.UnaryGraphicExpression;
import javafx.scene.shape.Shape;

public class GraphicExpressionConfiguration {
	private Pair<Shape, Shape> operators;
	private BinaryGraphicExpression.Orientation orientation;
	
	public GraphicExpressionConfiguration(BinaryGraphicExpression.Orientation orientation, Pair<Shape, Shape> operators) {
		this.orientation = orientation;
		this.operators = operators;
	}
	
	public GraphicExpression generateBinaryExpression(GraphicExpression first, GraphicExpression second) {
		return new BinaryGraphicExpression(first,
										   second,
										   OperatorDuplicator.copyOperator(operators.first),
										   orientation);
	}
	
	public GraphicExpression generateUnaryExpression(GraphicExpression sub) {
		return new UnaryGraphicExpression(sub,
										  OperatorDuplicator.copyOperator(operators.first),
										  OperatorDuplicator.copyOperator(operators.second),
										  orientation);
	}
}
