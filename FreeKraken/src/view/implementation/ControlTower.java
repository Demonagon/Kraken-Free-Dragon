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
 * 
 * @author florian Campanella, Thomas Rambaldi, Nicolas Leotier
 * 
 * Cette classe sert literalement de tour de control afin de faire la liaisons entre la partie grammaire et la partie graphique
 */
public class ControlTower {

	public static ControlTower tower;
	private KrakenTree tree;
	private ChoiceContextMenu menu;
	private StackPane pane;
	private Stage stage;
	
	
	
	/**
	 * constructeur
	 * @param tree type:KrakenTree -> import model.KrakenTree;
	 * @param pane type:StackPane -> import javafx.scene.layout.StackPane;
	 * @param stage type:Stage -> import javafx.stage.Stage;
	 */
	public ControlTower(KrakenTree tree, StackPane pane, Stage stage) {
		this.tree = tree;
		this.menu = null;
		this.pane = pane;
		this.stage = stage;
	}
	
	
	/**
	 * methodes servant a rafraichir la fenètre javafx
	 * elle clear les children associer et rajoute les nouveaux children
	 * voir fonctionnement de group et stage javafx 
	 */
	public void refreshWindow() {
		pane.getChildren().clear();
		pane.getChildren().add((GraphicExpression) (tree.getRoot().generateExpression()) );
	}
	
	
	/**
	 * (utilisation pour événements, click, doubleClick...)
	 * Verifie si il y a des régles applicable à la formule.
	 * S'il y a qu'une regle elle est automatiquement appliquer
	 * S'il y a plusieurs régles un ChoiceContextMenu est crée proposant toutes les régles possible applicable sur cette formule
	 * @param result -> la liste des formule possible selon les régles de calcules
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
	 * Applique les methodes liées aux événement de souris
	 * ouvre un ChoiceContextMenu si necessaire devoilant les
	 * actions possibles lors d'un click gauche simple de souris sur une GraphicExpression
	 * @param expression type:GraphicExpression
	 */
	public void processSimpleLeftClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("left_click" , expression.getExpression());
		checkContextMenuRuleList(result);
	}


	/**
	 * Applique les methodes liées aux événement de souris
	 * ouvre un ChoiceContextMenu si necessaire devoilant les
	 * actions possibles lors d'un double click gauche de souris sur une GraphicExpression
	 * @param expression type:GraphicExpression
	 */
	public void processDoubleLeftClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("double_left_click" ,expression.getExpression());
		checkContextMenuRuleList(result);
	}


	/**
	 * Applique les methodes liées aux événement de souris
	 * ouvre un ChoiceContextMenu si necessaire devoilant les
	 * actions possibles lors d'un click droit de souris sur une GraphicExpression 
	 * @param expression type:GraphicExpression
	 */
	public void processRigthClick(GraphicExpression expression) {
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("rigth_click" ,expression.getExpression());
		checkContextMenuRuleList(result);
	}
	
	/**
	 * Applique les methodes de drag&drop aux deux GraphicExpression passé en param
	 * @param firstExpression type:GraphicExpression
	 * @param secondExpression type:GraphicExpression
	 */
	public void processDragAndDrop(GraphicExpression firstExpression, GraphicExpression secondExpression) {
		List<Expression> expressionList = new LinkedList<Expression>();
		expressionList.add(firstExpression.getExpression());
		expressionList.add(secondExpression.getExpression());
		
		Pair<Expression, List<Rule>> result = KrakenTree.processInput("drag_and_drop", expressionList);
		checkContextMenuRuleList(result);
	}

}
