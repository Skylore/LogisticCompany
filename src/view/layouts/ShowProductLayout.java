package view.layouts;

import controller.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        //table.setItems(admin.showAllWorkRequests());
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, weightColumn, sizeColumn);
        table.setMaxHeight(300);


        VBox layout = new VBox();
        layout.getChildren().addAll(table);

        return layout;
    }

    private static ObservableList<Product> getProduct() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Iphone", 1, 2));
        products.add(new Product("Ipad",2, 2));
        products.add(new Product("Ipod", 3, 3));

        return products;
    }
}
