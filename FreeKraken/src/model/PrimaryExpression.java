package model;

import view.GraphicExpression;
import view.PrimaryGraphicExpression;


public class PrimaryExpression implements Expression{
	
	String type;
	String name;
	
	public PrimaryExpression(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	@Override
	public PrimaryExpression clone() {
		return new PrimaryExpression(type, name);
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public GraphicExpression generateExpression() {
		PrimaryGraphicExpression graphicExpression = new PrimaryGraphicExpression();
		graphicExpression.setExpression(name);
		return graphicExpression;
	}

	/*@Override
	public boolean isCompatible(Expression expression) {
		if( expression instanceof BinaryExpression ) return false;
		if( expression instanceof UnaryExpression ) return false;
		PrimaryExpression p_expression = (PrimaryExpression) expression;
		if( p_expression.getType() == "EXPRESSION" ) return true; // Correspondance mémoire ?
		if( p_expression.getType() != this.type ) return false;
		return true; // Correspondance mémoire ?
	}*/


}
