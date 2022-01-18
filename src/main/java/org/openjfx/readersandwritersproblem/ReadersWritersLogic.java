package org.openjfx.readersandwritersproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class ReadersWritersLogic {
    private final int numberOfReaders;
    private final int numberOfWriters;
    private List<Reader> readersList;
    private List<Writer> writersList;

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

    public void createReaders(){
        for (int i = 0; i < numberOfReaders; i++){
            readersList.add(new Reader(i,this));
        }
    }

    public void createWriters(){
        for (int i = 0; i < numberOfWriters; i++){
            writersList.add(new Writer(i,this));
        }
    }

    public void stopReadersWriters(){
        IntStream.range(0, readersList.size()).forEach(i -> readersList.get(i).setRunning(false));
        IntStream.range(0, writersList.size()).forEach(i -> writersList.get(i).setRunning(false));
        readersList.clear();
        writersList.clear();
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
}
