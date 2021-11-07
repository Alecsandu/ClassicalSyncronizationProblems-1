package org.openjfx;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class MyPane extends FlowPane {
    MyCanvas canvas;
    int numberOfPhilosophers;

    public MyPane(MyCanvas canvas, int n) {
        super();
        setAlignment(Pos.CENTER);
        getChildren().add(canvas);
        this.canvas = canvas;
        this.numberOfPhilosophers = n;
    }

    private void drawTable() {
        canvas.drawCircleInCentre(200, Color.GREY);
    }

    private void drawPhilosophers() {
        canvas.drawCirclesAroundCentre(numberOfPhilosophers, Color.GREEN);
    }

    private void drawChopsticks() {
        canvas.drawLinesAroundCentre(numberOfPhilosophers, Color.BLACK);
    }

    public void drawInitialFormation() {
        drawTable();
        drawChopsticks();
        drawPhilosophers();
    }
}
