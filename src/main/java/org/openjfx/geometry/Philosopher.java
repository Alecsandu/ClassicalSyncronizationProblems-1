package org.openjfx.geometry;

class Philosopher extends Thread {
    private final int id;
    private final PhilosophersLogic parent;

    Philosopher(int id, PhilosophersLogic philosophersLogic) {
        this.id = id;
        this.parent = philosophersLogic;
    }

    @Override
    public void run() {
        try {
            while (true) {
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
        Thread.sleep(1000);
    }

    private void hungry() {
        parent.getPhilosophersPane().setPhilosopherHungry(id);
        tryToPickLeftChopstick();
        tryToPickRightChopstick();
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
        parent.getPhilosophersPane().setPhilosopherEating(id);
        Thread.sleep(1000);
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
}
