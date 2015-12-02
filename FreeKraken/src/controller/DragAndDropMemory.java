package controller;

import view.implementation.ControlTower;
import view.implementation.GraphicExpression;
import javafx.scene.Group;


/**
 * @author florian Campanella, Thomas Rambaldi, Nicolas Leotier
 * 
 * classe qui sert de bloc-note memoire, pour memoriser les inforamation utile au DragAndDrop
 * comme la source et la cible du dragAndDrop ainsi que toute les informations les concernants
 */
public class DragAndDropMemory {

	public static DragAndDropMemory memory;
	private Group source;
	private Group target;
	private ControlTower tower;

	/**
	 * constructeur de DragAndDropMemory
	 * @param tower 
	 */
	public DragAndDropMemory (ControlTower tower) {
		this.tower = tower;
	}

	/**
	 * setteur de l'attribut source
	 * @param source Element source du D&D
	 */
	public void setSource(Group source) {
		this.source = source;
	}
	
	
	/**
	 * setteur de l'attibut target
	 * @param target Element cible du D&D
	 */
	public void setTarget(Group target) {
		this.target = target;
	}
	
	
	/**
	 * Methodes servant a reference les instances de la source et de la cible à la classe ControlTower
	 */
	public void notifyDragAndDrop () {
		tower.processDragAndDrop((GraphicExpression) source, (GraphicExpression) target);
	}
	
	
	/**
	 * METHODES UTILISER LORS DE TESTS
	 * Methode qui echange deux element de type Text 
	 * Principalement utiliser pour les elements PrimaryGraphicExpression
	 */
	/*public  void swapText() {
		if( ! (source instanceof PrimaryGraphicExpression) ) return;
		if( ! (target instanceof PrimaryGraphicExpression) ) return;
		
		PrimaryGraphicExpression source_text = (PrimaryGraphicExpression) source;
		PrimaryGraphicExpression target_text = (PrimaryGraphicExpression) target;
		String tmp = source_text.getText().getText();
		
		source_text.getText().setText(target_text.getText().getText());
		target_text.getText().setText(tmp);
	}*/
	
}