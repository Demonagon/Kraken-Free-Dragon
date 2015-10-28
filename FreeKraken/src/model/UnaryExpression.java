package model;

import view.BinaryGraphicExpression;
import view.GraphicExpression;
import view.UnaryGraphicExpression;
import javafx.scene.shape.Shape;

public class UnaryExpression implements Expression {

	String type;
	Expression sub_expression;
	Shape first_decoration;
	Shape second_decoration;
	BinaryGraphicExpression.Orientation orientation;
	
	public UnaryExpression(String type, Expression sub_expression, Shape first_decoration, Shape second_decoration, BinaryGraphicExpression.Orientation orientation) {
		this.sub_expression = sub_expression;
		this.first_decoration = first_decoration;
		this.second_decoration = second_decoration;
		this.orientation = orientation;
		this.type = type;
	}
	
	@Override
	public UnaryExpression cloneExpression() {
		return new UnaryExpression(type, sub_expression.cloneExpression(), first_decoration, second_decoration, orientation);
	}

	@Override
	public GraphicExpression generateExpression() {
		return new UnaryGraphicExpression(sub_expression.generateExpression(), OperatorDuplicator.copyOperator(first_decoration), OperatorDuplicator.copyOperator(second_decoration), orientation);
	}
	
	public Expression subExpression() {
		return sub_expression;
	}
	
	public void setSubExpression(Expression expression) {
		sub_expression = expression;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public boolean compare(Expression expression) {
		if( ! (expression instanceof UnaryExpression) ) return false;
		
		UnaryExpression unary_expression = (UnaryExpression) expression;
		if( unary_expression.getType() != getType() ) return false;
		
		return unary_expression.subExpression().compare(subExpression());
	}

	@Override
	public boolean doesMatchModel(Expression model) {
		if( model instanceof PrimaryExpression && model.getType() == PrimaryExpression.general_expression_type ) return true;
		if( ! (model instanceof UnaryExpression) ) return false;
		if( ! (model.getType() == getType()) ) return false;
		
		UnaryExpression unary_model = (UnaryExpression) model;
		return subExpression().doesMatchModel(unary_model.subExpression());
	}

	@Override
	public String expressionToString() {
		return getType() + "( " + subExpression().expressionToString() + " )";
	}

}
