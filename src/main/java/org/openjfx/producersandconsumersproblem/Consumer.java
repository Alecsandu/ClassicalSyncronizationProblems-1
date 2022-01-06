package org.openjfx.producersandconsumersproblem;

public class Consumer extends Thread{
    private ProducerConsumerLogic producerConsumerLogic;
    private Band band;
    private boolean isRunning;

    public Consumer(Band band){
        this.band = band;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
                try
                {
                    Thread.sleep((int) (Math.random() * 1000));
                    band.removeObject();
                }
                catch (InterruptedException ex){}
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
