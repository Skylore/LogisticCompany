package view.layouts;

import controller.SupportController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SupportRequest;

/**
 * Created by Влад on 15.12.2016.
 */
public class SupportLayout {
    private static TableView<SupportRequest> table;

    public static void getLayout(Stage window, Scene scene, SupportController support){

        //column
        TableColumn<SupportRequest, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<SupportRequest, String> questionColumn = new TableColumn<>("Question");
        questionColumn.setMinWidth(500);
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));

        table = new TableView<>();
        table.setItems(support.showRequests());
        table.getColumns().addAll(emailColumn, questionColumn);
        table.setMaxHeight(300);

        //text field
        TextField answer = new TextField();
        answer.setMinHeight(70);
        answer.setPromptText("Answer for request");

        //button
        Button replyButton = new Button("Reply");
        replyButton.setOnAction(e -> {
            support.reply(table.getSelectionModel().getSelectedItem().getId(), answer.getText());
            answer.setText("");
            table.setItems(support.showRequests());
        });

        //check out
        Button checkOutButton = new Button("Log out");
        checkOutButton.setOnAction(e -> {
            support.checkOut();
            window.setScene(scene);
        });


        VBox layout = new VBox();
        layout.getChildren().addAll(table, answer, replyButton, checkOutButton);
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Scene supportScene = new Scene(layout, 760, 475);
        supportScene.getStylesheets().add("view/style.css");
        window.setScene(supportScene);
    }
}
