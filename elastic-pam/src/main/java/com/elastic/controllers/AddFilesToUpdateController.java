package com.elastic.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

public class AddFilesToUpdateController extends MainController {

	@FXML
	StackPane dragPane;

	@FXML
	public void onDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		event.consume();
	}

	@FXML
	public void onDragEntered(DragEvent event) {
		dragPane.setStyle("-fx-background-color: #C5CAE9");
		event.consume();
	}

	@FXML
	public void onDragExited(DragEvent event) {
		dragPane.setStyle("-fx-background-color: #FFFFFF");
		event.consume();
	}

	@FXML
	public void onDragDropped(DragEvent event) {
		/* data dropped */
		System.out.println("onDragDropped");
		/* if there is a string data on dragboard, read it and use it */
		Dragboard db = event.getDragboard();

		dragPane.setStyle("-fx-background-color: #888888");
		event.setDropCompleted(true);

		event.consume();
	}

	@FXML
	public void onDragDone(DragEvent event) {
		/* if the data was successfully moved, clear it */
		if (event.getTransferMode() == TransferMode.MOVE) {
			dragPane.setStyle("-fx-background-color: #000080");
		}

		event.consume();
	}
}