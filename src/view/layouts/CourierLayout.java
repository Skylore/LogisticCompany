package view.layouts;


import controller.BuilderController;
import controller.CourierController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CourierLayout {
    public static void getLayout(Stage window, Scene scene, CourierController courier) {

        GridPane courierLayout = new GridPane();
        courierLayout.setPadding(new Insets(10, 10, 10, 10));
        courierLayout.setVgap(8);
        courierLayout.setHgap(10);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 0);
        Button deliveryButton = new Button("Deliver Product");
        deliveryButton.setOnAction(e -> resultLabel.setText("Done"));
        GridPane.setConstraints(deliveryButton, 0, 0);

        Button checkOutButton = new Button("Log out");
        checkOutButton.setOnAction(e -> {
            courier.checkOut();
            window.setScene(scene);
        });
        GridPane.setConstraints(checkOutButton, 0, 1);

        courierLayout.getChildren().addAll(resultLabel, deliveryButton, checkOutButton);

        Scene builderScene = new Scene(courierLayout, 500, 500);
        window.setScene(builderScene);

    }
}
