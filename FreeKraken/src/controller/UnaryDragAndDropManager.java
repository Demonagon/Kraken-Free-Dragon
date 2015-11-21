package controller;

import view.implementation.ControlTower;
import view.implementation.UnaryGraphicExpression;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;



/**
 * 
 * @author florian Campanella, Thomas Rambaldi, Nicolas Leotier
 *
 * implemente les methodes de drag and drop pour le UnaryGraphicExpression
 * on y trouve les methodes 
 * 		onDragDetected();
 *		onDragOver();
 *		onDragEntered();
 * 		onDragExited();
 *		onDragDropped();
 *		onDragDone();
 * -> DragAndDropManager étant une classe abstraite
 */
public class UnaryDragAndDropManager extends DragAndDropManager {

	
	Node decoOpen, decoClose;
	private UnaryGraphicExpression unary;
	private ControlTower tower;
	
	
	
	/**
	 * constructeur
	 * @param group type:Group -> import javafx.scene.Group;
	 * @param decoOpen type:Node -> import javafx.scene.Node;
	 * @param decoClose type:Node -> import javafx.scene.Node;
	 * @param unary type:UnaryGrapicExpression -> import view.implementation.UnaryGraphicExpression;
	 * @param tower type:ControlTower -> import view.implementation.ControlTower;
	 */
	public UnaryDragAndDropManager(Group group, Node decoOpen, Node decoClose, UnaryGraphicExpression unary, ControlTower tower) {
		super(group);
		this.decoOpen = decoOpen;
		this.decoClose = decoClose;
		this.unary = unary;
		this.tower = tower;
	}

	/**
	 * voir la doc de la classe abstraite DragAndDropManager
	 */
	@Override
	public void onDragDetected() {
		decoOpen.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        Dragboard db = decoOpen.startDragAndDrop(TransferMode.MOVE);
		        ClipboardContent content = new ClipboardContent();
		        DragAndDropMemory.memory.setSource(group);
		        db.setContent(content);
		        event.consume();
		    }
		});
		decoClose.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        Dragboard db = decoOpen.startDragAndDrop(TransferMode.MOVE);
		        ClipboardContent content = new ClipboardContent();
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
		        /* data dropped */
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
		decoOpen.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        if (event.getTransferMode() == TransferMode.MOVE) {
		        	DragAndDropMemory.memory.notifyDragAndDrop();
		        }
		        event.consume();
		    }
		});
		decoClose.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        if (event.getTransferMode() == TransferMode.MOVE) {
		        	DragAndDropMemory.memory.notifyDragAndDrop();
		        }
		        event.consume();
		    }
		});
	}
}
