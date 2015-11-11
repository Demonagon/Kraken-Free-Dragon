package model;

import java.util.List;

public interface Expression {
	Object generateExpression();
	boolean compare(Expression expression);
	boolean doesMatchModel(Expression model);
	String getType();
	Expression cloneExpression();
	String expressionToString();
	
	// Father and vertical tree exploration
	void setFather(Expression expression);
	Expression getFather();
	List<Expression> generatePathList();
}
