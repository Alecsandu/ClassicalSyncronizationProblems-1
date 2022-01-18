package org.openjfx.readersandwritersproblem;

public class Writer extends Thread {
    private final int writerId;
    private final ReadersWritersLogic parent;
    private boolean isRunning;

    Writer(int id, ReadersWritersLogic readersWritersLogic){
        this.writerId = id;
        this.parent = readersWritersLogic;
        this.isRunning = true;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                think();
                waitToWrite();
                write();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void think() throws InterruptedException{
        parent.getReadersWritersPane().setWriterColorToThinking(writerId);
        Thread.sleep((int) (Math.random() * 10000));
        System.out.println("Writer " + writerId + " is done thinking.");
    }

    private void waitToWrite() throws InterruptedException {
        parent.getReadersWritersPane().setWriterColorToWaiting(writerId);
        parent.getWritersSemaphore().acquire();
    }

    private void write() throws InterruptedException {
        parent.getReadersWritersPane().setWriterColorToWriting(writerId);
        System.out.println("Writer " + writerId + " is writing.");
        Thread.sleep(4000);
        parent.getWritersSemaphore().release();
        System.out.println("Writer " + writerId + " is done writing.");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
