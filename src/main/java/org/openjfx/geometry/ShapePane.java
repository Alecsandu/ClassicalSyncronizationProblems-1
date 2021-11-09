package org.openjfx.geometry;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.openjfx.Point;

import java.util.ArrayList;
import java.util.List;

public class ShapePane extends Pane {

    List<Circle> circles;
    List<Line> lines;

    public ShapePane() {
        super();
        circles = new ArrayList<>();
        lines = new ArrayList<>();
    }

    public final void drawCircleAndSaveIt(Point point, double radius, Color color) {
        circles.add(drawCircle(point, radius, color));
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
        lines.add(drawLine(p1, p2, color));
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
        return new Point(getWidth() / 2, getHeight() / 2);
    }
}
