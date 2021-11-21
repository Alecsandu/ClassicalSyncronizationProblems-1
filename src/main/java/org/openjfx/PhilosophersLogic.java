package org.openjfx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class PhilosophersLogic {
    private final int numberOfPhilosophers;
    private final List<DeadlockPhilosopher> threadList;
    private final PhilosophersPane philosophersPane;
    private final List<ReentrantLock> mutexes;

    private boolean startState;

    public PhilosophersLogic(int numberOfPhilosophers, PhilosophersPane philosophersPane) {
        this.numberOfPhilosophers = numberOfPhilosophers;
        this.philosophersPane = philosophersPane;
        this.threadList = new ArrayList<>();
        this.mutexes = new ArrayList<>();

        startState = false;
    }

    public void createAndStartThreads() {
        startState = true;
        createDeadlockThreads();
        createMutexes();
        startThreads();
    }

    public void createAndStartFixedThreads() {
        startState = true;
        createFixedThreads();
        createMutexes();
        startThreads();
    }

    public void createDeadlockThreads() {
        IntStream.range(0, numberOfPhilosophers).forEach(i -> threadList.add(new DeadlockPhilosopher(i, this)));
    }

    public void createFixedThreads() {
        IntStream.range(0, numberOfPhilosophers).forEach(i -> threadList.add(new PhilosopherFix(i, this)));
    }

    private void createMutexes() {
        IntStream.range(0, numberOfPhilosophers).forEach(i -> mutexes.add(new ReentrantLock()));
    }

    public void startThreads() {
        threadList.forEach(DeadlockPhilosopher::start);
    }

    public void closeThreads() {
        IntStream.range(0, threadList.size()).forEach(i -> threadList.get(i).setRunning(false));
        threadList.clear();
    }

    public PhilosophersPane getPhilosophersPane() {
        return philosophersPane;
    }

    public ReentrantLock getMutex(int i) {
        return mutexes.get(i);
    }

    public int getNumberOfPhilosophers() {
        return numberOfPhilosophers;
    }

    public void checkThreadsStateAndStopThem() {
        if (startState)
            closeThreads();
    }
}