package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewTasksWindow {
    @SuppressWarnings("unchecked") 
    public static void display() {
        Stage window = new Stage();
        window.setTitle("View Tasks");

        TableView<Task> table = new TableView<>();

        TableColumn<Task, String> nameColumn = new TableColumn<>("Task Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Task, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Task, Long> timeColumn = new TableColumn<>("Time Spent (sec)");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("totalTime"));

        table.getColumns().addAll(nameColumn, statusColumn, timeColumn);

        ObservableList<Task> tasks = FXCollections.observableArrayList(
            new Task("Task 1"),
            new Task("Task 2")
        );
        table.setItems(tasks);

        VBox layout = new VBox(10, table);
        Scene scene = new Scene(layout, 400, 300);

        window.setScene(scene);
        window.show();
    }
}
