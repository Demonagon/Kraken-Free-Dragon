package model;

import view.GraphicExpression;

public interface Expression {
	GraphicExpression generateExpression();
	boolean compare(Expression expression);
	boolean doesMatchModel(Expression model);
	String getType();
	Expression cloneExpression();
	String expressionToString();
}
