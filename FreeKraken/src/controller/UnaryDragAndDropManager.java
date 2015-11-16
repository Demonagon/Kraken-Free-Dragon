package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;

public class UnaryDragAndDropManager extends DragAndDropManager {

	Node decoOpen, decoClose;
	
	public UnaryDragAndDropManager(Group group, Node decoOpen, Node decoClose) {
		super(group);
		this.decoOpen = decoOpen;
		this.decoClose = decoClose;
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
		        DragAndDropMemory.memory.setSource(group);
		        db.setContent(content);
		        event.consume();
		    }
		});
	}

	@Override
	public void onDragOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragEntered() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragExited() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragDropped() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragDone() {
		// TODO Auto-generated method stub
		
	}

}
