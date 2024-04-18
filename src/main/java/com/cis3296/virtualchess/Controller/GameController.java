package com.cis3296.virtualchess.Controller;

import com.cis3296.virtualchess.Components.BoardSettings;
import com.cis3296.virtualchess.Systems.Database;
import com.cis3296.virtualchess.Game;
import com.cis3296.virtualchess.Entities.Player;
import com.cis3296.virtualchess.Systems.TurnSystem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class GameController {

    @FXML
    GridPane chessBoard = new GridPane();

    @FXML
    private Text timerTextWhite;
    @FXML
    private Text timerTextBlack;
    @FXML
    private Text currentTurnText;

    private Timeline timeline;

    private TurnSystem turnSystem;
    Game game;

    /**
     * Creates the chess board and adds functionality for the drag handling
     */
    public void initialize(){
        this.game = new Game(chessBoard);
        this.turnSystem = TurnSystem.getInstance();
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> updateTime()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play();
        currentTurnText.setText("Current Turn:\n" + turnSystem.getCurrentPlayer().name);
        turnSystem.setCurrentPlayerText(currentTurnText);

        // Add drag-and-drop event handlers to the chessboard GridPane
        chessBoard.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
            event.consume();
        });

        chessBoard.setOnDragDropped(event -> {
            game.chessBoard.removeShownMoves();
            event.setDropCompleted(true);
            event.consume();
        });



    }

    public void updateTime(){
        int minutesW = turnSystem.whiteTimer.getRemainingTimeMinutes();
        int secondsW = turnSystem.whiteTimer.getRemainingTimeSeconds();

        timerTextWhite.setText(String.format("White Time: %02d:%02d", minutesW, secondsW));

        int minutesB = turnSystem.blackTimer.getRemainingTimeMinutes();
        int secondsB = turnSystem.blackTimer.getRemainingTimeSeconds();

        timerTextBlack.setText(String.format("Black Time: %02d:%02d", minutesB, secondsB));
    }

    public void leaveGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cis3296/virtualchess/mainmenu.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/cis3296/virtualchess/menuStyle.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
        game.endGame();

    }

    public void settings(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cis3296/virtualchess/settingsMenu.fxml"));
            Parent settingsContent = loader.load();

            Stage settingsPopup = new Stage();
            Scene settingsScene = new Scene(settingsContent);
            settingsPopup.setScene(settingsScene);

            settingsPopup.setTitle("Settings");

            settingsPopup.initModality(Modality.APPLICATION_MODAL);


            SettingsMenuController controller = loader.getController();
            controller.backButton.setOnAction(event -> {
                Properties props = new Properties();

                props.setProperty(BoardSettings.CONFIG_ACCESS_STRING, controller.ThemeDropDown.getValue().toString());

                try {
                    File configFile = new File("config.xml");
                    FileOutputStream out = new FileOutputStream(configFile);
                    props.storeToXML(out,"Configuration");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                game.getTheme();
                game.chessBoard.rerenderBoard();

                settingsPopup.close();
            });

            settingsPopup.setOnCloseRequest(event -> {
                Platform.runLater(settingsPopup::close);
            });

            settingsPopup.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

