package org.openjfx.readersandwritersproblem;

public class Reader extends Thread {
    private final int readerId;
    private boolean isRunning;
    private final ReadersWritersLogic parent;

    Reader(int readerId, ReadersWritersLogic readersWritersLogic){
        this.readerId = readerId;
        this.parent = readersWritersLogic;
        this.isRunning = true;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                think();
                waitToRead();
                read();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void think() throws InterruptedException{
        parent.getReadersWritersPane().setReaderColorToThinking(readerId);
        Thread.sleep((int) (Math.random() * 10000));
        System.out.println("Reader " + readerId + " is done thinking.");
    }

    private void waitToRead() throws InterruptedException {
        parent.getReadersWritersPane().setReaderColorToWaiting(readerId);
        parent.getReadersSemaphore().acquire();
        if (parent.getReadersCount() == 0)
            parent.getWritersSemaphore().acquire();
        parent.incrementReaders();
        parent.getReadersSemaphore().release();
    }

    private void read() throws InterruptedException {
        parent.getReadersWritersPane().setReaderColorToReading(readerId);
        System.out.println("Reader " + readerId + " is reading.");
        Thread.sleep(3000);  // changed it from 4000
        parent.getReadersSemaphore().acquire();
        parent.decrementReaders();
        if (parent.getReadersCount() == 0)
            parent.getWritersSemaphore().release();
        parent.getReadersSemaphore().release();
        System.out.println("Reader " + readerId + " is done reading.");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

}
