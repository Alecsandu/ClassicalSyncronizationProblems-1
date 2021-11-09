package org.openjfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.GREEN;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        double width = 640;
        double height = 480;
        int numberOfPhilosophers = 5;

        PhilosophersPane pane = new PhilosophersPane(numberOfPhilosophers);
        Scene scene = new Scene(pane, width, height);

        pane.drawInitialFormation();
        pane.setPhilosopherHungry(2);

        stage.setTitle("Philosophers");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}