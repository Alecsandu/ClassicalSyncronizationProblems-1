package org.openjfx.geometry;

class Philosopher extends Thread {
    private final int id;
    private final PhilosophersLogic parent;
    private boolean isRunning = true;

    Philosopher(int id, PhilosophersLogic philosophersLogic) {
        this.id = id;
        this.parent = philosophersLogic;
    }

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

    private void think() throws InterruptedException {
        parent.getPhilosophersPane().setPhilosopherThinking(id);
        Philosopher.sleep(3000);
    }

    private void hungry() {
        parent.getPhilosophersPane().setPhilosopherHungry(id);
        tryToPickLeftChopstick();
        tryToPickRightChopstick();
        parent.getPhilosophersPane().setPhilosopherEating(id);
    }

    private void tryToPickLeftChopstick() {
        parent.getMutex(getLeftChopstickPosition()).lock();
        parent.getPhilosophersPane().setChopstickTaken(getLeftChopstickPosition());
    }

    private void tryToPickRightChopstick() {
        parent.getMutex(getRightChopstickPosition()).lock();
        parent.getPhilosophersPane().setChopstickTaken(getRightChopstickPosition());
    }

    private int getLeftChopstickPosition() {
        return id;
    }

    private int getRightChopstickPosition() {
        return (id + 1) % parent.getNumberOfPhilosophers();
    }

    private void eat() throws InterruptedException {
        Philosopher.sleep(2000);
        releaseLeftChopstick();
        releaseRightChopstick();
    }

    private void releaseLeftChopstick() {
        parent.getPhilosophersPane().setChopstickAvailable(getLeftChopstickPosition());
        parent.getMutex(getLeftChopstickPosition()).unlock();
    }

    private void releaseRightChopstick() {
        parent.getPhilosophersPane().setChopstickAvailable(getRightChopstickPosition());
        parent.getMutex(getRightChopstickPosition()).unlock();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
