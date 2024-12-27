package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class ModifyTaskStatusWindow {
    public static void display(List<Task> tasks) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modify Task Status");

        // UI Elements
        Label taskLabel = new Label("Select Task:");
        ComboBox<String> taskBox = new ComboBox<>();
        for (Task task : tasks) {
            taskBox.getItems().add(task.getName() + " [" + task.getStatus() + "]");
        }

        Label statusLabel = new Label("Select New Status:");
        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("PENDING", "IN_PROGRESS", "COMPLETED");

        Button updateButton = new Button("Update Status");
        Label resultLabel = new Label();

        updateButton.setOnAction(e -> {
            String selectedTask = taskBox.getValue();
            String newStatus = statusBox.getValue();

            if (selectedTask != null && newStatus != null) {
                String taskName = selectedTask.split(" \\[")[0];
                Task.Status statusEnum = Task.Status.valueOf(newStatus);

                for (Task task : tasks) {
                    if (task.getName().equals(taskName)) {
                        task.updateStatus(statusEnum);
                        resultLabel.setText("Task '" + taskName + "' updated to status: " + newStatus);
                        break;
                    }
                }

                taskBox.getItems().clear();
                for (Task task : tasks) {
                    taskBox.getItems().add(task.getName() + " [" + task.getStatus() + "]");
                }
            } else {
                resultLabel.setText("Please select a task and a status.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(taskLabel, taskBox, statusLabel, statusBox, updateButton, resultLabel);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
