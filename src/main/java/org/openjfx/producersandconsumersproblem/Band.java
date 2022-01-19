package org.openjfx.producersandconsumersproblem;

import java.util.ArrayList;

public class Band {
    private final ArrayList<Boolean> band;
    private final int maxBandDimension;
    private final ProducerConsumerPane producerConsumerPane;

    public Band(int maxBandDimension, ProducerConsumerPane producerConsumerPane)
    {
        band = new ArrayList<>(maxBandDimension);
        this.maxBandDimension = maxBandDimension;
        this.initializeBand();
        this.producerConsumerPane = producerConsumerPane;
    }

    public void initializeBand(){
        for (int i = 0; i < this.maxBandDimension; i++){
            band.add(false);
        }
    }

    public synchronized boolean isBandFull(){
        for (Boolean position : band) {
            if (!position) {
                return false;
            }
        }
        return true;

    }

    public int getFirstEmptyPosition(){
        for (int i = 0; i < band.size(); i++) {
            if (!band.get(i)) {
                return i;
            }
        }

        return -1;
    }

    public void addObject(){
        int idx = this.getFirstEmptyPosition();
        this.band.set(idx, Boolean.TRUE);
    }

    public synchronized void placeObject(boolean x) throws InterruptedException
    {
        while (isBandFull()) {
            wait();
        }
        this.addObject();
        this.producerConsumerPane.refillRectanglesAccordingToBand(this.band, maxBandDimension);
        notify();
    }

    public int getFirstTakenPosition(){
        for (int i = 0; i < band.size(); i++) {
            if (band.get(i)) {
                return i;
            }
        }

        return -1;
    }

    public void moveObjectsToLeft(){
        for (int i = 1; i < band.size(); i++) {
            band.set(i - 1, band.get(i));
        }
        band.set(band.size() - 1, false);
    }

    public void takeOutObject(){
        int idx = this.getFirstTakenPosition();
        this.band.set(idx, Boolean.FALSE);
        this.moveObjectsToLeft();
    }

    public synchronized void removeObject() throws InterruptedException
    {
        while (!band.get(0)) {
            wait();
        }
        this.takeOutObject();
        this.producerConsumerPane.refillRectanglesAccordingToBand(this.band, maxBandDimension);
        notify();
    }
}
