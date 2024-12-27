package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.util.List;

public class ReportWindow {

    public static void display(List<Project> projects) {
        Stage window = new Stage();
        window.setTitle("Generate Reports");

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));

        Label header = new Label("Project Reports");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        mainLayout.getChildren().add(header);

        for (Project project : projects) {
            if (!project.getTasks().isEmpty()) {
                Label projectLabel = new Label("Project: " + project.getName());
                projectLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

                PieChart pieChart = createPieChartForProject(project);

                VBox taskDetailsBox = new VBox(10); 
                for (Task task : project.getTasks()) {
                    Label taskLabel = new Label("Task: " + task.getName());
                    taskLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

                    Label statusLabel = new Label("   Status: " + task.getStatus());
                    Label membersLabel = new Label("   Assigned Members: " + formatMembers(task));

                    taskDetailsBox.getChildren().addAll(taskLabel, statusLabel, membersLabel);
                }

                                VBox projectBox = new VBox(10, projectLabel, pieChart, taskDetailsBox);
                projectBox.setPadding(new Insets(10));
                projectBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px; -fx-padding: 10;");

                mainLayout.getChildren().add(projectBox);
            }
        }

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 800, 600); // Window size
        window.setScene(scene);
        window.show();
    }

    private static PieChart createPieChartForProject(Project project) {
        long pending = project.getTasks().stream().filter(t -> t.getStatus() == Task.Status.PENDING).count();
        long inProgress = project.getTasks().stream().filter(t -> t.getStatus() == Task.Status.IN_PROGRESS).count();
        long completed = project.getTasks().stream().filter(t -> t.getStatus() == Task.Status.COMPLETED).count();

        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(
                new PieChart.Data("Pending", pending),
                new PieChart.Data("In Progress", inProgress),
                new PieChart.Data("Completed", completed)
        );
        pieChart.setTitle("Task Status Distribution");
        pieChart.setLegendVisible(true);
        pieChart.setLabelsVisible(true);
        pieChart.setPrefSize(300, 300);

        return pieChart;
    }

    private static String formatMembers(Task task) {
        if (task.getAssignedMembers() == null || task.getAssignedMembers().isEmpty()) {
            return "None";
        }
        return String.join(", ", task.getAssignedMembers().stream().map(TeamMember::getName).toList());
    }
}
