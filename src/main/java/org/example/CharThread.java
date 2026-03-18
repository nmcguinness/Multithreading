package org.example;


public class CharThread implements Runnable {
    /**
     * 1. Runnable
     * 2. run()
     * 3. doWork()
     * 4. public CharThread(int minCharInt, int maxCharInt, int sleepMS)
     *  System.out.println((char) minCharInt);  //'A'
     *  5. Create and start a CharThread
     *
     */

    private int minCharAsInt, maxCharAsInt, sleepMS;

    public CharThread(int minCharAsInt, int maxCharAsInt, int sleepMS)
            throws IllegalArgumentException
    {
        if(sleepMS < 0)
            throw new IllegalArgumentException("sleep cannot be negative");
        if(minCharAsInt > maxCharAsInt)
            throw new IllegalArgumentException("min > max!");

        this.sleepMS = sleepMS;
        this.maxCharAsInt = maxCharAsInt;
        this.minCharAsInt = minCharAsInt;
    }

    //Step 2 - implement Run()
    @Override
    public void run() {
        doWork();
    }

    private void doWork(){
        for(int i = minCharAsInt; i <= maxCharAsInt; i++)
        {
            System.out.println("Char: " + (char) i);
            try {
                Thread.sleep(sleepMS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
