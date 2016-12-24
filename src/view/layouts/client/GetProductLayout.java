package view.layouts.client;

import controller.ClientController;
import database.DataBase;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Product;
import view.layouts.AlertBox;

public class GetProductLayout {

    private ClientController clientController;
    private DataBase dataBase;


    public GetProductLayout(ClientController clientController, DataBase dataBase) {
        this.clientController = clientController;
        this.dataBase = dataBase;
    }

    public GridPane getLayout() {
        GridPane getProductLayout = new GridPane();
        getProductLayout.setPadding(new Insets(10, 10, 10, 10));
        getProductLayout.setVgap(8);
        getProductLayout.setHgap(10);

        Label idLabel = new Label("Product id: ");
        GridPane.setConstraints(idLabel, 0, 0);

        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 1);

        Button button = new Button("Get product");
        button.setOnAction(e -> {
            try {
                if (!dataBase.getRequests().containsKey(Integer.valueOf(idInput.getText()))) {
                    AlertBox.display("Incorrect id input");
                } else if (clientController.whereIsMyProduct(Integer.valueOf(idInput.getText())).
                        equals("Your product delivered")) {

                    Product product = clientController.getProduct(Integer.valueOf(idInput.getText()));

                    resultLabel.setText("name " + product.getName() + "\nweight " + product.getWeight() +
                            "\nsize " + product.getSize());
                } else {
                    resultLabel.setText(clientController.whereIsMyProduct(Integer.valueOf(idInput.getText())));
                }
            } catch (Exception e1) {
                AlertBox.display("wrong input");
            }
        });
        GridPane.setConstraints(button, 0, 1);

        getProductLayout.getChildren().addAll(idInput, idLabel, button, resultLabel);
        return getProductLayout;
    }

}
