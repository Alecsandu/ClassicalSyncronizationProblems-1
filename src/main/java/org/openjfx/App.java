package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.openjfx.appinterface.ActionButton;
import org.openjfx.geometry.PhilosophersLogic;

public class App extends Application {
    private PhilosophersLogic philosophersLogic;

    @Override
    public void start(Stage stage) throws Exception{
        int numberOfPhilosophers = 5;

        stage.setTitle("CSP");
        BorderPane root = new BorderPane();
        BorderPane navRoot = new BorderPane();  // left zone

        PhilosophersPane pane = new PhilosophersPane(numberOfPhilosophers);  // right zone
        philosophersLogic = new PhilosophersLogic(numberOfPhilosophers, pane);

        /* Start and stop buttons for the philosohpser demo */
        Button startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                philosophersLogic.createAndStartThreads();
            }
        });

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                philosophersLogic.checkThreadsState();
            }
        });

        /* Add the buttons on the left zone of the scene */
        VBox leftZone = new VBox();
        leftZone.getChildren().addAll(startButton, stopButton);
        navRoot.setCenter(leftZone);

        SplitPane split = new SplitPane();
        split.setOrientation(Orientation.HORIZONTAL);
        split.getItems().addAll(navRoot, pane);
        split.setDividerPositions(0.18);
        SplitPane.setResizableWithParent(navRoot, Boolean.FALSE);
        navRoot.minWidthProperty().bind(split.widthProperty().multiply(0.18));  // used in order to block
        navRoot.maxWidthProperty().bind(split.widthProperty().multiply(0.25));  // the line from moving too much

        root.setCenter(split);

        pane.drawInitialFormation();

        Scene scene = new Scene(root, 800, 600);
        pane.setStyle("-fx-background-color: #344464");
        navRoot.setStyle("-fx-background-color: #253046");

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        philosophersLogic.checkThreadsState();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}