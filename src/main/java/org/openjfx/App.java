package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private PhilosophersLogic philosophersLogic;
    private PhilosophersPane philosophersPane;

    @Override
    public void start(Stage stage) throws Exception{
        int numberOfPhilosophers = 5;

        stage.setTitle("CSP");
        BorderPane root = new BorderPane();
        BorderPane navRoot = new BorderPane();  // left zone

        philosophersPane = new PhilosophersPane(numberOfPhilosophers);  // right zone
        philosophersLogic = new PhilosophersLogic(numberOfPhilosophers, philosophersPane);

        /* Start and stop buttons for the philosohpser demo */
        Button startButton = new Button("Start deadlock");
        startButton.setOnAction(this::startButton);

        Button startButtonFixed = new Button("Start");
        startButtonFixed.setOnAction(this::startButtonFixed);

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(this::stopButton);

        /* Add the buttons on the left zone of the scene */
        VBox leftZone = new VBox();
        leftZone.getChildren().addAll(startButton, startButtonFixed, stopButton);
        navRoot.setCenter(leftZone);

        SplitPane split = new SplitPane();
        split.setOrientation(Orientation.HORIZONTAL);
        split.getItems().addAll(navRoot, philosophersPane);
        split.setDividerPositions(0.18);
        SplitPane.setResizableWithParent(navRoot, Boolean.FALSE);
        navRoot.minWidthProperty().bind(split.widthProperty().multiply(0.18));  // used in order to block
        navRoot.maxWidthProperty().bind(split.widthProperty().multiply(0.25));  // the line from moving too much

        root.setCenter(split);

        Scene scene = new Scene(root, 800, 600);
        philosophersPane.setStyle("-fx-background-color: #344464");
        navRoot.setStyle("-fx-background-color: #253046");

        stage.setScene(scene);
        stage.show();
    }

    private void startButton(ActionEvent actionEvent) {
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartThreads();
    }

    private void startButtonFixed(ActionEvent actionEvent) {
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartFixedThreads();
    }


    private void stopButton(ActionEvent actionEvent) {
        philosophersLogic.checkThreadsStateAndStopThem();
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