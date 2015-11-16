package controller;

import view.implementation.ControlTower;
import view.implementation.GraphicExpression;
import view.implementation.PrimaryGraphicExpression;
import javafx.scene.Group;


/**
 * 
 * @author florian Campanella, Thomas Rambaldi
 * 
 * classe qui sert de bloc-note memoire, pour memoriser les inforamation utile au DragAndDrop 
 *
 */
public class DragAndDropMemory {

	public static DragAndDropMemory memory;
	private Group source;
	private Group target;
	private ControlTower tower;

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
		notifyDragAndDrop();
	}
	
	
	public void notifyDragAndDrop () {
		tower.processDragAndDrop((GraphicExpression) source, (GraphicExpression) target);
	}
	
	
	/**
	 * Methode qui echange deux élément de type Text 
	 * Principalement utiliser pour les élements PrimaryGraphicExpression
	 */
	public  void swapText() {
		if( ! (source instanceof PrimaryGraphicExpression) ) return;
		if( ! (target instanceof PrimaryGraphicExpression) ) return;
		
		PrimaryGraphicExpression source_text = (PrimaryGraphicExpression) source;
		PrimaryGraphicExpression target_text = (PrimaryGraphicExpression) target;
		String tmp = source_text.getText().getText();
		
		source_text.getText().setText(target_text.getText().getText());
		target_text.getText().setText(tmp);
	}
	
}