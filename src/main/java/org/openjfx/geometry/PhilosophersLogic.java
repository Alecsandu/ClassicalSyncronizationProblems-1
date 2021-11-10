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

    public PhilosophersLogic(int numberOfPhilosophers, PhilosophersPane philosophersPane) {
        this.numberOfPhilosophers = numberOfPhilosophers;
        this.philosophersPane = philosophersPane;
        this.threadList = new ArrayList<>();
        this.mutexes = new ArrayList<>();
    }

    public void createAndStartThreads() {
        createThreads();
        startThreads();
    }

    public void createThreads() {
        IntStream.range(0, numberOfPhilosophers).forEach(i -> threadList.add(new Philosopher(i, this)));
        IntStream.range(0, numberOfPhilosophers).forEach(i -> mutexes.add(new ReentrantLock()));
    }

    public void closeThreads() {
        IntStream.range(0, numberOfPhilosophers).forEach(i -> threadList.get(i).setRunning(false));
    }

    public void startThreads() {
        threadList.forEach(t -> t.start());
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
}