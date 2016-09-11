package controller;

import view.implementation.BinaryGraphicExpression;
import view.implementation.ControlTower;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


/**
 * @author florian Campanella, Rambaldi Thomas, Nicolas Leotier
 * Etend de DragAndDropManager
 * Classe qui implemente les methodes du drag and drop pour les expressions binaires
 */
public class BinaryDragAndDropManager extends DragAndDropManager{


	private BinaryGraphicExpression binary;
	private ControlTower tower;
	
	
	/**
	 * Constructeur pour effectuer un dragAndDrop d'un expression binaire
	 * @param node : le nodee cible
	 * @param binary : l'objet graphique correspondant
	 * @param tower : la controltower
	 */
	public BinaryDragAndDropManager(Node node, BinaryGraphicExpression binary, ControlTower tower) {
		super(node);
		this.binary = binary;
		this.tower = tower;
	}

	/**
	 * @see DragAndDropManager#onDragDetected()
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
		        DragAndDropMemory.memory.setSource(binary);
		        db.setContent(content);
		        event.consume();
		    }
		});
	}
	
	/**
	 * @see DragAndDropManager#onDragOver()
	 */
	@Override
	public void onDragOver () {
		node.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
	            event.acceptTransferModes(TransferMode.MOVE);
		        event.consume();
		    }
		});
	}
	
	/**
	 * @see DragAndDropManager#onDragEntered()
	 */
	@Override
	public void onDragEntered () {
		node.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    	DragAndDropMemory.memory.setTarget(binary);
		    	event.consume();
		    }
		});
	}
	
	/**
	 * @see DragAndDropManager#onDragExited()
	 */
	@Override
	public void onDragExited () {
		node.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        event.consume();
		    }
		});
	}	
	
	/**
	 * @see DragAndDropManager#onDragDropped()
	 */
	@Override
	public void onDragDropped () {
		node.setOnDragDropped(new EventHandler<DragEvent>() {
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
	 * @see DragAndDropManager#onDragDone()
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
