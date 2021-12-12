package org.openjfx.philosophersproblem;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.openjfx.geometry.Point;
import org.openjfx.geometry.ShapePane;

import java.util.stream.IntStream;

public class GeometryPane extends ShapePane {
    private double circleRadius;
    private double lineLength;
    private double distanceFromCenter;
    private int totalNumberOfPhilosophers;
    private Circle centralCircle;

    public GeometryPane(int numberOfPhilosophers, double actualWidth, double actualHeight) {
        super(actualWidth, actualHeight);
        this.circleRadius = 100;
        this.lineLength = 20;
        this.distanceFromCenter = 50;
        this.totalNumberOfPhilosophers = numberOfPhilosophers;
    }

    public void drawCircleInCenter(Color color) {
        Point centre = getCentrePoint();
        centralCircle = drawCircle(centre, circleRadius, color);
    }

    public void drawAllCirclesAroundCenter(Color color) {
        IntStream.range(0, totalNumberOfPhilosophers).forEach(i ->
                drawCircleAroundCenter(i, color));
    }

    public void drawCircleAroundCenter(int number, Color color) {
        Point circleCenter = getCenterOfCircleAroundCenter(number);
        drawCircle(circleCenter, circleRadius, color);
    }

    public Point getCenterOfCircleAroundCenter(int  number) {
        Point centre = getCentrePoint();
        double x = centre.getX() + distanceFromCenter * Math.cos(2 * Math.PI * number / totalNumberOfPhilosophers);
        double y = centre.getY() + distanceFromCenter * Math.sin(2 * Math.PI * number / totalNumberOfPhilosophers);
        return new Point(x, y);
    }

    public void drawAllLinesAroundCenter(Color color) {
        IntStream.range(0, totalNumberOfPhilosophers).forEach(i ->
                drawLineAroundCenter(i, color));
    }

    public void drawLineAroundCenter(int number, Color color) {
        Point startPoint = getStartOfLineAroundCenter(number);
        Point endPoint = getEndOfLineAroundCenter(number);
        drawLine(startPoint, endPoint, color);
    }

    public Point getStartOfLineAroundCenter(int number) {
        Point centre = getCentrePoint();
        double x = centre.getX() + distanceFromCenter * Math.cos(2 * Math.PI * number / totalNumberOfPhilosophers + 100);
        double y = centre.getY() + distanceFromCenter  * Math.sin(2 * Math.PI * number / totalNumberOfPhilosophers+ 100);
        return new Point(x, y);
    }

    public Point getEndOfLineAroundCenter(int number) {
        Point centre = getCentrePoint();
        double x = centre.getX() + (distanceFromCenter  + lineLength) * Math.cos(2 * Math.PI * number / totalNumberOfPhilosophers + 100);
        double y = centre.getY() + (distanceFromCenter  + lineLength) * Math.sin(2 * Math.PI * number / totalNumberOfPhilosophers + 100);
        return new Point(x, y);
    }

    public void recenterAllCircles() {
        setCenterOfCircle(0, getCentrePoint());
        IntStream.range(1, totalNumberOfPhilosophers + 1).forEach(i ->
                setCenterOfCircle(i, getCenterOfCircleAroundCenter(i)));
    }

    public void recenterAllLines() {
        IntStream.range(0, totalNumberOfPhilosophers).forEach(i ->
                setEndpointsOfLine(i, getStartOfLineAroundCenter(i), getEndOfLineAroundCenter(i)));
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

    public Circle getCentralCircle() {
        return centralCircle;
    }
}
