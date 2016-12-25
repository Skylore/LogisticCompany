package view.layouts.client;

import controller.ClientController;
import gmailApi.SendMailSSL;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import utils.SecurityUtils;
import view.layouts.AlertBox;

import java.util.UUID;

class PersonalCabinetLayout {

    public GridPane getLayout(ClientController clientController, Scene scene, Stage stage) {
        GridPane cabinetLayout = new GridPane();
        cabinetLayout.setPadding(new Insets(10, 10, 10, 10));
        cabinetLayout.setVgap(8);
        cabinetLayout.setHgap(10);

        User user = clientController.getInSystem();

        Label loginLabel = new Label("Login   ");
        Label login = new Label(user.getLogin());
        GridPane.setConstraints(loginLabel, 0, 0);
        GridPane.setConstraints(login, 1, 0);

        Label emailLabel = new Label("Email   ");
        Label email = new Label(user.getEmail());
        GridPane.setConstraints(emailLabel, 0, 2);
        GridPane.setConstraints(email, 1, 2);

        Button changePassword = new Button("change password");
        GridPane.setConstraints(changePassword, 0, 4);

        changePassword.setOnAction((e) -> {
            TextField passInput = new TextField();
            passInput.setPromptText("Input new password");
            GridPane.setConstraints(passInput, 1, 4);
            cabinetLayout.getChildren().addAll(passInput);

            changePassword.setOnAction((e1) -> {
                if (!passInput.getText().equals("")) {
                    try {
                        clientController.updatePass(SecurityUtils.hashMD5(passInput.getText()), user.getLogin());
                        cabinetLayout.getChildren().remove(passInput);
                    } catch (Exception e2) {
                        AlertBox.display("Invalid input");
                    }
                } else {
                    AlertBox.display("Please input new password");
                }
            });
        });

        Button changeEmail = new Button("change email");
        GridPane.setConstraints(changeEmail, 0, 5);

        changeEmail.setOnAction((e) -> {
            TextField emailInput = new TextField();
            emailInput.setPromptText("Input new email");
            GridPane.setConstraints(emailInput, 1, 5);
            cabinetLayout.getChildren().addAll(emailInput);

            changeEmail.setOnAction((e1) -> {
                if (!emailInput.getText().equals("")) {
                    String mail = emailInput.getText();
                    final String VERIFY = UUID.randomUUID().toString();

                    SendMailSSL.sendLetter(user.getEmail(), "Delivery company",
                            "confirm that your new email is " + mail + "\nverify code is " + VERIFY);

                    changeEmail.setText("Confirm");
                    emailInput.setText("");
                    emailInput.setPromptText("Input verify code");

                    changeEmail.setOnAction((e2) -> {
                        if (emailInput.getText().equals(VERIFY)) {
                            try {
                                clientController.updateEmail(emailInput.getText(), user.getLogin());
                                email.setText(mail);
                                cabinetLayout.getChildren().remove(emailInput);

                                SendMailSSL.sendLetter(mail, "Delivery company",
                                        "your mail address has been successfully changed!");

                                changeEmail.setText("change email");
                            } catch (Exception e3) {
                                AlertBox.display("Invalid input");
                            }
                        }
                    });
                } else {
                    AlertBox.display("Please input your email");
                }
            });
        });

        Button logOutButton = new Button("log out");
        GridPane.setConstraints(logOutButton, 0, 6);
        logOutButton.setOnAction((e) -> stage.setScene(scene));

        cabinetLayout.getChildren().addAll(loginLabel, login,
                emailLabel, email, changeEmail, changePassword, logOutButton);
        return cabinetLayout;
    }
}
