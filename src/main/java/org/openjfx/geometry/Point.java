package org.openjfx.geometry;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void moveHigher(double y) {
        this.y = this.y - y;
    }

    public void moveLower(double y) {
        this.y = this.y + y;
    }

    public void moveLeft(double x) {
        this.x = this.x - x;
    }

    public void moveRight(double x) {
        this.x = this.x + x;
    }

}
