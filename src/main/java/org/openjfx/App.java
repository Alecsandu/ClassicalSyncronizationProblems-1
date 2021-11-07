package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        double width = 640;
        double height = 480;
        int numberOfPhilosophers = 5;

        MyCanvas canvas = new MyCanvas(width, height);
        MyPane pane = new MyPane(canvas, numberOfPhilosophers);
        Scene scene = new Scene(pane, width, height);

        pane.drawInitialFormation();

        stage.setTitle("Philosophers");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}