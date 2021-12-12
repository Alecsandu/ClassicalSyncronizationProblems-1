package org.openjfx.producersandconsumersproblem;

public class Consumer extends Thread{
    private ProducerConsumerLogic producerConsumerLogic;
    private boolean isRunning;
    @Override
    public void run() {
        while (isRunning) {

        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
