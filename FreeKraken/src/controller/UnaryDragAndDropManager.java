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
 * @author florian Campanella, Thomas Rambaldi, Nicolas Leotier
 * etend DragAndDropManager
 * implemente les methodes de drag and drop pour le UnaryGraphicExpression
 */
public class UnaryDragAndDropManager extends DragAndDropManager {

	
	Node decoOpen, decoClose;
	private UnaryGraphicExpression unary;
	private ControlTower tower;
	
	
	
	/**
	 * constructeur
	 * @param group : le group
	 * @param decoOpen  : la première décoration
	 * @param decoClose : la seconde décoration
	 * @param unary : l'expression graphique
	 * @param tower : la controltower
	 */
	public UnaryDragAndDropManager(Group group, Node decoOpen, Node decoClose, UnaryGraphicExpression unary, ControlTower tower) {
		super(group);
		this.decoOpen = decoOpen;
		this.decoClose = decoClose;
		this.unary = unary;
		this.tower = tower;
	}

	/**
	 * @see DragAndDropManager#onDragDetected()
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
	 * @see DragAndDropManager#onDragOver()
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
	 * @see DragAndDropManager#onDragEntered()
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
	 * @see DragAndDropManager#onDragExited()
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
	 * @see DragAndDropManager#onDragDropped()
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
	 * @see DragAndDropManager#onDragDone()
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
