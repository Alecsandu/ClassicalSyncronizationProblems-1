package org.openjfx.producersandconsumersproblem;

public class Producer extends Thread{
    private Band band;
    private boolean isRunning;

    public Producer(Band band){
        this.band = band;
        this.isRunning = true;
    }
    @Override
    public void run() {
        while (isRunning) {
            for (int i = 1; i <= 10; i++)
                try
                {
                    Thread.sleep((int) (Math.random() * 1000));
                    band.placeObject(true);
                }
                catch (InterruptedException ignored){}
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
