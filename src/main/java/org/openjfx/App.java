package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.openjfx.philosophersproblem.PhilosophersLogic;
import org.openjfx.philosophersproblem.PhilosophersPane;

public class App extends Application {
    private PhilosophersLogic philosophersLogic;
    private PhilosophersPane philosophersPane;

    @Override
    public void start(Stage stage){
        stage.setTitle("CSP");
        // TO DO: vezi mai jos unde e declarata metoda
        //setStageEventListeners(stage);

        int numOfPhilosophers = 5;
        philosophersPane = new PhilosophersPane(numOfPhilosophers, 800, 600);
        philosophersPane.setBackground(new Background(new BackgroundFill(Color.web("#aabbbf"), CornerRadii.EMPTY, Insets.EMPTY)));

        philosophersLogic = new PhilosophersLogic(numOfPhilosophers, philosophersPane);

        BorderPane root = new BorderPane();
        HBox leftZone = setButtonsForLeftZone();

        root.setTop(leftZone);
        root.setCenter(philosophersPane);

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.show();
    }

    private HBox setButtonsForLeftZone() {
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
        philosophersLogic.createAndStartThreads();
    }

    private void startCorrectSynchronizationButton(ActionEvent actionEvent) {
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartFixedThreads();
    }

    private void stopButton(ActionEvent actionEvent) {
        philosophersLogic.checkThreadsStateAndStopThem();
    }

    //TO DO: sa facem ca dupa ce apeleaza drawInitialFormation din nou sa dispara ce era deja desenat
    /*private void setStageEventListeners(Stage stage) {
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            philosophersPane.setActualWidth(newVal.doubleValue());
            philosophersPane.setDrawn(false);
            philosophersPane.drawInitialFormation();
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            philosophersPane.setActualHeight(newVal.doubleValue());
        });
    }*/

    @Override
    public void stop() throws Exception {
        philosophersLogic.checkThreadsStateAndStopThem();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}