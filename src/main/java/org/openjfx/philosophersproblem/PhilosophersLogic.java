package org.openjfx.philosophersproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class PhilosophersLogic {
    private int numberOfPhilosophers;
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

    public void createAndStartDeadlockProtocolThreads() {
        startState = true;
        createDeadlockThreads();
        createMutexes();
        startThreads();
    }

    public void createAndStartSynchronizedProtocolThreads() {
        startState = true;
        createFixedThreads();
        createMutexes();
        startThreads();
    }

    public void createDeadlockThreads() {
        threadList.clear();
        IntStream.range(0, numberOfPhilosophers).forEach(i -> threadList.add(new DeadlockedPhilosopher(i, this)));
    }

    public void createFixedThreads() {
        threadList.clear();
        IntStream.range(0, numberOfPhilosophers).forEach(i -> threadList.add(new SynchronizedPhilosopher(i, this)));
    }

    private void createMutexes() {
        IntStream.range(0, numberOfPhilosophers).forEach(i -> mutexes.add(new ReentrantLock()));
    }

    public void startThreads() {
        threadList.forEach(Philosopher::start);
    }

    public void closeThreads() {
        threadList.forEach(thread -> thread.setRunning(false));
    }

    public int getNumberOfPhilosophers() {
        return numberOfPhilosophers;
    }

    public void setNumberOfPhilosophers(int numberOfPhilosophers) {
        this.numberOfPhilosophers = numberOfPhilosophers;
    }

    public PhilosophersPane getPhilosophersPane() {
        return philosophersPane;
    }

    public ReentrantLock getMutex(int i) {
        return mutexes.get(i);
    }

    public boolean allThreadsFinished() {
        AtomicBoolean threadsAreRunning = new AtomicBoolean(false);
        threadList.forEach(thread -> threadsAreRunning.set(threadsAreRunning.get() || thread.isAlive()));
        return !threadsAreRunning.get();
    }

    public void checkThreadsStateAndStopThem() {
        if (startState)
            closeThreads();
    }
}