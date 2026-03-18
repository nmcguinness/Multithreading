package org.example;

/**
 * A worker task that prints a sequence of characters based on their integer codes,
 * with a delay between each one.
 */
public class CharThread implements Runnable {

    private final int minCharAsInt;
    private final int maxCharAsInt;
    private final int sleepMs;

    /**
     * Creates a new character-printing task.
     *
     * @param minCharAsInt the first character code in the range (inclusive)
     * @param maxCharAsInt the last character code in the range (inclusive)
     * @param sleepMs the delay in milliseconds between each printed character
     * @throws IllegalArgumentException if {@code sleepMs} is negative or the minimum code is greater than the maximum code
     */
    public CharThread(int minCharAsInt, int maxCharAsInt, int sleepMs) {
        if (sleepMs < 0)
            throw new IllegalArgumentException("sleep cannot be negative");

        if (minCharAsInt > maxCharAsInt)
            throw new IllegalArgumentException("min cannot be greater than max");

        this.minCharAsInt = minCharAsInt;
        this.maxCharAsInt = maxCharAsInt;
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
     * Prints each character in the configured range and pauses between prints.
     * <p>
     * If the thread is interrupted while sleeping, the interrupt flag is restored
     * and the method exits early.
     */
    private void doWork() {
        for (int i = minCharAsInt; i <= maxCharAsInt; i++) {
            System.out.println("Char: " + (char) i);

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
