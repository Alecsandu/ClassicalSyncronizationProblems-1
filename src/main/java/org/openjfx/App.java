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
import org.openjfx.producersandconsumersproblem.ProducerConsumerLogic;
import org.openjfx.producersandconsumersproblem.ProducerConsumerPane;
import org.openjfx.readersandwritersproblem.ReadersWritersLogic;
import org.openjfx.readersandwritersproblem.ReadersWritersPane;

public class App extends Application {
    private PhilosophersLogic philosophersLogic;
    private PhilosophersPane philosophersPane;
    private ProducerConsumerPane producerConsumerPane;
    private ProducerConsumerLogic producerConsumerLogic;
    private ReadersWritersPane readersWritersPane;
    private ReadersWritersLogic readersWritersLogic;

    private BorderPane stageRootNode;

    private static Boolean buttonIsActive = false;

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final Background GENERIC_BACKGROUND = new Background(new BackgroundFill(Color.web("#93a397"), CornerRadii.EMPTY, Insets.EMPTY));
    private static final String BUTTON_STATIC_STYLE = "-fx-background-radius: 0;" +
                    "-fx-background-color: rgb(55, 55, 56);" +
                    "-fx-text-fill: rgb(220, 220, 220);";
    private static final String BUTTON_HOVER_STYLE = "-fx-background-radius: 0;" +
            "-fx-background-color: rgb(85, 85, 85);" +
            "-fx-text-fill: rgb(220, 220, 220);";
    private static final String BUTTON_CLICKED_STYLE = "-fx-background-radius: 0;" +
            "-fx-background-color: rgb(45, 45, 45);" +
            "-fx-text-fill: rgb(220, 220, 220);";

    @Override
    public void start(Stage stage) {
        int numOfPhilosophers = 5;
        int bufferSize = 7;
        int numOfReaders = 3;
        int numOfWriters = 3;

        philosophersPane = new PhilosophersPane(numOfPhilosophers, WINDOW_WIDTH, WINDOW_HEIGHT);
        philosophersPane.setBackground(GENERIC_BACKGROUND);
        philosophersLogic = new PhilosophersLogic(numOfPhilosophers, philosophersPane);

        producerConsumerPane = new ProducerConsumerPane(bufferSize, WINDOW_WIDTH, WINDOW_HEIGHT);
        producerConsumerPane.setBackground(GENERIC_BACKGROUND);
        producerConsumerLogic = new ProducerConsumerLogic(bufferSize, producerConsumerPane);

        readersWritersPane = new ReadersWritersPane(numOfReaders, numOfWriters, WINDOW_WIDTH, WINDOW_HEIGHT);
        readersWritersPane.setBackground(GENERIC_BACKGROUND);
        readersWritersLogic = new ReadersWritersLogic(numOfReaders, numOfWriters, readersWritersPane);

        HBox buttonsBox = getButtonsForPhilosophersProblem();
        Pane emptyPane = new Pane();
        emptyPane.setBackground(GENERIC_BACKGROUND);
        stageRootNode = new BorderPane();
        stageRootNode.setTop(buttonsBox);
        stageRootNode.setCenter(emptyPane);

        Scene scene = new Scene(stageRootNode, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("CSP");
        stage.setResizable(false);
        stage.show();
    }

    private HBox getButtonsForPhilosophersProblem() {
        Button startDeadlockExecButton = withGivenLabelCreateButtonAfterSetStyleAndAction("Philosophers-DeadLock");

        Button startCorrectExecutionButton = withGivenLabelCreateButtonAfterSetStyleAndAction("Philosophers-Sync");

        Button startProducerConsumerButton = withGivenLabelCreateButtonAfterSetStyleAndAction("Producers-Consumers");

        Button startReadersWritersButton = withGivenLabelCreateButtonAfterSetStyleAndAction("Readers-Writers");

        Button stopExecButton = withGivenLabelCreateButtonAfterSetStyleAndAction("Finish");

        HBox horizontalBox = new HBox();
        horizontalBox.setStyle("-fx-background-color: rgb(55, 55, 55);");
        horizontalBox.setFillHeight(true);
        horizontalBox.getChildren()
                .addAll(startDeadlockExecButton,
                        startCorrectExecutionButton,
                        startProducerConsumerButton,
                        startReadersWritersButton,
                        stopExecButton);
        return horizontalBox;
    }

    private Button withGivenLabelCreateButtonAfterSetStyleAndAction(String label) {
        Button newButton = new Button(label);

        newButton.setPrefHeight(38);

        newButton.setStyle(BUTTON_STATIC_STYLE);
        newButton.setOnMouseEntered(e -> newButton.setStyle(BUTTON_HOVER_STYLE));
        newButton.setOnMousePressed(e -> newButton.setStyle(BUTTON_CLICKED_STYLE));
        newButton.setOnMouseReleased(e -> newButton.setStyle(BUTTON_HOVER_STYLE));
        newButton.setOnMouseMoved(e -> newButton.setStyle(BUTTON_HOVER_STYLE));
        newButton.setOnMouseExited(e -> newButton.setStyle(BUTTON_STATIC_STYLE));

        switch (label){
            case "Philosophers-DeadLock":
                newButton.setOnAction(this::startDeadlockPossibilityButton);
                break;
            case "Philosophers-Sync":
                newButton.setOnAction(this::startCorrectSynchronizationButton);
                break;
            case "Producers-Consumers":
                newButton.setOnAction(this::startProducerConsumer);
                break;
            case "Readers-Writers":
                newButton.setOnAction(this::startReadersWriters);
                break;
            case "Finish":
                newButton.setOnAction(this::stopButton);
                break;
        }

        return newButton;
    }

    private void startDeadlockPossibilityButton(ActionEvent actionEvent) {
        if (!buttonIsActive) {
            buttonIsActive = true;
            stageRootNode.setCenter(philosophersPane);
            philosophersPane.drawInitialFormation();
            philosophersLogic.createAndStartDeadlockProtocolThreads();
        }
    }

    private void startCorrectSynchronizationButton(ActionEvent actionEvent) {
        if (!buttonIsActive) {
            buttonIsActive = true;
            stageRootNode.setCenter(philosophersPane);
            philosophersPane.drawInitialFormation();
            philosophersLogic.createAndStartSynchronizedProtocolThreads();
        }
    }

    private void startProducerConsumer(ActionEvent actionEvent) {
        if (!buttonIsActive) {
            buttonIsActive = true;
            stageRootNode.setCenter(producerConsumerPane);
            producerConsumerPane.drawInitialFormation();
            producerConsumerLogic.createAndStartConsumerProducer();
        }
    }

    private void startReadersWriters(ActionEvent actionEvent){
        if (!buttonIsActive) {
            buttonIsActive = true;
            stageRootNode.setCenter(readersWritersPane);
            readersWritersPane.drawInitialFormation();
            readersWritersLogic.createAndStartReadersWriters();
        }
    }

    private void stopButton(ActionEvent actionEvent) {
        if (buttonIsActive) {
            buttonIsActive = false;
            philosophersLogic.checkThreadsStateAndStopThem();
            producerConsumerLogic.stopConsumerAndProducer();
            readersWritersLogic.stopReadersWriters();
        }
    }

    @Override
    public void stop() throws Exception {
        philosophersLogic.checkThreadsStateAndStopThem();
        producerConsumerLogic.stopConsumerAndProducer();
        readersWritersLogic.stopReadersWriters();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}