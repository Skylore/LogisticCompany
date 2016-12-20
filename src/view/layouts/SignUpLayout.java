package view.layouts;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpLayout {

    public static void getLayout(Stage window, Scene scene) {

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
        signUpButton.setOnAction(event -> window.setScene(scene));

        layout.getChildren().addAll(logInLabel, login, emailLabel, email, passLabel, pass, repeatPassLabel,
                repPass, signUpButton);

        Scene signUpScene = new Scene(layout, 760, 475);
        signUpScene.getStylesheets().add("view/style.css");
        window.setScene(signUpScene);

    }

}
