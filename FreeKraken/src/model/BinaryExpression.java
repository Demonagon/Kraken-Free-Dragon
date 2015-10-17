package model;

import view.BinaryGraphicExpression;
import view.GraphicExpression;
import javafx.scene.shape.Shape;

public class BinaryExpression implements Expression {

	Expression first_expression;
	Expression second_expression;
	Shape operator;
	BinaryGraphicExpression.Orientation orientation;
	
	public BinaryExpression(Expression first_expression, Expression second_expression, Shape operator, BinaryGraphicExpression.Orientation orientation) {
		this.first_expression = first_expression;
		this.second_expression = second_expression;
		this.operator = operator;
		this.orientation = orientation;
	}

	@Override
	public GraphicExpression generateExpression() {
		return new BinaryGraphicExpression(first_expression.generateExpression(),
										   second_expression.generateExpression(),
										   operator,
										   orientation);
	}

	/*@Override
	public boolean isCompatible(Expression expression) {
		// TODO Auto-generated method stub
		return false;
	}*/

}
