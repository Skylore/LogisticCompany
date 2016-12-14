package view.layouts;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Влад on 13.12.2016.
 */
public class GetProductLayout {
    
    public static GridPane getLayout(){
        GridPane getProductLayout = new GridPane();
        getProductLayout.setPadding(new Insets(10, 10, 10, 10));
        getProductLayout.setVgap(8);
        getProductLayout.setHgap(10);

        Label idLabel = new Label("Product id: ");
        GridPane.setConstraints(idLabel, 0, 0);

        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 1);

        Button button = new Button("Get product");
        button.setOnAction(e -> resultLabel.setText("You got your product"));
        GridPane.setConstraints(button, 0, 1);

        getProductLayout.getChildren().addAll(idInput, idLabel, button, resultLabel);
        return getProductLayout;
    }
    
}
