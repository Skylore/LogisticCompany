package view.layouts.client;

import controller.*;
import dao.ControllerFactory;
import database.Converter;
import database.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientView {

    private static BorderPane layout;

    public static void getLayout(Stage window, Scene scene) {

        ControllerFactory controllerFactory = new ControllerFactory();
        ClientController clientController = (ClientController) controllerFactory.getController("ClientController");
        SupportController supportController = (SupportController) controllerFactory.getController("SupportController");

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

        TreeView<String> tree = new TreeView<>(root);
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
        leftMenu.getChildren().addAll(tree);
        leftMenu.setSpacing(10);
        leftMenu.setMaxHeight(300);

        layout = new BorderPane();
        layout.setLeft(leftMenu);
        layout.setBottom(bottomMenu);
        Scene userScene = new Scene(layout, 760, 475);
        userScene.getStylesheets().add("view/style.css");
        window.setScene(userScene);
        window.setOnCloseRequest((e) ->
                new Logger().write(Converter.toJson(controllerFactory.getDataBase())));

    }

    private static TreeItem<String> makeBrunch(String name, TreeItem<String> parent) {

        TreeItem<String> item = new TreeItem<>(name);
        item.setExpanded(true);
        parent.getChildren().add(item);

        return item;
    }
}
