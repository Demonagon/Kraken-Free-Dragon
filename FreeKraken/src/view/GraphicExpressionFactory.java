package view;

import model.Expression;

public interface GraphicExpressionFactory {

	public Object generateBinaryExpression(Expression expression, String type, Object first, Object second);
	
	public Object generateUnaryExpression(Expression expression, String type, Object sub);
	
	public Object generatePrimaryExpression(Expression expression, String type, String name);
	
	public void init();
}
