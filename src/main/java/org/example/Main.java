package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {






    static void main(/*String[] args*/)  {
        //how do we make the multi-threaded server respond faster to new clients?
        //create a pool of threads that we can re-user over time
        ExecutorService pool = Executors.newCachedThreadPool();

        //notice ppol wraps the runnable in Thread AND it calls Thread.start()
        pool.submit(new NumberThread(1,100, 50));
        pool.submit(new Thread(new CharThread(65,92, 10)));
        pool.submit(new Thread(new NumberThread(1000,1200, 25)));

        //paste in copied code here!
        pool.shutdown();
        try {
            if (!pool.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All threads finished!");
    }



    // using lists to wait (i.e. join) for thread completion
//    static void main(/*String[] args*/) throws InterruptedException {
//
//        int intitialCapacity = 20;
//        List<Thread> threads = new ArrayList<Thread>(intitialCapacity);
//
//        //instantiate 3 threads
//        threads.add(new Thread(new NumberThread(1,100, 50)));
//        threads.add(new Thread(new CharThread(65,92, 10)));
//        threads.add(new Thread(new NumberThread(1000,1200, 25)));
//
//        //check if threads are daemon (lower priority and terminated on Main.exe termination) or user
//        for(Thread t : threads)
//            System.out.println(t.isDaemon());
//
//        //start 3 threads
//        for(Thread t : threads)
//            t.start();
//
//        //wait for 3 threads to complete
//        for(Thread t : threads)
//            t.join();
//
//        System.out.println("All threads have completed!");
//    }


    //creating three threads and calling join()
//    static void main(/*String[] args*/) throws InterruptedException {
//
//        //create and start a thread
//        NumberThread r1 = new NumberThread(25, 50, 1);
//        Thread t1 = new Thread(r1);
//        // t1.start();
//
//        //create and start another BUT we have no thread reference :(
//        new Thread(new NumberThread(500, 500, 124)).start();
//
//        Thread t2 = new Thread(new CharThread(65, 92, 248));
//        //t2.start();
//
//        // put in the start list (i.e. doesnt guarantee that we run!)
//        t1.start();  t2.start();
//
//        //wait for threads to complete!
//        t1.join();  t2.join();
//
//        System.out.println("Finished!");
//    }
}
