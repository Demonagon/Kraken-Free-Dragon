package view.implementation;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.layout.StackPane;
import model.Expression;
import model.KrakenTree;
import model.Pair;
import model.Rule;
import javafx.stage.Stage;


/**
 * @author florian Campanella, Thomas Rambaldi, Nicolas Leotier
 * La de tour de control, elle fais la liaisons entre la partie technique et la partie affichage graphique
 */
public class ControlTower {

	
	public static ControlTower tower;
	private KrakenTree tree;
	private ChoiceContextMenu menu;
	private StackPane pane;
	private Stage stage;
	
	
	
	/**
	 * constructeur
	 * @param tree 
	 * @param pane 
	 * @param stage 
	 */
	public ControlTower(KrakenTree tree, StackPane pane, Stage stage) {
		this.tree = tree;
		this.menu = null;
		this.pane = pane;
		this.stage = stage;
	}
	
	
	/**
	 * methodes servant a rafraichir la fenetre javafx
	 * elle clear les children associer et rajoute les nouveaux children
	 * voir fonctionnement de group et stage javafx 
	 */
	public void refreshWindow() {
		pane.getChildren().clear();
		pane.getChildren().add((GraphicExpression) (tree.getRoot().generateExpression()) );
	}
	
	
	/**
	 * (utilisation pour l'evenements, click, doubleClick...)
	 * Verifie si il y a des regles applicable a la formule.
	 * S'il y a qu'une regle elle est automatiquement appliquer
	 * S'il y a plusieurs regles un ChoiceContextMenu est cree proposant toutes les regles possible applicable sur cette formule
	 * @param result 
	 */
	public void checkContextMenuRuleList (Pair<Expression, List<Rule>> result ) {
		if( result.second.isEmpty() ) return;
		
		if( result.second.size() == 1 ) {
			tree.applicRule(result.first, result.second.get(0));
			refreshWindow();
			return;
		}
		
		menu = new ChoiceContextMenu(result.second, result.first, tree, pane);
		menu.show(stage, stage.getX() + stage.getWidth()/2, stage.getY() + stage.getHeight()/2);
	}

	
	/**
	 * Applique les methodes lies aux evenement de souris
	 * ouvre un ChoiceContextMenu si necessaire devoilant les
	 * actions possibles lors d'un click gauche simple de souris sur une GraphicExpression
	 * @param expression
	 */
	public void processSimpleLeftClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("left_click" , expression.getExpression());
		checkContextMenuRuleList(result);
	}


	/**
	 * Applique les methodes liees aux evenement de souris
	 * ouvre un ChoiceContextMenu si necessaire devoilant les
	 * actions possibles lors d'un double click gauche de souris sur une GraphicExpression
	 * @param expression
	 */
	public void processDoubleLeftClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("double_left_click" ,expression.getExpression());
		checkContextMenuRuleList(result);
	}


	/**
	 * Applique les methodes liees aux evenement de souris
	 * ouvre un ChoiceContextMenu si necessaire devoilant les
	 * actions possibles lors d'un click droit de souris sur une GraphicExpression 
	 * @param expression
	 */
	public void processRigthClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("rigth_click" ,expression.getExpression());
		checkContextMenuRuleList(result);
	}
	
	/**
	 * Applique les methodes de drag&drop aux deux GraphicExpression passee en param
	 * @param firstExpression
	 * @param secondExpression 
	 */
	public void processDragAndDrop(GraphicExpression firstExpression, GraphicExpression secondExpression) {
		List<Expression> expressionList = new LinkedList<Expression>();
		expressionList.add(firstExpression.getExpression());
		expressionList.add(secondExpression.getExpression());
		
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("drag_and_drop", expressionList);
		checkContextMenuRuleList(result);
	}

}
