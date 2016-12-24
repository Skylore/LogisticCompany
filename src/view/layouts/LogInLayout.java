package view.layouts;

import controller.ClientController;
import database.ControllerFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.layouts.client.ClientView;
import view.layouts.employee.AsEmployeeLayout;

public class LogInLayout extends Application {

    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ControllerFactory controllerFactory = new ControllerFactory();

        ClientView clientView = new ClientView(controllerFactory);
        AsEmployeeLayout asEmployeeLayout = new AsEmployeeLayout(controllerFactory);

        Stage window = primaryStage;
        window.setTitle("Logistic Company");

        //top menu
        StackPane topMenu = new StackPane();
        topMenu.setAlignment(Pos.TOP_RIGHT);
        topMenu.setPadding(new Insets(15, 15, 15, 15));
        Button employee = new Button("Employee");
        employee.setOnAction(event -> asEmployeeLayout.getLayout(window, scene));
        topMenu.getChildren().addAll(employee);

        //center menu
        VBox centerMenu = new VBox();
        centerMenu.setSpacing(10);
        centerMenu.setAlignment(Pos.CENTER);

        ClientController clientController = (ClientController)
                controllerFactory.getController("ClientController");

        Label logInLabel = new Label("Your login: ");
        Label passLabel = new Label("Your pass: ");
        TextField logIn = new TextField();
        logIn.setMaxWidth(150);
        TextField pass = new TextField();
        pass.setMaxWidth(150);
        Button logInButton = new Button("Log in");
        logInButton.setOnAction(e -> {
            if (!logIn.getText().equals("") && !pass.getText().equals("")) {
                try {
                    clientController.logIn(logIn.getText(), pass.getText());
                    clientView.getLayout(window, scene, clientController);
                } catch (Exception e1) {
                    e1.printStackTrace();

                    logIn.setText("");
                    pass.setText("");
                }
            } else {
                AlertBox.display("Please fill up all fields");
            }
        });

        SignUpLayout signUpLayout = new SignUpLayout((ClientController)
                controllerFactory.getController("ClientController"));

        Button signUpButton = new Button("Sign up");
        signUpButton.setOnAction(event -> signUpLayout.getLayout(window, scene));

        centerMenu.getChildren().addAll(logInLabel, logIn, passLabel, pass, logInButton, signUpButton);

        BorderPane layout = new BorderPane();
        layout.setTop(topMenu);
        layout.setCenter(centerMenu);
        scene = new Scene(layout, 760, 475);
        scene.getStylesheets().add("view/style.css");
        window.setScene(scene);
        window.show();
    }
}
