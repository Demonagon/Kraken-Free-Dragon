package model;

public interface Expression {
	Object generateExpression();
	boolean compare(Expression expression);
	boolean doesMatchModel(Expression model);
	String getType();
	Expression cloneExpression();
	String expressionToString();
}
