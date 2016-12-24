package view.layouts;

import controller.ClientController;
import dao.ControllerFactory;
import database.Converter;
import database.Logger;
import exceptions.BookedLoginException;
import gmailApi.SendMailSSL;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.UUID;

public class SignUpLayout {

    private ClientController clientController;
    private ControllerFactory controllerFactory;

    public SignUpLayout(ClientController clientController, ControllerFactory controllerFactory) {
        this.clientController = clientController;
        this.controllerFactory = controllerFactory;
    }

    public void getLayout(Stage window, Scene scene) {

        VBox layout = new VBox();
        layout.setSpacing(5);
        layout.setAlignment(Pos.CENTER);

        Label logInLabel = new Label("Your login: ");
        Label emailLabel = new Label("Your email: ");
        Label passLabel = new Label("Your pass: ");
        Label repeatPassLabel = new Label("Repeat pass: ");
        TextField login = new TextField();
        login.setMaxWidth(150);
        TextField email = new TextField();
        email.setMaxWidth(150);
        TextField pass = new TextField();
        pass.setMaxWidth(150);
        TextField repPass = new TextField();
        repPass.setMaxWidth(150);
        Button signUpButton = new Button("Sign up");
        signUpButton.setOnAction((event) -> {
            if (!login.getText().equals("") && !email.getText().equals("") && pass.getText().equals(repPass.getText())) {

                String emailU = email.getText();

                layout.getChildren().retainAll(signUpButton);

                final String VERIFY_CODE = UUID.randomUUID().toString();
                SendMailSSL.sendLetter(emailU, "Delivery company", "your verify code is  " +
                        VERIFY_CODE);

                TextField verifyInput = new TextField();
                verifyInput.setPromptText("Enter checking code");
                verifyInput.setMaxWidth(150);
                layout.getChildren().addAll(verifyInput);

                signUpButton.setOnAction((event1 -> {
                    if (verifyInput.getText().equals(VERIFY_CODE)) {

                        try {
                            clientController.registration(email.getText(), login.getText(), pass.getText());
                        } catch (BookedLoginException e) {
                            e.printStackTrace();
                        }
                        new Logger().write(Converter.toJson(controllerFactory.getDataBase()));
                        SendMailSSL.sendLetter(email.getText(), "Delivery company",
                                "Registration has been successfully passed\nyour login is : " + login.getText());
                        window.setScene(scene);
                    }
                }));

            } else {
                AlertBox.display("Please fill in all boxes");
            }
        });

        layout.getChildren().addAll(logInLabel, login, emailLabel, email, passLabel, pass, repeatPassLabel,
                repPass, signUpButton);

        Scene signUpScene = new Scene(layout, 760, 475);
        signUpScene.getStylesheets().add("view/style.css");
        window.setScene(signUpScene);

    }

}
