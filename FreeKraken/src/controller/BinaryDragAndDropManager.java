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



public class BinaryDragAndDropManager extends DragAndDropManager{

	private BinaryGraphicExpression binary;
	private ControlTower tower;
	
	
	public BinaryDragAndDropManager(Group group, BinaryGraphicExpression binary, ControlTower tower) {
		super(group);
		this.binary = binary;
		this.tower = tower;
	}

	@Override
	public void onDragDetected() {
		binary.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		    	
		        Dragboard db = binary.startDragAndDrop(TransferMode.MOVE);
		        //Petite icone
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
		    	DragAndDropMemory.memory.setTarget(group);
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
		binary.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        /* if the data was successfully moved, clear it */
		        if (event.getTransferMode() == TransferMode.MOVE) {
		        	DragAndDropMemory.memory.notifyDragAndDrop();
		        }
		        event.consume();
		    }
		});
	}
	
}
