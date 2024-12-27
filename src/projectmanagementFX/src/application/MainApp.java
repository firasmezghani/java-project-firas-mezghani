package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {
    private List<Project> projects = new ArrayList<>();
    private List<TeamMember> teamMembers = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Button projectButton = new Button("Manage Projects");
        Button taskButton = new Button("Manage Tasks");
        Button teamButton = new Button("Manage Team Members");
        Button assignTaskButton = new Button("Assign Tasks");
        Button modifyStatusButton = new Button("Modify Task Status");
        Button reportButton = new Button("Generate Reports");
        Button exitButton = new Button("Exit");

        projectButton.setOnAction(e -> ProjectWindow.display(projects));
        taskButton.setOnAction(e -> TaskWindow.display(projects));
        teamButton.setOnAction(e -> TeamWindow.display(teamMembers));
        assignTaskButton.setOnAction(e -> AssignTaskWindow.display(projects, teamMembers));
        modifyStatusButton.setOnAction(e -> ModifyTaskStatusWindow.display(getAllTasks()));
        reportButton.setOnAction(e -> ReportWindow.display(projects));
        exitButton.setOnAction(e -> primaryStage.close());

        Label poweredByLabel = new Label("Powered by Firas Mezghani");
        poweredByLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray; -fx-padding: 10;");

        VBox layout = new VBox(10, projectButton, taskButton, teamButton, assignTaskButton, modifyStatusButton, reportButton, exitButton, poweredByLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 520);
        primaryStage.setTitle("Project Management Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        for (Project project : projects) {
            allTasks.addAll(project.getTasks());
        }
        return allTasks;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
