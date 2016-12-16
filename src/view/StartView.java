package view;

import controller.*;
import dao.ControllerFactory;
import database.DataBase;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SupportRequest;
import view.layouts.*;

public class StartView extends Application {

    private Stage window;
    private BorderPane layout;
    private TreeView<String> tree;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ControllerFactory controllerFactory = new ControllerFactory();

        ClientController clientController = controllerFactory.getClientController();
        AdminController adminController = controllerFactory.getAdminController();
        SupportController supportController = controllerFactory.getSupportController();
        BuilderController builderController = controllerFactory.getBuilderController();
        CourierController courierController = controllerFactory.getCourierController();

        window = primaryStage;
        window.setTitle("Logistic Company");

        //label
        Label topLabel = new Label("Hi there!");

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
                adminController.checkIn(pass.getText());
                if (adminController.isInSystem()) {
                    AdminLayout.getLayout(window, scene, adminController);
                } else
                    AlertBox.display("Wrong password!");
            }

            if (choiceBox.getValue().equals("Builder")) {
                builderController.checkIn(pass.getText());
                if (builderController.isInSystem()) {
                    BuilderLayout.getLayout(window, scene, builderController);
                } else
                    AlertBox.display("Wrong password!");
            }

            if (choiceBox.getValue().equals("Courier")) {
                courierController.checkIn(pass.getText());
                if (courierController.isInSystem()) {
                    CourierLayout.getLayout(window, scene, courierController);
                } else
                    AlertBox.display("Wrong password!");
            }

            if (choiceBox.getValue().equals("Support")) {
                supportController.checkIn(pass.getText());
                if (supportController.isInSystem()) {
                    SupportLayout.getLayout(window, scene, supportController);
                } else
                    AlertBox.display("Wrong password!");
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

        StackPane topMenu = new StackPane();
        topMenu.getChildren().add(topLabel);
        topMenu.setPadding(new Insets(20, 10, 20, 10));

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
        layout.setTop(topMenu);
        layout.setLeft(leftMenu);
        layout.setBottom(bottomMenu);
        scene = new Scene(layout, 760, 475);
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.show();

    }

    private TreeItem<String> makeBrunch(String name, TreeItem<String> parent) {

        TreeItem<String> item = new TreeItem<>(name);
        item.setExpanded(true);
        parent.getChildren().add(item);

        return item;
    }
}
