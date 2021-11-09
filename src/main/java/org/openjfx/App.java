package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.geometry.PhilosophersLogic;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        double width = 640;
        double height = 480;
        int numberOfPhilosophers = 5;

        PhilosophersPane pane = new PhilosophersPane(numberOfPhilosophers);
        PhilosophersLogic philosophersLogic = new PhilosophersLogic(numberOfPhilosophers, pane);
        Scene scene = new Scene(pane, width, height);

        pane.drawInitialFormation();
        philosophersLogic.createAndStartThreads();

        stage.setTitle("Philosophers");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}