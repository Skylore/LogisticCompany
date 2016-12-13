package view;

import controller.AdminController;
import controller.BuilderController;
import controller.ClientController;
import controller.CourierController;
import database.DataBase;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Влад on 13.12.2016.
 */
public class StartView extends Application {

    Stage window;
    BorderPane layout;
    TreeView<String> tree;
    Label topLabel;


    public static void main(String[] args) {

        DataBase dataBase = new DataBase();
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
        topLabel = new Label("Hi there!");

        TreeItem<String> root, client, admin;

        //root
        root = new TreeItem<>();
        root.setExpanded(true);

        //client
        client = makeBrunch("Client", root);
        makeBrunch("Send product", client);
        makeBrunch("Get product", client);
        makeBrunch("Send request for work", client);
        makeBrunch("Find out where your product", client);

        // admin
        admin = makeBrunch("Admin", root);
        makeBrunch("Show work requests", admin);
        makeBrunch("Show product in department", admin);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().
                addListener((v, oldV, newV) -> {

                    //logic for choice

                    System.out.println(newV.getValue());

                });

        StackPane topMenu = new StackPane();
        topMenu.getChildren().add(topLabel);
        topMenu.setPadding(new Insets(20, 10, 20, 10));


        StackPane leftMenu = new StackPane();
        leftMenu.getChildren().add(tree);
        leftMenu.setMinWidth(50);


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
