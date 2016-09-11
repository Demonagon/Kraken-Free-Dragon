package view.implementation;

import model.Expression;
import javafx.scene.Group;

/**
 * 
 * Class abstraite qui étend Group
 * @author Florian Campanella, Thomas Rambaldi, Nicolas Léotier
 *
 */
public abstract class GraphicExpression extends Group {
	
	/**
	 * @return l'expression entière
	 */
	abstract Expression getExpression();
}
