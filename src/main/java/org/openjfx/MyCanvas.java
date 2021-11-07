package org.openjfx;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.stream.IntStream;

public class MyCanvas extends Canvas {
    public MyCanvas(double v, double v1) {
        super(v, v1);
    }

    public void drawCircle(Point point, double radius, Color color) {
        this.getGraphicsContext2D().setFill(color);
        this.getGraphicsContext2D().fillOval(point.getX(), point.getY(), radius, radius);
    }

    public void drawLine(Point p1, Point p2, Color color) {
        this.getGraphicsContext2D().setStroke(color);
        this.getGraphicsContext2D().strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void drawCircleSizeAdjusted(Point point, double radius, Color color) {
        double halfOfRadius = radius / 2;
        point.moveHigher(halfOfRadius);
        point.moveLeft(halfOfRadius);
        drawCircle(point, radius, color);
    }

    private Point getCentrePoint() {
        return new Point(getWidth() / 2, getHeight() / 2);
    }

    public void drawCircleInCentre(double radius, Color color) {
        Point centre = getCentrePoint();
        drawCircleSizeAdjusted(centre, radius, color);
    }

    private void drawCircleAroundCentreOfTotal(double radius, double distanceFromCentre, int number, int totalNumberOfCircles, Color color) {
        Point centre = getCentrePoint();
        double x = centre.getX() + distanceFromCentre * Math.cos(2 * Math.PI * number / totalNumberOfCircles);
        double y = centre.getY() + distanceFromCentre * Math.sin(2 * Math.PI * number / totalNumberOfCircles);
        Point point = new Point(x, y);
        drawCircleSizeAdjusted(point, radius, color);
    }

    public void drawCirclesAroundCentre(int numberOfCircles, Color color) {
        IntStream.range(0, numberOfCircles).forEach(i ->
                drawCircleAroundCentreOfTotal(50, 130, i, numberOfCircles, color));
    }

    private void drawLineAroundCentreOfTotal(double radius, double distanceFromCentre, double length, int number, int totalNumberOfCircles, Color color) {
        Point centre = getCentrePoint();
        double x1 = centre.getX() + distanceFromCentre * Math.cos(2 * Math.PI * number / totalNumberOfCircles + 100);
        double y1 = centre.getY() + distanceFromCentre * Math.sin(2 * Math.PI * number / totalNumberOfCircles + 100);
        double x2 = centre.getX() + (distanceFromCentre + length) * Math.cos(2 * Math.PI * number / totalNumberOfCircles + 100);
        double y2 = centre.getY() + (distanceFromCentre + length) * Math.sin(2 * Math.PI * number / totalNumberOfCircles + 100);
        drawLine(new Point(x1,y1), new Point(x2, y2), color);
    }

    public void drawLinesAroundCentre(int numberOfLines, Color color) {
        IntStream.range(0, numberOfLines).forEach(i ->
                drawLineAroundCentreOfTotal(50, 60, 20, i, numberOfLines, color));
    }
}
