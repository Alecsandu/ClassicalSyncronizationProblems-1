package org.openjfx;

class Philosopher extends Thread {
    protected final int id;
    protected final PhilosophersLogic parent;
    protected boolean isRunning = true;

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

    protected void think() throws InterruptedException {
        Philosopher.sleep(3000);
    }

    protected void hungry() {
        parent.getPhilosophersPane().setPhilosopherHungry(id);
        tryToPickLeftChopstick();
        tryToPickRightChopstick();
    }

    protected void tryToPickLeftChopstick() {
        parent.getMutex(getLeftChopstickPosition()).lock();
        parent.getPhilosophersPane().setChopstickTaken(getLeftChopstickPosition());
    }

    protected void tryToPickRightChopstick() {
        parent.getMutex(getRightChopstickPosition()).lock();
        parent.getPhilosophersPane().setChopstickTaken(getRightChopstickPosition());
    }

    protected synchronized int getLeftChopstickPosition() {
        return id;
    }

    protected synchronized int getRightChopstickPosition() {
        return (id + 1) % parent.getNumberOfPhilosophers();
    }

    protected void eat() throws InterruptedException {
        parent.getPhilosophersPane().setPhilosopherEating(id);
        Philosopher.sleep(4000);
        releaseLeftChopstick();
        releaseRightChopstick();
        parent.getPhilosophersPane().setPhilosopherThinking(id);
    }

    protected void releaseLeftChopstick() {
        parent.getPhilosophersPane().setChopstickAvailable(getLeftChopstickPosition());
        parent.getMutex(getLeftChopstickPosition()).unlock();
    }

    protected void releaseRightChopstick() {
        parent.getPhilosophersPane().setChopstickAvailable(getRightChopstickPosition());
        parent.getMutex(getRightChopstickPosition()).unlock();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
