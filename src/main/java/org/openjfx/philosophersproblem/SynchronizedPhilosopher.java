package org.openjfx.philosophersproblem;

public class SynchronizedPhilosopher extends Philosopher {
    SynchronizedPhilosopher(int id, PhilosophersLogic philosophersLogic) {
        this.philosopherId = id;
        this.isRunning = true;
        this.parent = philosophersLogic;
    }

    @Override
    protected void think() throws InterruptedException {
        DeadlockedPhilosopher.sleep(3000);
    }

    @Override
    protected void hungry() {
        this.parent.getPhilosophersPane().setPhilosopherColorToHungry(this.philosopherId);
        if (this.philosopherId % 2 == 0) {
            tryToPickLeftChopstick();
            tryToPickRightChopstick();
        } else {
            tryToPickRightChopstick();
            tryToPickLeftChopstick();
        }
    }

    @Override
    protected void eat() throws InterruptedException {
        this.parent.getPhilosophersPane().setPhilosopherColorToEating(this.philosopherId);
        DeadlockedPhilosopher.sleep(4000);
        releaseLeftChopstick();
        releaseRightChopstick();
        this.parent.getPhilosophersPane().setPhilosopherColorToThinking(this.philosopherId);
    }
}
