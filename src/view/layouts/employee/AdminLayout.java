package view.layouts.employee;

import controller.AdminController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminLayout {

    private static TreeView<String> tree;

    public static void getLayout(Stage window, Scene scene, AdminController admin) {

        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10, 10, 10, 10));

        //top menu
        Label topLabel = new Label("Page for administration");
        StackPane topMenu = new StackPane();
        topMenu.getChildren().add(topLabel);
        topMenu.setPadding(new Insets(20, 10, 20, 10));
        mainLayout.setTop(topMenu);

        //left menu
        TreeItem<String> root, adminBranch;

        root = new TreeItem<>();
        root.setExpanded(true);

        adminBranch = makeBrunch("Admin", root);
        makeBrunch("Show work requests", adminBranch);
        makeBrunch("Show products", adminBranch);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().
                addListener((v, oldV, newV) -> {

                    if (newV.getValue().equals("Show work requests"))
                        mainLayout.setCenter(ShowWorkRequestLayout.getLayout(admin)); //layout for requests
                    if (newV.getValue().equals("Show products"))
                        mainLayout.setCenter(ShowProductLayout.getLayout(admin)); //layout for products
                });

        mainLayout.setLeft(tree);

        //bottom menu
        Button checkOutButton = new Button("Log out");
        StackPane bottomMenu = new StackPane();
        bottomMenu.getChildren().addAll(checkOutButton);
        bottomMenu.setAlignment(Pos.BOTTOM_RIGHT);
        checkOutButton.setOnAction(e -> {
            window.setScene(scene);
        });
        mainLayout.setBottom(bottomMenu);
        Scene adminScene = new Scene(mainLayout, 760, 475);
        adminScene.getStylesheets().add("view/style.css");
        window.setScene(adminScene);

    }

    private static TreeItem<String> makeBrunch(String name, TreeItem<String> parent) {

        TreeItem<String> item = new TreeItem<>(name);
        item.setExpanded(true);
        parent.getChildren().add(item);

        return item;
    }
}
