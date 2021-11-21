package org.openjfx;

import javafx.scene.paint.Color;
import org.openjfx.geometry.GeometryPane;

public class PhilosophersPane extends GeometryPane {
    private boolean drawn = false;

    public PhilosophersPane(int n) {
        super();
        setTotalNumber(n);
    }

    public void drawInitialFormation() {
        if (!drawn) {
            drawTable();
            drawPhilosophers();
            drawChopsticks();
            drawn = true;
        }
    }

    private void drawTable() {
        setCircleRadius(100);
        drawCircleInCenter(Color.GREY);
    }

    private void drawPhilosophers() {
        setCircleRadius(30);
        setDistanceFromCenter(150);
        drawAllCirclesAroundCenter(Color.GREEN);
    }

    private void drawChopsticks() {
        setDistanceFromCenter(50);
        drawAllLinesAroundCenter(Color.BLACK);
    }

    public synchronized void setPhilosopherThinking(int i) {
        setColorOfCircle(i, Color.GREEN);
    }

    public synchronized void setPhilosopherHungry(int i) {
        setColorOfCircle(i, Color.YELLOW);
    }

    public synchronized void setPhilosopherEating(int i) {
        setColorOfCircle(i, Color.RED);
    }

    public synchronized void setChopstickAvailable(int i) {
        setColorOfLine(i, Color.BLACK);
    }

    public synchronized void setChopstickTaken(int i) {
        setColorOfLine(i, Color.RED);
    }
}
