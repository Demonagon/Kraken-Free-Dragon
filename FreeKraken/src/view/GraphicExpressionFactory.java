package view;

public interface GraphicExpressionFactory {

	public Object generateBinaryExpression(String type, Object first, Object second);
	
	public Object generateUnaryExpression(String type, Object sub);
	
	public Object generatePrimaryExpression(String type, String name);
	
	public void init();
}
