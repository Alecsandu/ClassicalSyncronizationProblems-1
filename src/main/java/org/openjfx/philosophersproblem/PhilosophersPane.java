package org.openjfx.philosophersproblem;

import javafx.scene.paint.Color;

public class PhilosophersPane extends GeometryPane {

    public PhilosophersPane(int numberOfPhilosophers, double actualWidth, double actualHeight) {
        super(numberOfPhilosophers, actualWidth, actualHeight);
    }

    public void drawInitialFormation() {
        eraseShapes();
        drawTable();
        drawPhilosophers();
        drawChopsticks();
    }

    private void drawTable() {
        setCircleRadius(100);
        drawCircleInCenter(Color.GRAY);
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

    public synchronized void setPhilosopherColorToThinking(int i) {
        setColorOfCircle(i + 1, Color.GREEN);
    }

    public synchronized void setPhilosopherColorToHungry(int i) {
        setColorOfCircle(i + 1, Color.YELLOW);
    }

    public synchronized void setPhilosopherColorToEating(int i) {
        setColorOfCircle(i + 1, Color.RED);
    }

    public synchronized void setChopstickAvailable(int i) {
        setColorOfLine(i, Color.BLACK);
    }

    public synchronized void setChopstickTaken(int i) {
        setColorOfLine(i, Color.RED);
    }
}
