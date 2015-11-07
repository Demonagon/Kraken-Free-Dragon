package model;

public class UnaryExpression implements Expression {

	String type;
	Expression sub_expression;
	
	public UnaryExpression(String type, Expression sub_expression) {
		this.sub_expression = sub_expression;
		this.type = type;
	}
	
	@Override
	public UnaryExpression cloneExpression() {
		return new UnaryExpression(type, sub_expression.cloneExpression() );
	}

	@Override
	public Object generateExpression() {
		return Configuration.graphic.generateUnaryExpression(	
					type,
					sub_expression.generateExpression() );
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
