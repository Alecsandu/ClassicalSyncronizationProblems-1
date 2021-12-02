package org.openjfx.philosophersproblem;

public abstract class Philosopher extends Thread {
    protected int philosopherId;
    protected boolean isRunning;
    protected PhilosophersLogic parent;

    @Override
    public void run() {
        try {
            while (isRunning) {
                think();
                hungry();
                eat();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected abstract void think() throws InterruptedException;

    protected abstract void hungry();

    protected abstract void eat() throws InterruptedException;

    protected void tryToPickLeftChopstick() {
        parent.getMutex(getLeftChopstickPosition()).lock();
        parent.getPhilosophersPane().setChopstickTaken(getLeftChopstickPosition());
    }

    protected void tryToPickRightChopstick() {
        parent.getMutex(getRightChopstickPosition()).lock();
        parent.getPhilosophersPane().setChopstickTaken(getRightChopstickPosition());
    }

    protected synchronized int getLeftChopstickPosition() {
        return philosopherId;
    }

    protected synchronized int getRightChopstickPosition() {
        return (philosopherId + 1) % parent.getNumberOfPhilosophers();
    }

    protected void releaseLeftChopstick() {
        parent.getPhilosophersPane().setChopstickAvailable(getLeftChopstickPosition());
        parent.getMutex(getLeftChopstickPosition()).unlock();
    }

    protected void releaseRightChopstick() {
        parent.getPhilosophersPane().setChopstickAvailable(getRightChopstickPosition());
        parent.getMutex(getRightChopstickPosition()).unlock();
    }

    public int getPhilosopherId() {
        return philosopherId;
    }

    public void setPhilosopherId(int id) {
        this.philosopherId = id;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public PhilosophersLogic getParent() {
        return parent;
    }

    public void setParent(PhilosophersLogic parent) {
        this.parent = parent;
    }
}
