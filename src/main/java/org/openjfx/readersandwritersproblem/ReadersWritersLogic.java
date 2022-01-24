package org.openjfx.readersandwritersproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ReadersWritersLogic {
    private int numberOfReaders;
    private int numberOfWriters;
    private final List<Reader> readersList;
    private final List<Writer> writersList;

    private Semaphore readersSemaphore;
    private Semaphore writersSemaphore;
    private int readersCount;

    ReadersWritersPane readersWritersPane;

    public ReadersWritersLogic(int numberOfReaders, int numberOfWriters, ReadersWritersPane readersWritersPane){
        this.numberOfReaders = numberOfReaders;
        this.numberOfWriters = numberOfWriters;
        this.readersList = new ArrayList<>();
        this.writersList = new ArrayList<>();

        readersSemaphore = new Semaphore(1);
        writersSemaphore = new Semaphore(1);
        readersCount = 0;

        this.readersWritersPane = readersWritersPane;
    }

    public void createAndStartReadersWriters(){
        this.createReaders();
        this.createWriters();

        readersList.forEach(Reader::start);
        writersList.forEach(Writer::start);
    }

    public void createReaders() {
        readersList.clear();
        for (int i = 0; i < numberOfReaders; i++){
            readersList.add(new Reader(i,this));
        }
    }

    public void createWriters() {
        writersList.clear();
        for (int i = 0; i < numberOfWriters; i++){
            writersList.add(new Writer(i,this));
        }
    }

    public void stopReadersWriters(){
        readersList.forEach(thread -> thread.setRunning(false));
        writersList.forEach(thread -> thread.setRunning(false));
    }

    public boolean areThreadsAlive() {
        final boolean[] result = {false};
        readersList.forEach(thread -> {
            result[0] = result[0] || thread.isAlive();
        });
        writersList.forEach(thread -> {
            result[0] = result[0] || thread.isAlive();
        });
        return result[0];
    }

    public void incrementReaders() { readersCount += 1; }
    public void decrementReaders() { readersCount -= 1; }
    public int getReadersCount() { return readersCount; }

    public ReadersWritersPane getReadersWritersPane() {
        return readersWritersPane;
    }

    public Semaphore getReadersSemaphore() {
        return readersSemaphore;
    }

    public void setReadersSemaphore(Semaphore readersSemaphore) {
        this.readersSemaphore = readersSemaphore;
    }

    public Semaphore getWritersSemaphore() {
        return writersSemaphore;
    }

    public void setWritersSemaphore(Semaphore writersSemaphore) {
        this.writersSemaphore = writersSemaphore;
    }

    public void setNumberOfReaders(int n) {
        numberOfReaders = n;
    }

    public void setNumberOfWriters(int n) {
        numberOfWriters = n;
    }
}
