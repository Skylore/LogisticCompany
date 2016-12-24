package view.layouts.client;

import controller.ClientController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import gmailApi.SendMailSSL;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Product;
import view.layouts.AlertBox;
import utils.KeyFactory;

import java.util.UUID;

public class SendProductLayout {

    private ClientController clientController;
    private DataBase dataBase;

    public SendProductLayout(ClientController clientController, DataBase dataBase) {
        this.clientController = clientController;
        this.dataBase = dataBase;
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

        Label fromLabel = new Label("From: ");
        GridPane.setConstraints(fromLabel, 0, 3);
        ChoiceBox<String> choiceFrom = new ChoiceBox<>();
        dataBase.getDepartments().forEach(department -> choiceFrom.getItems().add(department.getLocation().getFormattedAddress()));
        choiceFrom.getSelectionModel().selectedItemProperty().
                addListener((v, oldValue, newValue) -> System.out.println(v));
        GridPane.setConstraints(choiceFrom, 1, 3);

        Label toLabel = new Label("To: ");
        GridPane.setConstraints(toLabel, 0, 4);
        ChoiceBox<String> choiceTo = new ChoiceBox<>();
        dataBase.getDepartments().forEach(department -> choiceTo.getItems().add(department.getLocation().getFormattedAddress()));
        choiceTo.getSelectionModel().selectedItemProperty().
                addListener((v, oldValue, newValue) -> System.out.println(v));
        GridPane.setConstraints(choiceTo, 1, 4);

        Utils utils = new Utils();

        Label price = new Label();
        GridPane.setConstraints(price, 1, 6);
        Button calculateButton = new Button("Calculate price");
        calculateButton.setOnAction(e -> price.setText(String.format("%.2f", utils.getPrice(choiceFrom.getValue(),
                choiceTo.getValue()))));
        GridPane.setConstraints(calculateButton, 0, 6);

        Label submit = new Label();
        GridPane.setConstraints(submit, 1, 7);
        Button submitButton = new Button("submit");

        KeyFactory keyFactory = new KeyFactory();
        submitButton.setOnAction((e) -> {

            final String CHECKING_CODE = UUID.randomUUID().toString();
            GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

            try {
                int weight = Integer.parseInt(weightInput.getText());
                int size = Integer.parseInt(sizeInput.getText());

                if (!nameInput.getText().equals("")) {

                    String email = clientController.getInSystem().getEmail();

                    TextField textField = new TextField();
                    textField.setPromptText("checking code");
                    GridPane.setConstraints(textField, 1, 7);
                    sendProductLayout.getChildren().addAll(textField);
                    SendMailSSL.sendLetter(email, "Delivery company",
                            "your checking code is " + CHECKING_CODE);

                    submitButton.setOnAction(event -> {
                        if (textField.getText().equals(CHECKING_CODE)) {
                            clientController.sendProductRequest(new Product(nameInput.getText(), weight, size),
                                    email, googleMapsAPI.findLocation(choiceFrom.getValue()),
                                    googleMapsAPI.findLocation(choiceTo.getValue()));

                            sendProductLayout.getChildren().remove(textField);
                            nameInput.setText("");
                            weightInput.setText("");
                            sizeInput.setText("");
                            price.setText("");
                            choiceTo.setValue(null);
                            choiceFrom.setValue(null);
                        }
                    });
                }

            } catch (Exception e1) {
                AlertBox.display("Wrong input");
            }
        });


        GridPane.setConstraints(submitButton, 0, 7);

        sendProductLayout.getChildren().addAll(nameLabel, nameInput, weightLabel, weightInput,
                sizeLabel, sizeInput, choiceFrom, choiceTo, fromLabel,
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
