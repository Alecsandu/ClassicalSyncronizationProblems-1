package org.openjfx;

import javafx.scene.paint.Color;
import org.openjfx.geometry.GeometryPane;

public class PhilosophersPane extends GeometryPane {

    public PhilosophersPane(int n) {
        super();
        setTotalNumber(n);
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

    public void drawInitialFormation() {
        drawTable();
        drawPhilosophers();
        drawChopsticks();
    }

    public void setPhilosopherThinking(int i) {
        setColorOfCircle(i, Color.GREEN);
    }

    public void setPhilosopherHungry(int i) {
        setColorOfCircle(i, Color.YELLOW);
    }

    public void setPhilosopherEating(int i) {
        setColorOfCircle(i, Color.RED);
    }

    public void setChopstickAvailable(int i) {
        setColorOfLine(i, Color.BLACK);
    }

    public void setChopstickTaken(int i) {
        setColorOfLine(i, Color.RED);
    }
}
