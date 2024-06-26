package com.cis3296.virtualchess.Controller;

import com.cis3296.virtualchess.Entities.LeaderBoardEntry;
import com.cis3296.virtualchess.Systems.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * UI Controller for the leaderboard screen
 */
public class LeaderBoardController {

    @FXML
    public Button backButton;
    @FXML
    public TableView table;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn Player1Column;
    @FXML
    public TableColumn Player1ResultColumn;
    @FXML
    public TableColumn Player2Column;
    @FXML
    public TableColumn Player2ResultColumn;
    @FXML
    public AnchorPane menu;

    /**
     * Called when the screen is loaded.
     * Sets up factories for each of the fields so that when an arraylist is given to it,
     * it can autoload the values into the right columns
     */
    public void initialize(){
        idColumn.setCellValueFactory(
                new PropertyValueFactory<LeaderBoardEntry, String>("id")
        );
        Player1Column.setCellValueFactory(
                new PropertyValueFactory<LeaderBoardEntry, String>("player1Name")
        );
        Player1ResultColumn.setCellValueFactory(
                new PropertyValueFactory<LeaderBoardEntry, String>("player1Result")
        );
        Player2Column.setCellValueFactory(
                new PropertyValueFactory<LeaderBoardEntry, String>("player2Name")
        );
        Player2ResultColumn.setCellValueFactory(
                new PropertyValueFactory<LeaderBoardEntry, String>("player2Result")
        );
        table.setItems(Database.getAllEntries());
    }

    /**
     * Button action for switching to the main screen
     * @param event {@link ActionEvent} for the click
     * @throws IOException Can throw if screen cant be loaded
     */
    public void backToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cis3296/virtualchess/mainmenu.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/cis3296/virtualchess/menuStyle.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
