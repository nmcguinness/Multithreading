package org.example;

/**
 * A worker task that prints a sequence of numbers with a delay between each one.
 */
public class NumberThread implements Runnable {

    private final int min;
    private final int max;
    private final int sleepMs;

    /**
     * Creates a new number-printing task.
     *
     * @param min the first number in the range (inclusive)
     * @param max the last number in the range (inclusive)
     * @param sleepMs the delay in milliseconds between each printed number
     * @throws IllegalArgumentException if {@code sleepMs} is negative or {@code min > max}
     */
    public NumberThread(int min, int max, int sleepMs) {
        if (sleepMs < 0)
            throw new IllegalArgumentException("sleep cannot be negative");

        if (min > max)
            throw new IllegalArgumentException("min cannot be greater than max");

        this.min = min;
        this.max = max;
        this.sleepMs = sleepMs;
    }

    /**
     * Starts the task logic for this worker.
     */
    @Override
    public void run() {
        doWork();
    }

    /**
     * Prints each number in the configured range and pauses between prints.
     * <p>
     * If the thread is interrupted while sleeping, the interrupt flag is restored
     * and the method exits early.
     */
    private void doWork() {
        for (int i = min; i <= max; i++) {
            System.out.println("Number: " + i);

            try {
                Thread.sleep(sleepMs);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
