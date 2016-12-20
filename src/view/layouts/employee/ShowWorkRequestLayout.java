package view.layouts.employee;

import controller.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Product;
import model.WorkRequest;

/**
 * Created by Влад on 14.12.2016.
 */
public class ShowWorkRequestLayout {

    private static TableView<WorkRequest> table;

    public static VBox getLayout(AdminController admin){

        //column
        TableColumn<WorkRequest, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<WorkRequest, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<WorkRequest, String> goalColumn = new TableColumn<>("Goal");
        goalColumn.setMinWidth(100);
        goalColumn.setCellValueFactory(new PropertyValueFactory<>("goal"));

        TableColumn<WorkRequest, Integer> salaryColumn = new TableColumn<>("Desired salary");
        salaryColumn.setMinWidth(50);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        table = new TableView<>();
        table.setItems(admin.showAllWorkRequests());
        table.getColumns().addAll(nameColumn, emailColumn, goalColumn, salaryColumn);
        table.setMaxHeight(300);

        //button
        Button confirmButton = new Button("Confirm");     //add logic for button
        confirmButton.setOnAction(e -> {
            admin.confirmWorkRequest(table.getSelectionModel().getSelectedItem());
            table.setItems(admin.showAllWorkRequests());
        });


        VBox layout = new VBox();
        layout.getChildren().addAll(table, confirmButton);
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

}
