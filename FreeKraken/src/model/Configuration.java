package model;

public class Configuration {
	
	public static GraphicConfiguration graphic;
	
	public static void init() {
		graphic = new GraphicConfiguration();
		graphic.init();
	}
}
