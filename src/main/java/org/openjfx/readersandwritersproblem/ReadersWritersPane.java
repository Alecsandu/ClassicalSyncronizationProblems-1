package org.openjfx.readersandwritersproblem;

import javafx.scene.paint.Color;

public class ReadersWritersPane extends GeometryPane {
    private int numberOfReaders;
    private int numberOfWriters;

    public ReadersWritersPane(int numOfReaders, int numOfWriters, double actualWidth, double actualHeight) {
        super(actualWidth, actualHeight);
        this.numberOfReaders = numOfReaders;
        this.numberOfWriters = numOfWriters;
    }

    public void drawInitialFormation() {
        eraseShapes();
        drawData();
        drawReaders(numberOfReaders, Color.GREEN);
        drawWriters(numberOfWriters, Color.GREEN);
    }

    public void drawData(){
        setCircleRadius(100);
        drawCircleInCenter(Color.GRAY);
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

    public void setNumberOfReaders(int n) {
        numberOfReaders = n;
    }

    public void setNumberOfWriters(int n) {
        numberOfWriters = n;
    }
}
