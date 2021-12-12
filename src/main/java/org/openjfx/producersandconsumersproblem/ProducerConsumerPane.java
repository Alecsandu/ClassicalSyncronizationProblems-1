package org.openjfx.producersandconsumersproblem;

import javafx.scene.paint.Color;

public class ProducerConsumerPane extends GeometryPane {
    private boolean isDrawn = false;

    public ProducerConsumerPane(int bufferSize, double actualWidth, double actualHeight) {
        super(bufferSize, actualWidth, actualHeight);

    }

    public void drawInitialFormation() {
        if (!isDrawn()) {
            drawBuffer();
            this.isDrawn = true;
        }
    }

    private void drawBuffer() {
        setSquareLength(100);
        drawAllSquares(Color.RED);
    }

    public void recenterSquares() {
        if (isDrawn()) {
            setSquareLength(100);
            recenterAllSquares();
        }
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }
}
