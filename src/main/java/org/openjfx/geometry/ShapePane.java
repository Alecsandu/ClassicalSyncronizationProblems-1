package org.openjfx.geometry;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class ShapePane extends Pane {
    private final List<Circle> circles;
    private final List<Line> lines;
    private double actualWidth;
    private double actualHeight;

    public ShapePane(double actualWidth, double actualHeight) {
        super();
        this.circles = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.actualWidth = actualWidth;
        this.actualHeight = actualHeight;
    }

    public final void drawCircleAndSaveIt(Point point, double radius, Color color) {
        this.circles.add(drawCircle(point, radius, color));
    }

    public final Circle drawCircle(Point point, double radius, Color color) {
        Circle circle = new Circle();
        circle.setFill(color);
        circle.setRadius(radius);
        circle.setCenterX(point.getX());
        circle.setCenterY(point.getY());
        this.getChildren().add(circle);
        return circle;
    }

    public final void drawLineAndSaveIt(Point p1, Point p2, Color color) {
        this.lines.add(drawLine(p1, p2, color));
    }

    public final Line drawLine(Point p1, Point p2, Color color) {
        Line line = new Line();
        line.setFill(color);
        line.setStartX(p1.getX());
        line.setStartY(p1.getY());
        line.setEndX(p2.getX());
        line.setEndY(p2.getY());
        this.getChildren().add(line);
        return line;
    }

    public final void eraseShapes() {
        this.lines.clear();
        this.circles.clear();
    }

    public final void setColorOfCircle(int i, Color color) {
        getCircle(i).setFill(color);
    }

    public final void setColorOfLine(int i, Color color) {
        getLine(i).setStroke(color);
    }

    public final Circle getCircle(int i) {
        return circles.get(i);
    }

    public final Line getLine(int i) {
        return lines.get(i);
    }

    public final Point getCentrePoint() {
        return new Point(getActualWidth() / 2, getActualHeight() / 2);
    }

    public double getActualWidth() {
        return actualWidth;
    }

    public void setActualWidth(double actualWidth) {
        this.actualWidth = actualWidth;
    }

    public double getActualHeight() {
        return actualHeight;
    }

    public void setActualHeight(double actualHeight) {
        this.actualHeight = actualHeight;
    }

    public List<Circle> getCircles() {
        return circles;
    }

    public List<Line> getLines() {
        return lines;
    }
}
