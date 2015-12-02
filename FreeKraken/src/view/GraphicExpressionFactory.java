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
	public Object generateBinaryExpression(Expression expression, String type, Object first, Object second);

	/**
	 * Génère un objet graphique.
	 * @param expression : l'expression unaire à traduire
	 * @param type : Son type
	 * @param sub : l'objet graphique correspondant au sous noeud
	 * @return l'objet graphique utilisateur
	 */
	public Object generateUnaryExpression(Expression expression, String type, Object sub);

	/**
	 * Génère un objet graphique.
	 * @param expression : l'expression primaire à traduire
	 * @param type : Son type
	 * @param name : Le nom de l'instance de l'objet primaire
	 * @return l'objet graphique utilisateur
	 */
	public Object generatePrimaryExpression(Expression expression, String type, String name);
	
	/**
	 * Génère un objet graphique correspondant à une règle.
	 * @param rule : la règle à traduire
	 * @return l'objet graphique utilisateur
	 */
	public Object generateRuleExpression(Rule rule);
	
	/**
	 * Fonction permettant à l'utilisateur d'initialiser d'éventuelles ressources.
	 * Est appellée par KrakenTree en début de vie du logiciel.
	 */
	public void init();
}
