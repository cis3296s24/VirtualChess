package com.cis3296.virtualchess;

import com.cis3296.virtualchess.Systems.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChessApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Database.getInstance();
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainmenu.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("menuStyle.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        Database.closeDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
