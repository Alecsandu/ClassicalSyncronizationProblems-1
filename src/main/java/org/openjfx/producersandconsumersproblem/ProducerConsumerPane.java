package org.openjfx.producersandconsumersproblem;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ProducerConsumerPane extends GeometryPane {

    public ProducerConsumerPane(int bufferSize, double actualWidth, double actualHeight) {
        super(bufferSize, actualWidth, actualHeight);
    }

    public void drawInitialFormation() {
        eraseShapes();
        drawBuffer();
    }

    private void drawBuffer() {
        setSquareLength(100);
        drawAllSquares(Color.RED);
    }

    public synchronized void refillRectanglesAccordingToBand(ArrayList<Boolean> positions, int bufferSize){
        for (int i = 0; i < positions.size(); i++){
            if (positions.get(i) == Boolean.TRUE){
                this.setSpotToOccupied(i);
            }
            else{
                this.setSpotToEmpty(i);
            }
        }
        for (int i = positions.size(); i < bufferSize; i++){
            this.setSpotToEmpty(i);
        }
    }

    public void setSpotToEmpty(int i){
        setFillOfRectangle(i, Color.BLACK);
    }

    public void setSpotToOccupied(int i){
        setFillOfRectangle(i, Color.GREEN);
    }
}
