package view.layouts;

import controller.BuilderController;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BuilderLayout {

    public static void getLayout(Stage window, Scene scene, BuilderController builder) {

        GridPane builderLayout = new GridPane();
        builderLayout.setPadding(new Insets(10, 10, 10, 10));
        builderLayout.setVgap(8);
        builderLayout.setHgap(10);

        Label countryLabel = new Label("Country: ");
        GridPane.setConstraints(countryLabel, 0, 0);
        TextField countryInput = new TextField();
        GridPane.setConstraints(countryInput, 1, 0);

        Label cityLabel = new Label("City : ");
        GridPane.setConstraints(cityLabel, 0, 1);
        TextField cityInput = new TextField();
        GridPane.setConstraints(cityInput, 1, 1);

        Label streetLabel = new Label("Street : ");
        GridPane.setConstraints(streetLabel, 0, 2);
        TextField streetInput = new TextField();
        GridPane.setConstraints(streetInput, 1, 2);

        Label numLabel = new Label("House number: ");
        GridPane.setConstraints(numLabel, 0, 3);
        TextField numInput = new TextField();
        GridPane.setConstraints(numInput, 1, 3);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 4);
        Button buildButton = new Button("Build");
        buildButton.setOnAction(e -> {
                try {
                    Location location = new GoogleMapsAPIImpl().findLocation(countryInput.getText(),
                            cityInput.getText(), streetInput.getText(), numInput.getText());
                    builder.build(location);
                    resultLabel.setText("you have built department by address + \n" + location.getFormattedAddress());
                } catch (IndexOutOfBoundsException ex) {
                    AlertBox.display("This address does not exist");
                }
        });
        GridPane.setConstraints(buildButton, 0, 4);

        Button checkOutButton = new Button("Log out");
        checkOutButton.setOnAction(e -> {
            builder.checkOut();
            window.setScene(scene);
        });
        GridPane.setConstraints(checkOutButton, 0, 5);

        builderLayout.getChildren().addAll(countryLabel, countryInput, cityLabel, cityInput,
                streetLabel, streetInput, numLabel, numInput, resultLabel, buildButton, checkOutButton);

        Scene builderScene = new Scene(builderLayout, 500, 500);
        window.setScene(builderScene);

    }

}
