package view.layouts;

import controller.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Product;
import model.WorkRequest;

/**
 * Created by Влад on 14.12.2016.
 */
public class ShowProductLayout {
    private static TableView<Product> table;

    public static VBox getLayout(AdminController admin){

        //column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> weightColumn = new TableColumn<>("Weight");
        weightColumn.setMinWidth(200);
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Product, Integer> sizeColumn = new TableColumn<>("Size(m^2)");
        sizeColumn.setMinWidth(200);
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));


        table = new TableView<>();
        table.getColumns().addAll(nameColumn, weightColumn, sizeColumn);
        table.setMaxHeight(300);

        TextField idInput = new TextField();
        idInput.setPromptText("Department id");
        idInput.setMaxWidth(150);

        Button showButton = new Button("Show");
        showButton.setOnAction(e ->
                table.getItems().setAll((admin.showProductInTheDepartment(Integer.parseInt(idInput.getText())))));

        VBox layout = new VBox();
        layout.getChildren().addAll(table, idInput, showButton);
        layout.setSpacing(10);

        return layout;
    }

}
