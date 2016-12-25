package view.layouts.client;

import controller.ClientController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import view.layouts.AlertBox;

class WorkRequestLayout {

    private ClientController clientController;

    WorkRequestLayout(ClientController clientController) {
        this.clientController = clientController;
    }

    public GridPane getLayout() {

        GridPane workRequestLayout = new GridPane();
        workRequestLayout.setPadding(new Insets(10, 10, 10, 10));
        workRequestLayout.setVgap(8);
        workRequestLayout.setHgap(10);

        Label nameLabel = new Label("Your name: ");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label salaryLabel = new Label("Desired salary : ");
        GridPane.setConstraints(salaryLabel, 0, 2);
        TextField salaryInput = new TextField();
        GridPane.setConstraints(salaryInput, 1, 2);

        Label goalLabel = new Label("state : ");
        GridPane.setConstraints(goalLabel, 0, 3);
        ChoiceBox<String> stateChoice = new ChoiceBox<>();
        stateChoice.getItems().addAll("admin", "builder", "courier");
        stateChoice.getSelectionModel().selectedItemProperty().
                addListener((v, oldValue, newValue) -> System.out.println(v));
        GridPane.setConstraints(stateChoice, 1, 3);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 4);

        Button button = new Button("Send");
        button.setOnAction(e -> {

            if (!nameInput.getText().equals("") && !stateChoice.getValue().equals("")) {

                clientController.sentWorkRequest(nameInput.getText(), clientController.getInSystem().getEmail(),
                        stateChoice.getValue(), Integer.valueOf(salaryInput.getText()));

                resultLabel.setText("Please wait for admin's answer");
            } else {
                AlertBox.display("Please fill all boxes");
            }
        });
        GridPane.setConstraints(button, 0, 4);

        workRequestLayout.getChildren().addAll(nameLabel, nameInput, salaryLabel, salaryInput,
                goalLabel, stateChoice, button, resultLabel);
        return workRequestLayout;
    }
}
