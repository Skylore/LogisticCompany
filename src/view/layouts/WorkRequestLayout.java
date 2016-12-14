package view.layouts;

import database.DataBase;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Влад on 14.12.2016.
 */
public class WorkRequestLayout {
    public static GridPane getLayout() {
        GridPane workRequestLayout = new GridPane();
        workRequestLayout.setPadding(new Insets(10, 10, 10, 10));
        workRequestLayout.setVgap(8);
        workRequestLayout.setHgap(10);

        Label nameLabel = new Label("Your name: ");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label emailLabel = new Label("Email: ");
        GridPane.setConstraints(emailLabel, 0, 1);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 1);

        Label salaryLabel = new Label("Desired salary : ");
        GridPane.setConstraints(salaryLabel, 0, 2);
        TextField salaryInput = new TextField();
        GridPane.setConstraints(salaryInput, 1, 2);

        Label goalLabel = new Label("state : ");
        GridPane.setConstraints(goalLabel, 0, 3);
        TextField goalInput = new TextField();
        GridPane.setConstraints(goalInput, 1, 3);


        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 4);

        Button button = new Button("Send");
        button.setOnAction(e -> resultLabel.setText("Your request sent"));
        GridPane.setConstraints(button, 0, 4);

        workRequestLayout.getChildren().addAll(nameLabel, nameInput, salaryLabel, salaryInput,
                goalLabel, goalInput, emailLabel, emailInput, button, resultLabel);
        return workRequestLayout;
    }
}
