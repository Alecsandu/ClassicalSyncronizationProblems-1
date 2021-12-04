package org.openjfx.philosophersproblem;

import javafx.scene.paint.Color;
import org.openjfx.geometry.GeometryPane;

public class PhilosophersPane extends GeometryPane {
    private boolean isDrawn = false;
    private boolean isActive = false;

    public PhilosophersPane(int numberOfPhilosophers, double actualWidth, double actualHeight) {
        super(numberOfPhilosophers, actualWidth, actualHeight);
    }

    public void drawInitialFormation() {
        if (!isDrawn && isActive) {
            drawTable();
            drawPhilosophers();
            drawChopsticks();
            this.isDrawn = true;
        }
    }

    public void setActive() {
        isActive = true;
    }

    public void setDeactive() {
        isActive = false;
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

    public synchronized void setPhilosopherColorToThinking(int i) {
        setColorOfCircle(i, Color.GREEN);
    }

    public synchronized void setPhilosopherColorToHungry(int i) {
        setColorOfCircle(i, Color.YELLOW);
    }

    public synchronized void setPhilosopherColorToEating(int i) {
        setColorOfCircle(i, Color.RED);
    }

    public synchronized void setChopstickAvailable(int i) {
        setColorOfLine(i, Color.BLACK);
    }

    public synchronized void setChopstickTaken(int i) {
        setColorOfLine(i, Color.RED);
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        this.isDrawn = drawn;
    }
}
