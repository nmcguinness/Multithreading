package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates several ways to create, start, and wait for threads in Java.
 */
public class Main
{
    /**
     * Entry point for the program.
     *
     * @param args Command-line arguments (unused).
     * @throws InterruptedException Thrown if the main thread is interrupted while waiting.
     */
    public static void main(String[] args) throws InterruptedException
    {
        Main demo = new Main();

        // Run whichever demo you want to show in class.
        demo.demoUsingExecutorService();
        // demo.demoUsingThreadListAndJoin();
        // demo.demoUsingDirectThreadReferences();
    }

    /**
     * Demonstrates using an {@link ExecutorService} to manage worker threads.
     *
     * This is usually the preferred approach because the thread pool handles
     * thread creation and reuse for us. We submit {@link Runnable} tasks
     * directly to the pool and then wait for all work to finish.
     */
    public void demoUsingExecutorService()
    {
        // Create a cached thread pool.
        // This can create new threads as needed and reuse old ones when possible.
        ExecutorService pool = Executors.newCachedThreadPool();

        // Submit Runnable tasks directly.
        // The pool handles wrapping them in threads and starting them.
        pool.submit(new NumberThread(1, 100, 50));
        pool.submit(new CharThread(65, 92, 10));
        pool.submit(new NumberThread(1000, 1200, 25));

        // Stop accepting new tasks.
        pool.shutdown();

        try
        {
            // Wait for existing tasks to finish.
            if (!pool.awaitTermination(5, TimeUnit.SECONDS))
            {
                // Force shutdown if tasks take too long.
                pool.shutdownNow();
            }
        }
        catch (InterruptedException e)
        {
            // If interrupted while waiting, force shutdown and restore interrupt status.
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All threads finished!");
    }

    /**
     * Demonstrates storing {@link Thread} objects in a list so they can all be started
     * and later joined.
     *
     * This is useful when you want to manage a group of threads manually.
     *
     * @throws InterruptedException Thrown if the main thread is interrupted while joining.
     */
    public void demoUsingThreadListAndJoin() throws InterruptedException
    {
        int initialCapacity = 20;
        List<Thread> threads = new ArrayList<>(initialCapacity);

        // Create three thread objects and store them in a list.
        threads.add(new Thread(new NumberThread(1, 100, 50)));
        threads.add(new Thread(new CharThread(65, 92, 10)));
        threads.add(new Thread(new NumberThread(1000, 1200, 25)));

        // Check whether each thread is a daemon thread.
        // Daemon threads are background threads that do not keep the JVM alive.
        for (Thread thread : threads)
        {
            System.out.println("Is daemon: " + thread.isDaemon());
        }

        // Start all threads.
        for (Thread thread : threads)
        {
            thread.start();
        }

        // Wait for all threads to finish.
        for (Thread thread : threads)
        {
            thread.join();
        }

        System.out.println("All threads have completed!");
    }

    /**
     * Demonstrates creating thread references individually and calling {@link Thread#join()}
     * on the ones we kept references to.
     *
     * This example also highlights an important limitation:
     * if you do not keep a reference to a thread, you cannot later join it.
     *
     * @throws InterruptedException Thrown if the main thread is interrupted while joining.
     */
    public void demoUsingDirectThreadReferences() throws InterruptedException
    {
        // Create a Runnable and wrap it in a Thread.
        NumberThread numberTaskOne = new NumberThread(25, 50, 1);
        Thread threadOne = new Thread(numberTaskOne);

        // Create and start another thread immediately,
        // but do NOT keep a reference to it.
        // Because we do not store the Thread object, we cannot join it later.
        new Thread(new NumberThread(500, 500, 124)).start();

        // Create another thread and keep the reference.
        Thread threadTwo = new Thread(new CharThread(65, 92, 248));

        // Start the threads we have references to.
        threadOne.start();
        threadTwo.start();

        // Wait only for the threads we can still reference.
        threadOne.join();
        threadTwo.join();

        System.out.println("Finished!");
    }
}