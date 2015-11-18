package model;

import java.util.LinkedList;
import java.util.List;

import view.GraphicExpressionFactory;

public class KrakenTree {
	private Expression root;
	
	public KrakenTree(GraphicExpressionFactory factory) {
		Configuration.init(factory);
		
	}
	
	public void setRoot(Expression root) {
		this.root = root;
		root.setFather(null);
	}
	
	public Expression getRoot() {
		return root;
	}
	
	public void applicRule(Expression expression, Rule rule) {
		if(expression == root) {
			setRoot(rule.applic(root));
		}
		else {
			Expression father = expression.getFather();
			if( father instanceof BinaryExpression ) {
				BinaryExpression b_father = (BinaryExpression) father;
				if( b_father.firstExpression() == expression )
					b_father.setFirstExpression(rule.applic(expression));
				else
					b_father.setSecondExpression(rule.applic(expression));
			}
			else {
				UnaryExpression u_father = (UnaryExpression) father;
				u_father.setSubExpression(rule.applic(expression));
			}
		}
	}
	
	public static Expression getRootFromRootPath(List<Expression> root_path) {
		if( root_path.isEmpty() ) throw new IllegalArgumentException();
		return root_path.get( root_path.size() - 1 );
	}
	
	public static List<Expression> deduceRootPath(List<Expression> path_list_1, List<Expression> path_list_2) {
		int k = 0;
		for(; k < path_list_1.size() && k < path_list_2.size(); k++)
			if( path_list_1.get(k) != path_list_2.get(k) )
				return path_list_1.subList(0, k);
		
		return path_list_1.subList(0, k);
	}
	
	public static Expression deduceRoot(List<Expression> expr) {
		if( expr.isEmpty() ) throw new IllegalArgumentException();
		if( expr.size() == 1 ) return expr.get(0); 
		
		List<Expression> path_list = expr.get(0).generatePathList();
		
		for(int k = 1; k < expr.size(); k++) {
			path_list = deduceRootPath(path_list, expr.get(k).generatePathList());
		}
		
		return getRootFromRootPath(path_list);
	}
	
	public static Pair<Expression, List<Rule>> processInput(String input_type, Expression expr) {
		List<Rule> raw_rule_list = Configuration.rules.getRuleList(input_type);
		List<Rule> rules = new LinkedList<Rule>();
		for(Rule rule : raw_rule_list)
			if( rule.canApplic(expr) ) rules.add(rule);
		
		return new Pair<Expression, List<Rule>>(expr, rules);
	}
	
	public static Pair<Expression, List<Rule>> processInput(String input_type, List<Expression> exprs) {
		Expression root = deduceRoot(exprs);
		List<Rule> raw_rule_list = Configuration.rules.getRuleList(input_type);
		List<Rule> rules = new LinkedList<Rule>();
		for(Rule rule : raw_rule_list)
			if( rule.canApplic(root) ) rules.add(rule);
		
		return new Pair<Expression, List<Rule>>(root, rules);
	}
}
