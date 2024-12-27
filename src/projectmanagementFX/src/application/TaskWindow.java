package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class TaskWindow {
    public static void display(List<Project> projects) {
        Stage window = new Stage();
        window.setTitle("Manage Tasks");

        ComboBox<String> projectBox = new ComboBox<>();
        for (Project project : projects) {
            projectBox.getItems().add(project.getName());
        }

        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Enter task name");

        Button addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(e -> {
            String selectedProject = projectBox.getValue();
            String taskName = taskNameField.getText();

            if (selectedProject != null && !taskName.isEmpty()) {
                for (Project project : projects) {
                    if (project.getName().equals(selectedProject)) {
                        project.addTask(new Task(taskName));
                        System.out.println("Task added: " + taskName + " to project " + selectedProject);
                        taskNameField.clear();
                        break;
                    }
                }
            }
        });

        VBox layout = new VBox(10, projectBox, taskNameField, addTaskButton);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 400, 250);

        window.setScene(scene);
        window.show();
    }
}
