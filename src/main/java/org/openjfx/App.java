package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.openjfx.philosophersproblem.PhilosophersLogic;
import org.openjfx.philosophersproblem.PhilosophersPane;

import java.util.List;

public class App extends Application {
    private PhilosophersLogic philosophersLogic;
    private PhilosophersPane philosophersPane;

    @Override
    public void start(Stage stage){
        stage.setTitle("CSP");
        // TO DO: vezi mai jos unde e declarata metoda
        setStageEventListeners(stage);

        int numOfPhilosophers = 5;
        philosophersPane = new PhilosophersPane(numOfPhilosophers, 800, 600);
        philosophersPane.setBackground(new Background(new BackgroundFill(Color.web("#aabbbf"), CornerRadii.EMPTY, Insets.EMPTY)));

        philosophersLogic = new PhilosophersLogic(numOfPhilosophers, philosophersPane);

        BorderPane root = new BorderPane();
        HBox topZone = setButtonsForTopZone();

        root.setTop(topZone);
        root.setCenter(philosophersPane);

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.show();
    }

    private HBox setButtonsForTopZone() {
        Button startDeadlockExecButton = new Button("Start deadlock demo");
        startDeadlockExecButton.setOnAction(this::startDeadlockPossibilityButton);

        Button startCorrectExecButton = new Button("Start correctSync demo");
        startCorrectExecButton.setOnAction(this::startCorrectSynchronizationButton);

        Button stopExecButton = new Button("Stop demo");
        stopExecButton.setOnAction(this::stopButton);

        HBox horizontalBox = new HBox();

        horizontalBox.getChildren().addAll(startDeadlockExecButton, startCorrectExecButton, stopExecButton);
        return horizontalBox;
    }

    private void startDeadlockPossibilityButton(ActionEvent actionEvent) {
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartDeadlockProtocolThreads();
    }

    private void startCorrectSynchronizationButton(ActionEvent actionEvent) {
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartSynchronizedProtocolThreads();
    }

    private void stopButton(ActionEvent actionEvent) {
        philosophersLogic.checkThreadsStateAndStopThem();
    }

    //TO DO: sa facem ca dupa ce apeleaza drawInitialFormation din nou sa dispara ce era deja desenat
    private void setStageEventListeners(Stage stage) {
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            philosophersPane.setActualWidth(newVal.doubleValue());
            philosophersPane.setDrawn(false);
            philosophersPane.eraseShapes();
            philosophersPane.drawInitialFormation();
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            philosophersPane.setActualHeight(newVal.doubleValue());
            philosophersPane.setDrawn(false);
            philosophersPane.eraseShapes();
            philosophersPane.drawInitialFormation();
        });
    }

    @Override
    public void stop() throws Exception {
        philosophersLogic.checkThreadsStateAndStopThem();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}