package org.openjfx.readersandwritersproblem;

import javafx.scene.paint.Color;

public class ReadersWritersPane extends GeometryPane {
    boolean isActive;
    boolean isDrawn;

    private int numberOfReaders;
    private int numberOfWriters;

    public ReadersWritersPane(int numOfReaders, int numOfWriters, double actualWidth, double actualHeight) {
        super(actualWidth, actualHeight);
        isDrawn = false;
        isActive = false;
        this.numberOfReaders = numOfReaders;
        this.numberOfWriters = numOfWriters;
    }

    public void drawInitialFormation() {
        if (!isDrawn()) {
            drawData();
            drawReaders(numberOfReaders, Color.WHITE);
            drawWriters(numberOfWriters, Color.WHITE);
            this.isDrawn = true;
        }
    }

    public void drawData(){
        setCircleRadius(100);
        drawCircleInCenter(Color.GREY);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setReaderColorToThinking(int i) {
        setColorOfCircle(i + 1, Color.GREEN);
    }
    public void setReaderColorToWaiting(int i) {
        setColorOfCircle(i + 1, Color.YELLOW);
    }
    public void setReaderColorToReading(int i) {
        setColorOfCircle(i + 1, Color.RED);
    }

    public void setWriterColorToThinking(int i) {
        setColorOfCircle(i + numberOfReaders + 1, Color.GREEN);
    }
    public void setWriterColorToWaiting(int i) {
        setColorOfCircle(i + numberOfReaders + 1, Color.YELLOW);
    }
    public void setWriterColorToWriting(int i) {
        setColorOfCircle(i + numberOfReaders + 1, Color.RED);
    }


    public void setIsActive() {
        isActive = true;
    }

    public void setIsNotActive() {
        isActive = false;
    }

    public boolean isDrawn() { return isDrawn; }

}
