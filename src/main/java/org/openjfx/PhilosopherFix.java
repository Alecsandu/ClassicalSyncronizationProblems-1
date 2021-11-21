package org.openjfx;

public class PhilosopherFix extends DeadlockPhilosopher {
    PhilosopherFix(int id, PhilosophersLogic philosophersLogic) {
        super(id, philosophersLogic);
    }

    @Override
    protected void hungry() {
        parent.getPhilosophersPane().setPhilosopherHungry(id);
        if (id % 2 == 0) {
            tryToPickLeftChopstick();
            tryToPickRightChopstick();
        } else {
            tryToPickRightChopstick();
            tryToPickLeftChopstick();
        }
    }
}
