package org.openjfx.producersandconsumersproblem;

import javafx.scene.paint.Color;
import org.openjfx.geometry.Point;
import org.openjfx.geometry.ShapePane;

import java.util.stream.IntStream;

public class GeometryPane extends ShapePane {
    private final int bufferSize;
    private int squareLength;

    public GeometryPane(int bufferSize, double actualWidth, double actualHeight) {
        super(actualWidth, actualHeight);
        this.bufferSize = bufferSize;
        this.squareLength = 100;    //I don't know why you chose to set this to 100
    }

    public void drawAllSquares(Color color) {
        IntStream.range(0, bufferSize)
                .forEach(i -> drawSquareOfTotal(i, color));
    }

    public void drawSquareOfTotal(int number, Color color) {
        Point point = getCenterOfSquareOfTotal(number);
        drawSquare(point, squareLength, color);
    }

    public void recenterAllSquares() {
        IntStream.range(0, bufferSize)
                .forEach(i -> setCenterOfSquare(i, getCenterOfSquareOfTotal(i)));
    }

    public Point getCenterOfSquareOfTotal(int number) {
        Point point = getCentrePoint();
        point.setX(getVectorStartPoint() + squareLength * number);
        return point;
    }

    private double getVectorStartPoint() {
        return (getPaneWidth() - getTotalVectorWidth()) / 2 + (double) squareLength / 2;
    }

    private double getTotalVectorWidth() {
        return bufferSize * squareLength;
    }

    public int getSquareLength() {
        return squareLength;
    }

    public void setSquareLength(int squareLength) {
        this.squareLength = squareLength;
    }
}
