package controller;

import view.PrimaryGraphicExpression;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;
import javafx.scene.Group;



/**
 * 
 * @author florian campanella, Thomas Rambaldi
 *
 */
public class DragAndDropManagment {
	
	Text expr;
	Group group;
	
	public DragAndDropManagment(Group group, Text expr) {
		this.expr = expr;
		this.group = group;
	}

	

	/**
	 * detection du D&D 
	 */
	public void onDragDetected() {
		expr.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		    	
		        Dragboard db = expr.startDragAndDrop(TransferMode.MOVE);
		        //Petite icone
		        db.setDragView(new Text(expr.getText()).snapshot(null, null), event.getX(), event.getY());
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
		        content.putString(expr.getText());
		        DragAndDropMemory.memory.setSource(thisInstance);
		        db.setContent(content);
		        event.consume();
		    }
		});
	}
	
	/**
	 * On se deplace une fois le D&D detecter
	 */
	public void onDragOver () {
		thisInstance.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		            /* allow for moving */
	            event.acceptTransferModes(TransferMode.MOVE);
		        event.consume();
		    }
		});
	}
	
	public void onDragEntered () {
		thisInstance.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		        DragAndDropMemory.memory.setTarget(thisInstance);
		    	event.consume();
		    }
		});
	}
	
	public void onDragExited () {
		thisInstance.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        event.consume();
		    }
		});
	}	
	
	public void onDragDropped () {
		thisInstance.setOnDragDropped(new EventHandler<DragEvent>() {
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
	
	public void onDragDone() {
		expr.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        /* if the data was successfully moved, clear it */
		        if (event.getTransferMode() == TransferMode.MOVE) {
		        	DragAndDropMemory.memory.swapText();
		        }
		        event.consume();
		    }
		});
	}
	
	public void onDragAndDropEvent() {
		onDragDetected();
		onDragOver();
		onDragEntered();
		onDragExited();
		onDragDropped();
		onDragDone();
	}
	
}
