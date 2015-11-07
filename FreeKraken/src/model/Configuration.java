package model;

import view.GraphicExpressionFactory;

public class Configuration {
	
	public static GraphicExpressionFactory graphic;
	
	public static void init(GraphicExpressionFactory factory) {
		graphic = factory;
		graphic.init();
	}
}
