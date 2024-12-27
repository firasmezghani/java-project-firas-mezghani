package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class AssignTaskWindow {
    public static void display(List<Project> projects, List<TeamMember> teamMembers) {
        Stage window = new Stage();
        window.setTitle("Assign Tasks");

        ComboBox<String> projectBox = new ComboBox<>();
        for (Project project : projects) {
            projectBox.getItems().add(project.getName());
        }

        ComboBox<String> taskBox = new ComboBox<>();
        ComboBox<String> memberBox = new ComboBox<>();

        projectBox.setOnAction(e -> {
            String selectedProject = projectBox.getValue();
            taskBox.getItems().clear();
            for (Project project : projects) {
                if (project.getName().equals(selectedProject)) {
                    for (Task task : project.getTasks()) {
                        taskBox.getItems().add(task.getName());
                    }
                    break;
                }
            }
        });

        for (TeamMember member : teamMembers) {
            memberBox.getItems().add(member.getName());
        }

        Button assignButton = new Button("Assign Task");
        Label resultLabel = new Label();

        assignButton.setOnAction(e -> {
            String selectedTask = taskBox.getValue();
            String selectedMember = memberBox.getValue();

            if (selectedTask != null && selectedMember != null) {
                for (Project project : projects) {
                    for (Task task : project.getTasks()) {
                        if (task.getName().equals(selectedTask)) {
                            for (TeamMember member : teamMembers) {
                                if (member.getName().equals(selectedMember)) {
                                    task.assignMember(member);
                                    resultLabel.setText("Task '" + selectedTask + "' assigned to " + selectedMember);
                                    return;
                                }
                            }
                        }
                    }
                }
            } else {
                resultLabel.setText("Please select a project, task, and member.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("Select Project:"), projectBox, new Label("Select Task:"), taskBox, new Label("Select Member:"), memberBox, assignButton, resultLabel);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }
}
