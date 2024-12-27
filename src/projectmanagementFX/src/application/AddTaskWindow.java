package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddTaskWindow {
    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Task");

        Label label = new Label("Enter Task Name:");
        TextField taskNameField = new TextField();

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            String taskName = taskNameField.getText();
            if (!taskName.isEmpty()) {
                System.out.println("Task Added: " + taskName);
                window.close();
            } else {
                System.out.println("Task name cannot be empty.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(label, taskNameField, addButton);

        Scene scene = new Scene(layout, 300, 150);
        window.setScene(scene);
        window.showAndWait();
    }
}

