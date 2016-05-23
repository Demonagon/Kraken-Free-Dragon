package view;

import model.Expression;
import model.Rule;

/**
 * Cette interface décrit le pont entre le modèle physique et le modèle graphique.
 * Elle doit être implémentée par l'utilisateur de la librairie pour renseigner
 * aux noeuds de l'arbre la forme des équivalents graphiques leur correspondant.
 * 
 * 
 * @author Pacôme
 *
 */
public interface GraphicExpressionFactory {

	/**
	 * Génère un objet graphique.
	 * @param expression : l'expression binaire à traduire
	 * @param type : Son type
	 * @param first : l'objet graphique correspondant au premier sous noeud
	 * @param second : l'objet graphique correspondant au second sous noeud
	 * @return l'objet graphique utilisateur
	 */
	public String generateBinaryExpression(Expression expression, String type, Expression first, Expression second, String id);

	/**
	 * GÃ©nÃ¨re un objet graphique.
	 * @param expression : l'expression unaire Ã  traduire
	 * @param type : Son type
	 * @param sub : l'objet graphique correspondant au sous noeud
	 * @return l'objet graphique utilisateur
	 */
	public String generateUnaryExpression(Expression expression, String type, Expression sub, String id);

	/**
	 * GÃ©nÃ¨re un objet graphique.
	 * @param expression : l'expression primaire Ã  traduire
	 * @param type : Son type
	 * @param name : Le nom de l'instance de l'objet primaire
	 * @return l'objet graphique utilisateur
	 */
	public String generatePrimaryExpression(Expression expression, String type, String name, String id);
	
	/**
	 * GÃ©nÃ¨re un objet graphique correspondant Ã  une rÃ¨gle.
	 * @param rule : la rÃ¨gle Ã  traduire
	 * @return l'objet graphique utilisateur
	 */
	
	public String generateRuleExpression(Rule rule);
	
	/**
	 * Fonction permettant Ã  l'utilisateur d'initialiser d'Ã©ventuelles ressources.
	 * Est appellÃ©e par KrakenTree en dÃ©but de vie du logiciel.
	 */
	
	public void init();
}
