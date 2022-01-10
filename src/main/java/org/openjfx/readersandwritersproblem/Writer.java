package org.openjfx.readersandwritersproblem;

public class Writer extends Thread {
    int writerId;
    boolean isRunning;
    ReadersWritersLogic parent;

    Writer(int id, ReadersWritersLogic readersWritersLogic){
        writerId = id;
        this.parent = readersWritersLogic;
        isRunning = true;
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
        parent.getReadersWritersPane().setWriterColorToThinking(this.writerId);
        Thread.sleep((int) (Math.random() * 10000));
        System.out.println("Writer " + writerId + " is done thinking.");
    }

    private void waitToWrite() throws InterruptedException {
        parent.getReadersWritersPane().setWriterColorToWaiting(this.writerId);
        parent.writersSemaphore.acquire();
    }

    private void write() throws InterruptedException {
        parent.getReadersWritersPane().setWriterColorToWriting(this.writerId);
        System.out.println("Writer " + writerId + " is writing.");
        Thread.sleep(4000);
        parent.writersSemaphore.release();
        System.out.println("Writer " + writerId + " is done writing.");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
