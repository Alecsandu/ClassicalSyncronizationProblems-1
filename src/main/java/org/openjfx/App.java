package org.openjfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.openjfx.philosophersproblem.PhilosophersLogic;
import org.openjfx.philosophersproblem.PhilosophersPane;

public class App extends Application {
    private PhilosophersLogic philosophersLogic;
    private PhilosophersPane philosophersPane;

    @Override
    public void start(Stage stage) {
        int numOfPhilosophers = 5;
        philosophersPane = new PhilosophersPane(numOfPhilosophers, 800, 600);
        philosophersPane.setBackground(new Background(new BackgroundFill(Color.web("#aabbbf"), CornerRadii.EMPTY, Insets.EMPTY)));

        philosophersLogic = new PhilosophersLogic(numOfPhilosophers, philosophersPane);

        BorderPane philosophersRootNode = new BorderPane();
        HBox philosophersButtonsBox = new HBox();
        setButtonsForPhilosophersProblem(philosophersButtonsBox);
        philosophersRootNode.setTop(philosophersButtonsBox);
        philosophersRootNode.setCenter(philosophersPane);

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

        Scene scene = new Scene(philosophersRootNode, 800, 600);

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

        Button stopExecButton = new Button("Stop demo");
        stopExecButton.setOnAction(this::stopButton);

        horizontalBox.getChildren().addAll(startDeadlockExecButton, startCorrectExecButton, stopExecButton);
    }

    private void startDeadlockPossibilityButton(ActionEvent actionEvent) {
        philosophersPane.setActive();
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartDeadlockProtocolThreads();
    }

    private void startCorrectSynchronizationButton(ActionEvent actionEvent) {
        philosophersPane.setActive();
        philosophersPane.drawInitialFormation();
        philosophersLogic.createAndStartSynchronizedProtocolThreads();
    }

    private void stopButton(ActionEvent actionEvent) {
        philosophersPane.setDeactive();
        philosophersLogic.checkThreadsStateAndStopThem();
    }

    private void setStageOnResizeEventListeners(Stage stage) {
        ChangeListener<Number> stageSizeChangeListener = (observableValue, oldValue, newValue) -> {
            philosophersPane.setActualWidth(stage.getWidth());
            philosophersPane.setActualHeight(stage.getHeight());
            philosophersPane.setDrawn(false);
            philosophersPane.getCircles().forEach(circle -> philosophersPane.getChildren().remove(circle));
            philosophersPane.getLines().forEach(circle -> philosophersPane.getChildren().remove(circle));
            philosophersPane.getChildren().remove(philosophersPane.getCentralCircle());
            philosophersPane.eraseShapes();
            philosophersPane.drawInitialFormation();
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