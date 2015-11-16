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


public class UnaryDragAndDropManager extends DragAndDropManager {

	Node decoOpen, decoClose;
	private UnaryGraphicExpression unary;
	private ControlTower tower;
	private DragAndDropMemory memory = new DragAndDropMemory(tower);
	
	public UnaryDragAndDropManager(Group group, Node decoOpen, Node decoClose, UnaryGraphicExpression unary, ControlTower tower) {
		super(group);
		this.decoOpen = decoOpen;
		this.decoClose = decoClose;
		this.unary = unary;
		this.tower = tower;
	}

	@Override
	public void onDragDetected() {
		decoOpen.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		    	
		        Dragboard db = decoOpen.startDragAndDrop(TransferMode.MOVE);
		        //Petite icone
//		        db.setDragView(new Text(decoOpen.getText()).snapshot(null, null), event.getX(), event.getY());
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
//		        content.putString(decoOpen.getText());
		        memory.setSource(group);
		        db.setContent(content);
		        event.consume();
		    }
		});
		decoClose.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		    	
		        Dragboard db = decoOpen.startDragAndDrop(TransferMode.MOVE);
		        //Petite icone
//		        db.setDragView(new Text(decoOpen.getText()).snapshot(null, null), event.getX(), event.getY());
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
//		        content.putString(decoOpen.getText());
		        memory.setSource(group);
		        db.setContent(content);
		        event.consume();
		    }
		});
	}

	
	@Override
	public void onDragOver () {
		group.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		            /* allow for moving */
	            event.acceptTransferModes(TransferMode.MOVE);
		        event.consume();
		    }
		});
	}
	
	@Override
	public void onDragEntered () {
		group.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		        memory.setTarget(group);
		    	event.consume();
		    }
		});
	}
	
	@Override
	public void onDragExited () {
		group.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        event.consume();
		    }
		});
	}	
	
	@Override
	public void onDragDropped () {
		group.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        event.consume();
		     }
		});
	}
	
	@Override
	public void onDragDone() {
		decoOpen.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        /* if the data was successfully moved, clear it */
		        if (event.getTransferMode() == TransferMode.MOVE) {
		        	memory.swapText();
		        }
		        event.consume();
		    }
		});
		decoClose.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        /* if the data was successfully moved, clear it */
		        if (event.getTransferMode() == TransferMode.MOVE) {
		        	memory.swapText();
		        }
		        event.consume();
		    }
		});
	}
}
