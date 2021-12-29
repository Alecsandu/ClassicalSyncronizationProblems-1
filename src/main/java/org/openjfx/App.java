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

public class App extends Application {
    private PhilosophersLogic philosophersLogic;
    private PhilosophersPane philosophersPane;
    private ProducerConsumerPane producerConsumerPane;
    private ProducerConsumerLogic producerConsumerLogic;
    private BorderPane rootNode;

    @Override
    public void start(Stage stage) {
        int numOfPhilosophers = 5;
        int bufferSize = 8;
        int width = 800;
        int height = 600;
        philosophersPane = new PhilosophersPane(numOfPhilosophers, width, height);
        philosophersPane.setBackground(new Background(new BackgroundFill(Color.web("#aabbbf"), CornerRadii.EMPTY, Insets.EMPTY)));
        producerConsumerPane = new ProducerConsumerPane(bufferSize, width, height);
        producerConsumerPane.setBackground(new Background(new BackgroundFill(Color.web("#aaabbb"), CornerRadii.EMPTY, Insets.EMPTY)));

        philosophersLogic = new PhilosophersLogic(numOfPhilosophers, philosophersPane);
        producerConsumerLogic = new ProducerConsumerLogic(bufferSize, producerConsumerPane);

        rootNode = new BorderPane();
        HBox ButtonsBox = new HBox();
        setButtonsForPhilosophersProblem(ButtonsBox);
        rootNode.setTop(ButtonsBox);

        //TO DO: tabs are bugged in order to see the shapes after pressing start
        // we have to go to another tab and then back to the first one
        /*
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Philosphers problem", root);
        Tab tab2 = new Tab("Producers and Consumers"  , new Label("Description"));
        Tab tab3 = new Tab("Readers and Writers" , new Label("Ceva"));

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);

        //VBox vBox = new VBox(tabPane);//change root with vBox in order to see how tabs look
        */

        Scene scene = new Scene(rootNode, width, height);

        setStageOnResizeEventListeners(stage);
        stage.setTitle("CSP");
        stage.setScene(scene);
        stage.show();
    }

    private void setButtonsForPhilosophersProblem(HBox horizontalBox) {
        Button startDeadlockExecButton = new Button("Start deadlock demo");
        startDeadlockExecButton.setOnAction(this::startDeadlockPossibilityButton);

        Button startCorrectExecButton = new Button("Start correctSync demo");
        startCorrectExecButton.setOnAction(this::startCorrectSynchronizationButton);

        Button startProducerConsumerButton = new Button("Start producerConsumer");
        startProducerConsumerButton.setOnAction(this::startProducerConsumer);

        Button stopExecButton = new Button("Stop demo");
        stopExecButton.setOnAction(this::stopButton);

        horizontalBox.getChildren().addAll(startDeadlockExecButton, startCorrectExecButton, startProducerConsumerButton, stopExecButton);
    }

    private void startDeadlockPossibilityButton(ActionEvent actionEvent) {
        rootNode.setCenter(philosophersPane);
        philosophersPane.setIsActive();
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartDeadlockProtocolThreads();
    }

    private void startCorrectSynchronizationButton(ActionEvent actionEvent) {
        rootNode.setCenter(philosophersPane);
        philosophersPane.setIsActive();
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartSynchronizedProtocolThreads();
    }

    private void startProducerConsumer(ActionEvent actionEvent) {
        rootNode.setCenter(producerConsumerPane);
        producerConsumerPane.setIsActive();
        producerConsumerPane.drawInitialFormation();
        producerConsumerLogic.createAndStartConsumerProducer();
    }

    private void stopButton(ActionEvent actionEvent) {
        philosophersPane.setIsNotActive();
        philosophersLogic.checkThreadsStateAndStopThem();

        producerConsumerPane.setIsNotActive();
        producerConsumerLogic.stopConsumerAndProducer();
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