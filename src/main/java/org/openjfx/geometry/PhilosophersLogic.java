package org.openjfx.geometry;

import org.openjfx.PhilosophersPane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class PhilosophersLogic {
    private final int numberOfPhilosophers;
    private final List<Philosopher> threadList;
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
        createThreads();
        startThreads();
    }

    public void createThreads() {
        IntStream.range(0, numberOfPhilosophers).forEach(i -> threadList.add(new Philosopher(i, this)));
        IntStream.range(0, numberOfPhilosophers).forEach(i -> mutexes.add(new ReentrantLock()));
    }

    public void startThreads() {
        threadList.forEach(Philosopher::start);
    }

    public void closeThreads() {
        IntStream.range(0, numberOfPhilosophers).forEach(i -> threadList.get(i).setRunning(false));
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

    public void checkThreadsState() {
        if (startState)
            closeThreads();
    }
}