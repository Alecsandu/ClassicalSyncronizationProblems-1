package org.openjfx.readersandwritersproblem;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.openjfx.geometry.Point;
import org.openjfx.geometry.ShapePane;

public class GeometryPane extends ShapePane {

    private double circleRadius;
    private Circle centralCircle;

    public GeometryPane(double actualWidth, double actualHeight) {
        super(actualWidth, actualHeight);
        this.circleRadius = 50;
    }

    public void drawCircleInCenter(Color color) {
        Point centre = getCentrePoint();
        centralCircle = drawCircle(centre, circleRadius, color);
    }

    public void drawReaders(int numberOfCircles, Color color){
        Point ReaderCoordinates = new Point(getActualWidth()/4, (getActualHeight() - 200)/numberOfCircles);
        circleRadius = ((getActualHeight() - 200)/numberOfCircles)/2;
        for (int i = 0; i < numberOfCircles; i++){
            drawCircle(ReaderCoordinates, circleRadius, color);
            ReaderCoordinates.setY(ReaderCoordinates.getY() + circleRadius*2);
        }
    }

    public void drawWriters(int numberOfCircles, Color color){
        Point ReaderCoordinates = new Point(getActualWidth()*3/4, (getActualHeight() - 200)/numberOfCircles);
        circleRadius = ((getActualHeight() - 200)/numberOfCircles)/2;
        for (int i = 0; i < numberOfCircles; i++){
            drawCircle(ReaderCoordinates, circleRadius, color);
            ReaderCoordinates.setY(ReaderCoordinates.getY() + circleRadius*2);
        }
    }

    public void setCircleRadius(double circleRadius){ this.circleRadius = circleRadius; }
}
