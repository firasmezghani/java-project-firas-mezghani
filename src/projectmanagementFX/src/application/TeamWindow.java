package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class TeamWindow {
    public static void display(List<TeamMember> teamMembers) {
        Stage window = new Stage();
        window.setTitle("Manage Team Members");

        ListView<String> memberListView = new ListView<>();
        TextField memberField = new TextField();
        memberField.setPromptText("Enter Member Name");

        Button addButton = new Button("Add Member");
        Button deleteButton = new Button("Delete Selected");

        addButton.setOnAction(e -> {
            String name = memberField.getText();
            if (!name.isEmpty()) {
                teamMembers.add(new TeamMember(String.valueOf(teamMembers.size() + 1), name));
                memberListView.getItems().add(name);
                memberField.clear();
            }
        });

        deleteButton.setOnAction(e -> {
            String selected = memberListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                teamMembers.removeIf(member -> member.getName().equals(selected));
                memberListView.getItems().remove(selected);
            }
        });

        memberListView.getItems().addAll(teamMembers.stream().map(TeamMember::getName).toList());

        VBox layout = new VBox(10, memberField, addButton, deleteButton, memberListView);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();
    }
}
