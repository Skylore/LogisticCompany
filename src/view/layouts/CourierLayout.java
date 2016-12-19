package view.layouts;


import controller.BuilderController;
import controller.CourierController;
import controller.SupportController;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;
import model.Request;
import model.SupportRequest;

public class CourierLayout {

    public static void getLayout(Stage window, Scene scene, CourierController courier) {

        VBox courierLayout = new VBox();
        courierLayout.setPadding(new Insets(10, 10, 10, 10));
        courierLayout.setSpacing(10);
        courierLayout.setAlignment(Pos.CENTER);

        //choice box
        ChoiceBox<String> idBox = new ChoiceBox<>();
        idBox.getItems().addAll(courier.getIdRequests());

        //deliver
        Button deliveryButton = new Button("Deliver Product");
        deliveryButton.setOnAction(e -> {
            courier.deliver(idBox.getValue());
            AlertBox.display("Product: " + idBox.getValue() + " is delivered");
        });

        //log out
        Button checkOutButton = new Button("Log out");
        checkOutButton.setOnAction(e -> {
            window.setScene(scene);
        });

        courierLayout.getChildren().addAll(idBox, deliveryButton, checkOutButton);

        Scene courierScene = new Scene(courierLayout, 760, 475);
        courierLayout.getStylesheets().add("view/style.css");
        window.setScene(courierScene);

    }

}
