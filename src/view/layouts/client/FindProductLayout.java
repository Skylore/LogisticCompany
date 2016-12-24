package view.layouts.client;

import controller.ClientController;
import dao.ControllerFactory;
import database.DataBase;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import view.layouts.AlertBox;

public class FindProductLayout  {

    ClientController clientController;
    DataBase dataBase;

    public FindProductLayout(ClientController clientController, DataBase dataBase) {
        this.clientController = clientController;
        this.dataBase = dataBase;
    }

    public GridPane getLayout(){
        GridPane findProduct = new GridPane();
        findProduct.setPadding(new Insets(10, 10, 10, 10));
        findProduct.setVgap(8);
        findProduct.setHgap(10);

        Label idLabel = new Label("Product id: ");
        GridPane.setConstraints(idLabel, 0, 0);

        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label locationLabel = new Label();
        GridPane.setConstraints(locationLabel, 1, 1);

        Button button = new Button("Find product");
        button.setOnAction(e -> {

            try {
                if (!idInput.getText().equals("")) {
                    if (dataBase.getRequests().containsKey(Integer.valueOf(idInput.getText()))) {
                        AlertBox.display("Incorrect input");
                    } else {
                        locationLabel.setText(clientController.whereIsMyProduct(Integer.parseInt(idInput.getText())));
                    }
                } else {
                    AlertBox.display("Please input id");
                }
            } catch (Exception e1) {
                AlertBox.display("wrong input");
            }
        });
        GridPane.setConstraints(button, 0, 1);

        findProduct.getChildren().addAll(idInput, idLabel, button, locationLabel);
        return findProduct;
    }
}
