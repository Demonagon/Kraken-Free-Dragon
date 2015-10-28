package model;

public class Rule {

	Expression input_model;
	Expression result_model;
	
	public Rule(Expression input_model, Expression result_model) {
		this.input_model = input_model;
		this.result_model = result_model;
	}
	
	public Expression applic(Expression input) throws IllegalArgumentException
	{
		if( ! input.doesMatchModel(input_model) ) throw new IllegalArgumentException();
		
		Memory memory = new Memory();
		
		if( ! memory.init(input_model, input) ) throw new IllegalArgumentException();
		
		return memory.applic(result_model);
	}
	
	@Override
	public String toString() {
		return input_model.expressionToString() + " ==> " + result_model.expressionToString();
	}

}
