package model;

import java.util.LinkedList;
import java.util.List;

public class BinaryExpression implements Expression {

	String type;
	Expression first_expression;
	Expression second_expression;
	
	Expression father;
	
	public BinaryExpression(String type, Expression first_expression, Expression second_expression) {
		setFirstExpression(first_expression);
		setSecondExpression(second_expression);
		this.type = type;
	}
	
	@Override
	public BinaryExpression cloneExpression() {
		return new BinaryExpression(type, first_expression.cloneExpression(), second_expression.cloneExpression());
	}

	@Override
	public Object generateExpression() {
		return Configuration.graphic.generateBinaryExpression(
					this,
					type,
					first_expression.generateExpression(),
					second_expression.generateExpression() );
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
		first_expression.setFather(this);
	}
	
	public void setSecondExpression(Expression expression) {
		second_expression = expression;
		second_expression.setFather(this);
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return expressionToString();
	}
	
	@Override
	public String expressionToString() {
		return "( " + firstExpression().expressionToString() + " " + getType() + " " + secondExpression().expressionToString() + " )";
	}

	@Override
	public void setFather(Expression father) {
		this.father = father;
	}

	@Override
	public Expression getFather() {
		return father;
	}

	@Override
	public List<Expression> generatePathList() {
		if( father == null ) {
			List<Expression> list = new LinkedList<Expression>();
			list.add(this);
			return list;
		}
		
		List<Expression> list = father.generatePathList();
		list.add(this);
		return list;
	}
}
