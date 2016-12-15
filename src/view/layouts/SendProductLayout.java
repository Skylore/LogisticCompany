package view.layouts;

import controller.ClientController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import gmailApi.SendMailSSL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Product;

import java.awt.*;

public class SendProductLayout {

    private ClientController clientController;

    public SendProductLayout(ClientController clientController) {
        this.clientController = clientController;
    }

    public GridPane getLayout() {
        GridPane sendProductLayout = new GridPane();
        sendProductLayout.setPadding(new Insets(10, 10, 10, 10));
        sendProductLayout.setVgap(8);
        sendProductLayout.setHgap(10);

        Label nameLabel = new Label("Product name: ");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label weightLabel = new Label("Weight : ");
        GridPane.setConstraints(weightLabel, 0, 1);
        TextField weightInput = new TextField();
        GridPane.setConstraints(weightInput, 1, 1);

        Label sizeLabel = new Label("Size(m^2) : ");
        GridPane.setConstraints(sizeLabel, 0, 2);
        TextField sizeInput = new TextField();
        GridPane.setConstraints(sizeInput, 1, 2);

        Label emailLabel = new Label("Email: ");
        GridPane.setConstraints(emailLabel, 0, 3);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 3);

        Label fromLabel = new Label("From: ");
        GridPane.setConstraints(fromLabel, 0, 4);
        ChoiceBox<String> choiceFrom = new ChoiceBox<>();
        DataBase.getDepartments().forEach(department -> choiceFrom.getItems().add(department.getLocation().getFormattedAddress()));
        choiceFrom.getSelectionModel().selectedItemProperty().
                addListener((v, oldValue, newValue) -> System.out.println(v));
        GridPane.setConstraints(choiceFrom, 1, 4);

        Label toLabel = new Label("To: ");
        GridPane.setConstraints(toLabel, 0, 5);
        ChoiceBox<String> choiceTo = new ChoiceBox<>();
        DataBase.getDepartments().forEach(department -> choiceTo.getItems().add(department.getLocation().getFormattedAddress()));
        choiceTo.getSelectionModel().selectedItemProperty().
                addListener((v, oldValue, newValue) -> System.out.println(v));
        GridPane.setConstraints(choiceTo, 1, 5);

        Utils utils = new Utils();

        Label price = new Label();
        GridPane.setConstraints(price, 1, 6);
        Button calculateButton = new Button("Calculate price");
        calculateButton.setOnAction(e -> price.setText(utils.getPrice(choiceFrom.getValue(),
                choiceTo.getValue()) + ""));
        GridPane.setConstraints(calculateButton, 0, 6);

        Label submit = new Label();
        GridPane.setConstraints(submit, 1, 7);
        Button submitButton = new Button("submit");

        submitButton.setOnAction((e) -> {

            final String CHECKING_CODE = "a1mK34f";
            GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

            if (!nameInput.getText().equals("") && !emailInput.getText().equals("")) {

                TextField textField = new TextField();
                textField.setPromptText("checking code");
                GridPane.setConstraints(textField, 1, 7);
                sendProductLayout.getChildren().addAll(textField);
                SendMailSSL.sendLetter(emailInput.getText(), "Delivery company",
                        "your checking code is " + CHECKING_CODE);

                submitButton.setOnAction(event -> {
                    if (textField.getText().equals(CHECKING_CODE)) {
                        clientController.sendProductRequest(new Product(nameInput.getText(),
                                        Integer.valueOf(weightInput.getText()), Integer.valueOf(sizeInput.getText())),
                                emailInput.getText(), googleMapsAPI.findLocation(choiceFrom.getValue()),
                                googleMapsAPI.findLocation(choiceTo.getValue()));

                        sendProductLayout.getChildren().remove(textField);
                    }
                });
            } else {
                AlertBox.display("Please fill all boxes");
            }
        });

        GridPane.setConstraints(submitButton, 0, 7);

        sendProductLayout.getChildren().addAll(nameLabel, nameInput, weightLabel, weightInput,
                sizeLabel, sizeInput, emailLabel, emailInput, choiceFrom, choiceTo, fromLabel,
                toLabel, price, calculateButton, submit, submitButton);
        return sendProductLayout;
    }

    private class Utils {

        double getPrice(String from, String to) {
            GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

            return new GoogleMapsAPIImpl().getDistance(googleMapsAPI.findLocation(from),
                    googleMapsAPI.findLocation(to)) * 0.001;
        }
    }
}
