package org.example;

//Step 1 - implement Runnable
public class NumberThread implements Runnable {
    private int min, max, sleepMS;

    public NumberThread(int min, int max, int sleepMS)
            throws IllegalArgumentException
    {

        if(sleepMS < 0)
            throw new IllegalArgumentException("sleep cannot be negative");
        if(min > max)
            throw new IllegalArgumentException("min > max!");

        this.sleepMS = sleepMS;
        this.max = max;
        this.min = min;
    }

    //Step 2 - implement Run()
    @Override
    public void run() {
        doWork();
    }

    private void doWork(){
        for(int i = min; i <= max; i++)
        {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(sleepMS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
