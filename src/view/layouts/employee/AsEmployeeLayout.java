package view.layouts.employee;

import controller.*;
import database.DataBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.layouts.AlertBox;

public class AsEmployeeLayout {

    public void getLayout(Stage window, Scene scene){

        DataBase dataBase = DataBase.getInstance();

        AdminController adminController = new AdminController(dataBase);
        SupportController supportController = new SupportController(dataBase);
        BuilderController builderController = new BuilderController(dataBase);
        CourierController courierController = new CourierController(dataBase);

        // center menu
        Label labelLogIn = new Label("Log in as an employee");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Admin", "Builder", "Courier", "Support");
        choiceBox.setValue("Admin");

        TextField pass = new TextField();
        pass.setMaxWidth(150);
        pass.setPromptText("password");

        Button buttonLogIn = new Button("Log in");
        buttonLogIn.setOnAction(e -> {

            if (choiceBox.getValue().equals("Admin")) {

                try {
                    adminController.checkIn(pass.getText());
                    AdminLayout.getLayout(window, scene, adminController);
                } catch (IllegalAccessException e1) {
                    AlertBox.display("Wrong password!");
                }
            }

            if (choiceBox.getValue().equals("Builder")) {
                try {
                    builderController.checkIn(pass.getText());
                    BuilderLayout.getLayout(window, scene, builderController);
                } catch (IllegalAccessException e1) {
                    AlertBox.display("Wrong password!");
                }
            }

            if (choiceBox.getValue().equals("Courier")) {
                try {
                    courierController.checkIn(pass.getText());
                    CourierLayout.getLayout(window, scene, courierController);
                } catch (IllegalAccessException e1) {
                    AlertBox.display("Wrong password!");
                }
            }

            if (choiceBox.getValue().equals("Support")) {
                try {
                    supportController.checkIn(pass.getText());
                    SupportLayout.getLayout(window, scene, supportController);
                } catch (IllegalAccessException e1) {
                    AlertBox.display("Wrong password!");
                }
            }

        });

        VBox center = new VBox();
        center.getChildren().addAll(labelLogIn, choiceBox, pass, buttonLogIn);
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);

        //top menu
        Button logOut = new Button("Log out");
        logOut.setOnAction(event -> window.setScene(scene));
        StackPane top = new StackPane(logOut);
        top.setAlignment(Pos.TOP_RIGHT);
        top.setPadding(new Insets(10, 10, 10, 10));

        BorderPane layout = new BorderPane();
        layout.setCenter(center);
        layout.setTop(top);

        Scene empScene = new Scene(layout, 760, 475);
        empScene.getStylesheets().add("view/style.css");
        window.setScene(empScene);
    }

}
