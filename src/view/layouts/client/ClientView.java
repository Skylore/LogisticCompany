package view.layouts.client;

import controller.ClientController;
import controller.SupportController;
import dao.ControllerFactory;
import database.Converter;
import database.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientView {

    private static BorderPane layout;
    private ControllerFactory controllerFactory;

    public ClientView(ControllerFactory controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    public void getLayout(Stage window, Scene scene, ClientController clientController) {

        SupportController supportController = (SupportController) controllerFactory.getController("SupportController");
        PersonalCabinetLayout personalCabinetLayout = new PersonalCabinetLayout();

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

        SendProductLayout sendProductLayout = new SendProductLayout(clientController, controllerFactory.getDataBase());
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
        TextField textInput = new TextField();
        textInput.setPromptText("Your question");
        textInput.setMinHeight(30);
        textInput.setMaxWidth(200);
        Button supportButton = new Button("Ask a question");
        supportButton.setOnAction((e) -> {
            if (!textInput.getText().equals("")) {
                supportController.ask(clientController.getInSystem().getEmail(), textInput.getText());
                textInput.setText("");
            }
        });

        VBox bottomMenu = new VBox();
        bottomMenu.getChildren().addAll(supportLabel, textInput, supportButton);
        bottomMenu.setPadding(new Insets(10, 10, 10, 10));
        bottomMenu.setSpacing(10);
        bottomMenu.setAlignment(Pos.BASELINE_LEFT);

        VBox leftMenu = new VBox();
        leftMenu.getChildren().addAll(tree);
        leftMenu.setSpacing(10);
        leftMenu.setMaxHeight(300);

        //top label
        Label clientInfo = new Label(clientController.getInSystem().getLogin());
        clientInfo.getStyleClass().add("label-info");
        StackPane topLayout = new StackPane(clientInfo);
        topLayout.setPadding(new Insets(10, 10, 10, 10));
        topLayout.setAlignment(Pos.TOP_RIGHT);

        clientInfo.setOnMouseClicked((e) ->
                layout.setCenter(personalCabinetLayout.getLayout(clientController, scene, window)));

        layout = new BorderPane();
        layout.setTop(topLayout);
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
