package org.openjfx.geometry;

import javafx.scene.paint.Color;

import java.util.stream.IntStream;

public class GeometryPane extends ShapePane {
    double circleRadius;
    double lineLength;
    double distanceFromCenter;
    private int totalNumberOfPhilosophers;

    public GeometryPane(int numberOfPhilosophers, double actualWidth, double actualHeight) {
        super(actualWidth, actualHeight);
        this.circleRadius = 100;
        this.lineLength = 20;
        this.distanceFromCenter = 50;
        this.totalNumberOfPhilosophers = numberOfPhilosophers;
    }

    public void drawCircleInCenter(Color color) {
        Point centre = getCentrePoint();
        drawCircle(centre, circleRadius, color);
    }

    public void drawAllCirclesAroundCenter(Color color) {
        IntStream.range(0, totalNumberOfPhilosophers).forEach(i -> drawCircleAroundCenter(i, color));
    }

    public void drawCircleAroundCenter(int number, Color color) {
        Point centre = getCentrePoint();
        double x = centre.getX() + distanceFromCenter * Math.cos(2 * Math.PI * number / totalNumberOfPhilosophers);
        double y = centre.getY() + distanceFromCenter * Math.sin(2 * Math.PI * number / totalNumberOfPhilosophers);
        Point point = new Point(x, y);
        drawCircleAndSaveIt(point, circleRadius, color);
    }

    public void drawAllLinesAroundCenter(Color color) {
        IntStream.range(0, totalNumberOfPhilosophers).forEach(i ->
                drawLineAroundCenter(i, color));
    }

    public void drawLineAroundCenter(int number, Color color) {
        Point centre = getCentrePoint();
        double x1 = centre.getX() + distanceFromCenter * Math.cos(2 * Math.PI * number / totalNumberOfPhilosophers + 100);
        double y1 = centre.getY() + distanceFromCenter  * Math.sin(2 * Math.PI * number / totalNumberOfPhilosophers+ 100);
        double x2 = centre.getX() + (distanceFromCenter  + lineLength) * Math.cos(2 * Math.PI * number / totalNumberOfPhilosophers + 100);
        double y2 = centre.getY() + (distanceFromCenter  + lineLength) * Math.sin(2 * Math.PI * number / totalNumberOfPhilosophers + 100);
        drawLineAndSaveIt(new Point(x1, y1), new Point(x2, y2), color);
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

    public int getTotalNumberOfPhilosophers() {
        return totalNumberOfPhilosophers;
    }

    public void setTotalNumberOfPhilosophers(int totalNumberOfPhilosophers) {
        this.totalNumberOfPhilosophers = totalNumberOfPhilosophers;
    }

    public double getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(double distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }
}
