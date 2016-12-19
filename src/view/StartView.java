package view;

import controller.*;
import dao.ControllerFactory;
import database.Converter;
import database.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.layouts.*;

public class StartView extends Application {

    private Stage window;
    private BorderPane layout;
    private TreeView<String> tree;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ControllerFactory controllerFactory = new ControllerFactory();

        ClientController clientController = (ClientController) controllerFactory.getController("ClientController");
        AdminController adminController = (AdminController) controllerFactory.getController("AdminController");
        SupportController supportController = (SupportController) controllerFactory.getController("SupportController");
        BuilderController builderController = (BuilderController) controllerFactory.getController("BuilderController");
        CourierController courierController = (CourierController) controllerFactory.getController("CourierController");

        window = primaryStage;
        window.setTitle("Logistic Company");

        // log in for employee
        Label labelLogIn = new Label("Log in as an employee");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Admin", "Builder", "Courier", "Support");
        choiceBox.setValue("Admin");

        TextField pass = new TextField();
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

        TreeItem<String> root, client;

        //root
        root = new TreeItem<>();
        root.setExpanded(true);

        //client
        client = makeBrunch("Client", root);
        makeBrunch("Send product", client);
        makeBrunch("Get product", client);
        makeBrunch("Send request for work", client);
        makeBrunch("Find out where your product", client);

        SendProductLayout sendProductLayout = new SendProductLayout(clientController);
        GetProductLayout getProductLayout = new GetProductLayout(clientController);
        WorkRequestLayout workRequestLayout = new WorkRequestLayout(clientController);
        FindProductLayout findProductLayout = new FindProductLayout(clientController);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().
                addListener((v, oldV, newV) -> {

                    if (newV.getValue().equals("Send product"))
                        layout.setCenter(sendProductLayout.getLayout());
                    if (newV.getValue().equals("Get product"))
                        layout.setCenter(getProductLayout.getLayout());
                    if (newV.getValue().equals("Send request for work"))
                        layout.setCenter(workRequestLayout.getLayout());
                    if (newV.getValue().equals("Find out where your product"))
                        layout.setCenter(findProductLayout.getLayout());
                });

        //bottom menu
        Label supportLabel = new Label("Here you can ask any question");
        TextField emailInput = new TextField();
        emailInput.setPromptText("Email");
        emailInput.setMaxWidth(200);
        TextField textInput = new TextField();
        textInput.setPromptText("Your question");
        textInput.setMinHeight(30);
        textInput.setMaxWidth(200);
        Button supportButton = new Button("Ask a question");
        supportButton.setOnAction((e) -> {
            if (!emailInput.getText().equals("") && !textInput.getText().equals("")) {
                supportController.ask(emailInput.getText(), textInput.getText());
                textInput.setText("");
                emailInput.setText("");
            }
        });

        VBox bottomMenu = new VBox();
        bottomMenu.getChildren().addAll(supportLabel, emailInput, textInput, supportButton);
        bottomMenu.setPadding(new Insets(10, 10, 10, 10));
        bottomMenu.setSpacing(10);
        bottomMenu.setAlignment(Pos.BASELINE_LEFT);

        VBox leftMenu = new VBox();
        leftMenu.getChildren().addAll(tree, labelLogIn, choiceBox, pass, buttonLogIn);
        leftMenu.setSpacing(10);
        leftMenu.setMaxHeight(300);

        layout = new BorderPane();
        layout.setLeft(leftMenu);
        layout.setBottom(bottomMenu);
        scene = new Scene(layout, 760, 475);
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest((e) ->
                new Logger().write(Converter.toJson(controllerFactory.getDataBase())));

    }

    private TreeItem<String> makeBrunch(String name, TreeItem<String> parent) {

        TreeItem<String> item = new TreeItem<>(name);
        item.setExpanded(true);
        parent.getChildren().add(item);

        return item;
    }
}
