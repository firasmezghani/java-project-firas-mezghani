package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ProjectWindow {
    public static void display(List<Project> projects) {
        Stage window = new Stage();
        window.setTitle("Manage Projects");

        TextField projectNameField = new TextField();
        projectNameField.setPromptText("Enter project name");

        Button addButton = new Button("Add Project");
        addButton.setOnAction(e -> {
            String projectName = projectNameField.getText();
            if (!projectName.isEmpty()) {
                projects.add(new Project(projectName));
                System.out.println("Project added: " + projectName);
                projectNameField.clear();
            }
        });

        VBox layout = new VBox(10, projectNameField, addButton);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 300, 200);

        window.setScene(scene);
        window.show();
    }
}
