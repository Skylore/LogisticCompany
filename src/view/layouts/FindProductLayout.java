package view.layouts;

import controller.ClientController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FindProductLayout  {

    private ClientController clientController;

    public FindProductLayout(ClientController clientController) {
        this.clientController = clientController;
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
                    if (Integer.valueOf(idInput.getText()) >= ClientController.getId()) {
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
