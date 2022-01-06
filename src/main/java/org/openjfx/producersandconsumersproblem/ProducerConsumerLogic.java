package org.openjfx.producersandconsumersproblem;

public class ProducerConsumerLogic {
    ProducerConsumerPane producerConsumerPane;
    int bufferSize;
    Producer producer;
    Consumer consumer;
    Band band;

    private boolean startState;

    public ProducerConsumerLogic(int bufferSize, ProducerConsumerPane producerConsumerPane){
        this.bufferSize = bufferSize;
        this.producerConsumerPane = producerConsumerPane;

        startState = false;
    }

    public void createAndStartConsumerProducer(){
        this.createBand();
        this.createConsumer();
        this.createProducer();

        producer.start();
        consumer.start();
        startState = true;
    }

    public void createBand(){
        this.band = new Band(this.bufferSize, this.producerConsumerPane);
    }

    public void createConsumer(){
        this.consumer = new Consumer(this.band);
    }

    public void createProducer(){
        this.producer = new Producer(this.band);
    }

    public void stopConsumerAndProducer() {
        if (startState) {
            closeProducer();
            closeConsumer();
        }
    }

    public void closeProducer(){
        this.producer.setRunning(false);
    }

    public void closeConsumer(){
        this.consumer.setRunning(false);
    }

}
