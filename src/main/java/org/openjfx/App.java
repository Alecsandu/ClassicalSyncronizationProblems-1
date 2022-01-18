package org.openjfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
    private static final Background GENERIC_BACKGROUND = new Background(new BackgroundFill(Color.web("#aaabbb"), CornerRadii.EMPTY, Insets.EMPTY));

    @Override
    public void start(Stage stage) {
        int numOfPhilosophers = 5;
        int bufferSize = 8;
        int numOfReaders = 3;
        int numOfWriters = 3;

        philosophersPane = new PhilosophersPane(numOfPhilosophers, WINDOW_WIDTH, WINDOW_HEIGHT);
        philosophersPane.setBackground(GENERIC_BACKGROUND);
        producerConsumerPane = new ProducerConsumerPane(bufferSize, WINDOW_WIDTH, WINDOW_HEIGHT);
        producerConsumerPane.setBackground(GENERIC_BACKGROUND);
        readersWritersPane = new ReadersWritersPane(numOfReaders, numOfWriters, WINDOW_WIDTH, WINDOW_HEIGHT);
        readersWritersPane.setBackground(GENERIC_BACKGROUND);

        philosophersLogic = new PhilosophersLogic(numOfPhilosophers, philosophersPane);
        producerConsumerLogic = new ProducerConsumerLogic(bufferSize, producerConsumerPane);
        readersWritersLogic = new ReadersWritersLogic(numOfReaders, numOfWriters, readersWritersPane);

        stageRootNode = new BorderPane();
        HBox ButtonsBox = getButtonsForPhilosophersProblem();
        stageRootNode.setTop(ButtonsBox);

        setStageOnResizeEventListeners(stage);
        stage.setTitle("CSP");
        Scene scene = new Scene(stageRootNode, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    private HBox getButtonsForPhilosophersProblem() {
        HBox horizontalBox = new HBox();
        Button startDeadlockExecButton = new Button("Philosophers-DeadLock");
        startDeadlockExecButton.setOnAction(this::startDeadlockPossibilityButton);

        Button startCorrectExecButton = new Button("Philosophers-Sync");
        startCorrectExecButton.setOnAction(this::startCorrectSynchronizationButton);

        Button startProducerConsumerButton = new Button("Producers-Consumers");
        startProducerConsumerButton.setOnAction(this::startProducerConsumer);

        Button startReadersWritersButton = new Button("Readers-Writers");
        startReadersWritersButton.setOnAction(this::startReadersWriters);

        Button stopExecButton = new Button("Finish");
        stopExecButton.setOnAction(this::stopButton);

        horizontalBox.getChildren()
                .addAll(startDeadlockExecButton,
                        startCorrectExecButton,
                        startProducerConsumerButton,
                        startReadersWritersButton,
                        stopExecButton);
        return horizontalBox;
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

    private void setStageOnResizeEventListeners(Stage stage) {
        ChangeListener<Number> stageSizeChangeListener = (observableValue, oldValue, newValue) -> {
            philosophersPane.setActualWidth(stage.getWidth());
            philosophersPane.setActualHeight(stage.getHeight());
            producerConsumerPane.setActualWidth(stage.getWidth());
            producerConsumerPane.setActualHeight(stage.getHeight());
            philosophersPane.recenterPhilosophers();
            philosophersPane.recenterChopsticks();
            producerConsumerPane.recenterSquares();
        };

        stage.widthProperty().addListener(stageSizeChangeListener);
        stage.heightProperty().addListener(stageSizeChangeListener);
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