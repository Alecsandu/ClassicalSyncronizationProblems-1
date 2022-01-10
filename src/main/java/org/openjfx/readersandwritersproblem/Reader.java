package org.openjfx.readersandwritersproblem;

public class Reader extends Thread {
    int readerId;
    boolean isRunning;
    ReadersWritersLogic parent;

    Reader(int id, ReadersWritersLogic readersWritersLogic){
        this.readerId = id;
        this.parent = readersWritersLogic;
        isRunning = true;
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
        parent.getReadersWritersPane().setReaderColorToThinking(this.readerId);
        Thread.sleep((int) (Math.random() * 10000));
        System.out.println("Reader " + this.readerId + " is done thinking.");
    }

    private void waitToRead() throws InterruptedException {
        parent.getReadersWritersPane().setReaderColorToWaiting(this.readerId);
        parent.readersSemaphore.acquire();
        if (parent.getReadersCount() == 0)
            parent.writersSemaphore.acquire();
        parent.incrementReaders();
        parent.readersSemaphore.release();
    }

    private void read() throws InterruptedException {
        parent.getReadersWritersPane().setReaderColorToReading(this.readerId);
        System.out.println("Reader " + readerId + " is reading.");
        Thread.sleep(4000);
        parent.readersSemaphore.acquire();
        parent.decrementReaders();
        if (parent.getReadersCount() == 0)
            parent.writersSemaphore.release();
        parent.readersSemaphore.release();
        System.out.println("Reader " + readerId + " is done reading.");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

}
