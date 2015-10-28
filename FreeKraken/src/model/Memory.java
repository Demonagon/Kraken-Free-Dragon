package model;

import java.util.HashMap;
import java.util.Map;

public class Memory {

	Map<String, Expression> reductions;
	
	public Memory() {
		reductions = new HashMap<String, Expression>();
	}
	
	private boolean canRegister(String name, Expression expression) {
		if( ! reductions.containsKey(name) ) return true;
		else return reductions.get(name).compare(expression);
	}
	
	private void register(String name, Expression expression) {
		if( reductions.containsKey(name) ) return;
		reductions.put(name, expression);
	}
	
	private boolean exists(String name) {
		return reductions.containsKey(name);
	}
	
	private Expression get(String name) {
		return reductions.get(name);
	}
	
	private boolean exploreBinary(Expression input_model, Expression input) {
		BinaryExpression bi_input_model = (BinaryExpression) input_model;
		BinaryExpression bi_input	    = (BinaryExpression) 	   input;
		
		if( ! explore(bi_input_model.firstExpression(),
							bi_input.firstExpression()) ) return false;
		if( ! explore(bi_input_model.secondExpression(),
							bi_input.secondExpression()) ) return false;
		
		return true;		
	}
	
	private boolean exploreUnary(Expression input_model, Expression input) {
		UnaryExpression un_input_model = (UnaryExpression) input_model;
		UnaryExpression un_input	   = (UnaryExpression) 	   input;

		if( ! explore(un_input_model.subExpression(),
							un_input.subExpression()) ) return false;
		
		return true;
	}
	
	private boolean explorePrimary(Expression input_model, Expression input) {
		PrimaryExpression pr_input_model = (PrimaryExpression) input_model;

		if( pr_input_model.getType() == PrimaryExpression.general_expression_type ) {
			if( ! canRegister(pr_input_model.getName(), input) ) return false;
			register(pr_input_model.getName(), input);
		}
		
		return true;
	}
	
	private boolean explore(Expression input_model, Expression input) {
		if( input_model instanceof BinaryExpression )
			return exploreBinary(input_model, input);
		else if( input_model instanceof UnaryExpression )
			return exploreUnary(input_model, input);
		else
			return explorePrimary(input_model, input);
	}

	public boolean init(Expression input_model, Expression input) {
		return explore(input_model, input);
	}
	
	private Expression fillBinary(Expression input) {
		BinaryExpression bi_input = (BinaryExpression) input;
		
		bi_input.setFirstExpression (  fill(bi_input.firstExpression()) );
		bi_input.setSecondExpression( fill(bi_input.secondExpression()) );
		
		return bi_input;
	}
	
	private Expression fillUnary(Expression input) {
		UnaryExpression un_input = (UnaryExpression) input;

		un_input.setSubExpression( fill(un_input.subExpression()) );
		
		return un_input;
	}
	
	private Expression fillPrimary(Expression input) {
		PrimaryExpression pr_input = (PrimaryExpression) input;

		if( pr_input.getType() == PrimaryExpression.general_expression_type )
			if( exists( pr_input.getName() ) )
				return get(pr_input.getName()).cloneExpression();
		
		return input;
	}
	
	private Expression fill(Expression input) {
		if( input instanceof BinaryExpression )
			return fillBinary(input);
		else if( input instanceof UnaryExpression )
			return fillUnary(input);
		else
			return fillPrimary(input);
	}

	public Expression applic(Expression result_model) {
		return fill( result_model.cloneExpression() );
	}

}
