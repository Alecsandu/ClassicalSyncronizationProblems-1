package org.openjfx.philosophersproblem;

class DeadlockedPhilosopher extends Philosopher {
    DeadlockedPhilosopher(int id, PhilosophersLogic philosophersLogic) {
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
        tryToPickLeftChopstick();
        tryToPickRightChopstick();
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
