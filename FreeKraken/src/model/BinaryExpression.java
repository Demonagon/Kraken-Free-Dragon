package model;

import view.BinaryGraphicExpression;
import view.GraphicExpression;
import javafx.scene.shape.Shape;

public class BinaryExpression implements Expression {

	String type;
	Expression first_expression;
	Expression second_expression;
	Shape operator;
	BinaryGraphicExpression.Orientation orientation;
	
	public BinaryExpression(String type, Expression first_expression, Expression second_expression, Shape operator, BinaryGraphicExpression.Orientation orientation) {
		this.first_expression = first_expression;
		this.second_expression = second_expression;
		this.operator = operator;
		this.orientation = orientation;
		this.type = type;
	}
	
	@Override
	public BinaryExpression cloneExpression() {
		return new BinaryExpression(type, first_expression.cloneExpression(), second_expression.cloneExpression(), operator, orientation);
	}

	@Override
	public GraphicExpression generateExpression() {
		return new BinaryGraphicExpression(first_expression.generateExpression(),
										   second_expression.generateExpression(),
										   OperatorDuplicator.copyOperator(operator),
										   orientation);
	}

	@Override
	public boolean compare(Expression expression) {
		if( ! (expression instanceof BinaryExpression) ) return false;
		
		BinaryExpression binary_expression = (BinaryExpression) expression;
		if( binary_expression.getType() != getType() ) return false;
		
		return binary_expression.firstExpression().compare(firstExpression()) && binary_expression.secondExpression().compare(secondExpression());
	}

	@Override
	public boolean doesMatchModel(Expression model) {
		if( model instanceof PrimaryExpression && model.getType() == PrimaryExpression.general_expression_type ) return true;
		if( ! (model instanceof BinaryExpression) ) return false;
		if( ! (model.getType() == getType()) ) return false;
		
		BinaryExpression binary_model = (BinaryExpression) model;
		return firstExpression().doesMatchModel(binary_model.firstExpression()) && secondExpression().doesMatchModel(binary_model.secondExpression());
	}
	
	public Expression firstExpression() {
		return first_expression;
	}
	
	public Expression secondExpression() {
		return second_expression;
	}
	
	public void setFirstExpression(Expression expression) {
		first_expression = expression;
	}
	
	public void setSecondExpression(Expression expression) {
		second_expression = expression;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String expressionToString() {
		return "( " + firstExpression().expressionToString() + " " + getType() + " " + secondExpression().expressionToString() + " )";
	}
}
