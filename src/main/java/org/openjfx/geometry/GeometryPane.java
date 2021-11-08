package org.openjfx.geometry;

import javafx.scene.paint.Color;
import org.openjfx.Point;

import java.util.stream.IntStream;

public class GeometryPane extends ShapePane {
    double circleRadius;
    double lineLength;
    double distanceFromCenter;
    int totalNumber;
    public GeometryPane(double x, double y) {
        super(x, y);
        this.circleRadius = 100;
        this.lineLength = 20;
        this.distanceFromCenter = 50;
        this.totalNumber = 5;
    }
    public GeometryPane(double x, double y, double circleRadius, double lineLength, double distanceFromCenter, int totalNumber) {
        super(x, y);
        this.circleRadius = circleRadius;
        this.lineLength = lineLength;
        this.distanceFromCenter = distanceFromCenter;
        this.totalNumber = totalNumber;
    }

    public double getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(double circleRadius) {
        this.circleRadius = circleRadius;
    }

    public double getLineLength() {
        return lineLength;
    }

    public void setLineLength(double lineLength) {
        this.lineLength = lineLength;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public double getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(double distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }

    public void drawCircleInCenter(Color color) {
        Point centre = getCentrePoint();
        drawCircle(centre, circleRadius, color);
    }

    public void drawCircleAroundCenter(int number, Color color) {
        Point centre = getCentrePoint();
        double x = centre.getX() + distanceFromCenter * Math.cos(2 * Math.PI * number / totalNumber);
        double y = centre.getY() + distanceFromCenter * Math.sin(2 * Math.PI * number / totalNumber);
        Point point = new Point(x, y);
        drawCircleAndSaveIt(point, circleRadius, color);
    }

    public void drawAllCirclesAroundCenter(Color color) {
        IntStream.range(0, totalNumber).forEach(i ->
                drawCircleAroundCenter(i, color));
    }

    public void drawLineAroundCenter(int number, Color color) {
        Point centre = getCentrePoint();
        double x1 = centre.getX() + distanceFromCenter * Math.cos(2 * Math.PI * number / totalNumber + 100);
        double y1 = centre.getY() + distanceFromCenter  * Math.sin(2 * Math.PI * number / totalNumber+ 100);
        double x2 = centre.getX() + (distanceFromCenter  + lineLength) * Math.cos(2 * Math.PI * number / totalNumber + 100);
        double y2 = centre.getY() + (distanceFromCenter  + lineLength) * Math.sin(2 * Math.PI * number / totalNumber + 100);
        drawLineAndSaveIt(new Point(x1, y1), new Point(x2, y2), color);
    }

    public void drawAllLinesAroundCenter(int numberOfLines, Color color) {
        IntStream.range(0, numberOfLines).forEach(i ->
                drawLineAroundCenter(i, color));
    }
}
