package controller;

import view.implementation.BinaryGraphicExpression;
import view.implementation.ControlTower;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


/**
 * 
 * @author florian Campanella, Rambaldi Thomas, Nicolas Leotier
 * Classe qui implemente les methodes du drag&drop pour les expressions binaires
 * on y trouve les methodes 
 * 		onDragDetected();
 *		onDragOver();
 *		onDragEntered();
 * 		onDragExited();
 *		onDragDropped();
 *		onDragDone();
 * -> DragAndDropManager étant une classe abstraite
 */
public class BinaryDragAndDropManager extends DragAndDropManager{

	private BinaryGraphicExpression binary;
	private ControlTower tower;
	
	
	/**
	 * Constructeur pour effectuer un dragAndDrop d'un expression binaire
	 * @param group type:groupe -> import javafx.scene.Group;
	 * @param binary type:BinaryGraphicExpression -> import view.implementation.BinaryGraphicExpression;
	 * @param tower type:controleTower -> import view.implementation.ControlTower;
	 */
	public BinaryDragAndDropManager(Group group, BinaryGraphicExpression binary, ControlTower tower) {
		super(group);
		this.binary = binary;
		this.tower = tower;
	}

	/**
	 * voir la doc de la classe abstraite DragAndDropManager
	 */
	@Override
	public void onDragDetected() {
		binary.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		    	
		        Dragboard db = binary.startDragAndDrop(TransferMode.MOVE);
		        //Petite icone au suivie de la souris
//		        db.setDragView(new Text(binary.getText()).snapshot(null, null), event.getX(), event.getY());
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
//		        content.putString(binary.getText());
		        DragAndDropMemory.memory.setSource(group);
		        db.setContent(content);
		        event.consume();
		    }
		});
	}
	
	/**
	 * voir la doc de la classe abstraite DragAndDropManager
	 */
	@Override
	public void onDragOver () {
		group.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
	            event.acceptTransferModes(TransferMode.MOVE);
		        event.consume();
		    }
		});
	}
	
	/**
	 * voir la doc de la classe abstraite DragAndDropManager
	 */
	@Override
	public void onDragEntered () {
		group.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    	DragAndDropMemory.memory.setTarget(group);
		    	event.consume();
		    }
		});
	}
	
	/**
	 * voir la doc de la classe abstraite DragAndDropManager
	 */
	@Override
	public void onDragExited () {
		group.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        event.consume();
		    }
		});
	}	
	
	/**
	 * voir la doc de la classe abstraite DragAndDropManager
	 */
	@Override
	public void onDragDropped () {
		group.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		           success = true;
		        }
		        event.setDropCompleted(success);
		        event.consume();
		     }
		});
	}
	
	/**
	 * voir la doc de la classe abstraite DragAndDropManager
	 */
	@Override
	public void onDragDone() {
		binary.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        if (event.getTransferMode() == TransferMode.MOVE) {
		        	DragAndDropMemory.memory.notifyDragAndDrop();
		        }
		        event.consume();
		    }
		});
	}
	
}
