package view.layouts;

import controller.*;
import dao.ControllerFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.layouts.employee.AdminLayout;
import view.layouts.employee.BuilderLayout;
import view.layouts.employee.CourierLayout;
import view.layouts.employee.SupportLayout;

public class AsEmployeeLayout {

    public static void getLayout(Stage window, Scene scene){

        ControllerFactory controllerFactory = new ControllerFactory();

        AdminController adminController = (AdminController) controllerFactory.getController("AdminController");
        SupportController supportController = (SupportController) controllerFactory.getController("SupportController");
        BuilderController builderController = (BuilderController) controllerFactory.getController("BuilderController");
        CourierController courierController = (CourierController) controllerFactory.getController("CourierController");

        // log in for employee
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

        VBox layout = new VBox();
        layout.getChildren().addAll(labelLogIn, choiceBox, pass, buttonLogIn);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        Scene empScene = new Scene(layout, 760, 475);
        empScene.getStylesheets().add("view/style.css");
        window.setScene(empScene);
    }

}
