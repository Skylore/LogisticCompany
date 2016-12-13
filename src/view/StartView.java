package view;

import controller.AdminController;
import controller.BuilderController;
import controller.ClientController;
import controller.CourierController;
import database.DataBase;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.layouts.SendProductLayout;

/**
 * Created by Влад on 13.12.2016.
 */
public class StartView extends Application {

    Stage window;
    BorderPane layout;
    TreeView<String> tree;
    private static DataBase dataBase;


    public static void main(String[] args) {

        dataBase = new DataBase();
        AdminController admin = new AdminController(dataBase);
        ClientController client = new ClientController(dataBase);
        CourierController courier = new CourierController(dataBase);
        BuilderController builder = new BuilderController(dataBase);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Logistic Company");

        //label
        Label topLabel = new Label("Hi there!");


        // log in for employee
        Label labelLogIn = new Label("Log in as an employee");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Admin", "Builder", "Courier");
        choiceBox.setValue("Admin");
        choiceBox.getSelectionModel().selectedItemProperty().
                addListener( (v, oldValue, newValue) -> System.out.println(v));

        TextField pass = new TextField();
        pass.setPromptText("password");

        Button buttonLogIn = new Button("Log in");
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

        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().
                addListener((v, oldV, newV) -> {

                    if(newV.getValue().equals("Send product"))
                        layout.setCenter(SendProductLayout.getLayout());

                });

        StackPane topMenu = new StackPane();
        topMenu.getChildren().add(topLabel);
        topMenu.setPadding(new Insets(20, 10, 20, 10));

        VBox leftMenu = new VBox();
        leftMenu.getChildren().addAll(tree, labelLogIn, choiceBox, pass, buttonLogIn);
        leftMenu.setSpacing(10);
        leftMenu.setMaxHeight(300);

        layout = new BorderPane();
        layout.setTop(topMenu);
        layout.setLeft(leftMenu);
        Scene scene = new Scene(layout, 800, 500);
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
