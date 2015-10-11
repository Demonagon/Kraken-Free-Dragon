package model;

import javafx.scene.canvas.GraphicsContext;

public interface GraphicExpression {
	void ComputeSize();
	int getWidth();
	int getHeigth();
	void paint(GraphicsContext gc);
	Expression getExpression();
}
