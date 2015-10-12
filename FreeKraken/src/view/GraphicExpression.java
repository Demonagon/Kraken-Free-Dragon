package view;

import model.Expression;
import javafx.scene.Group;

public abstract class GraphicExpression extends Group {
	
	abstract Expression getExpression();
}
