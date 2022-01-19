package org.openjfx.readersandwritersproblem;

import javafx.scene.paint.Color;

public class ReadersWritersPane extends GeometryPane {
    boolean isDrawn;

    private final int numberOfReaders;
    private final int numberOfWriters;

    public ReadersWritersPane(int numOfReaders, int numOfWriters, double actualWidth, double actualHeight) {
        super(actualWidth, actualHeight);
        isDrawn = false;
        this.numberOfReaders = numOfReaders;
        this.numberOfWriters = numOfWriters;
    }

    public void drawInitialFormation() {
        if (!isDrawn()) {
            drawData();
            drawReaders(numberOfReaders, Color.GREEN);
            drawWriters(numberOfWriters, Color.GREEN);
            this.isDrawn = true;
        }
    }

    public void drawData(){
        setCircleRadius(100);
        drawCircleInCenter(Color.GREY);
    }

    public synchronized void setReaderColorToThinking(int i) {
        setColorOfCircle(i + 1, Color.GREEN);
    }
    public synchronized void setReaderColorToWaiting(int i) {
        setColorOfCircle(i + 1, Color.YELLOW);
    }
    public synchronized void setReaderColorToReading(int i) {
        setColorOfCircle(i + 1, Color.RED);
    }
    public synchronized void setWriterColorToThinking(int i) {
        setColorOfCircle(i + numberOfReaders + 1, Color.GREEN);
    }
    public synchronized void setWriterColorToWaiting(int i) {
        setColorOfCircle(i + numberOfReaders + 1, Color.YELLOW);
    }
    public synchronized void setWriterColorToWriting(int i) {
        setColorOfCircle(i + numberOfReaders + 1, Color.RED);
    }

    public boolean isDrawn() { return isDrawn; }

}
