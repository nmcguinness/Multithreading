package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws InterruptedException {

        //create and start a thread
        NumberThread r1 = new NumberThread(25, 50, 1);
        Thread t1 = new Thread(r1);
       // t1.start();

        //create and start another BUT we have no thread reference :(
        new Thread(new NumberThread(500, 500, 124)).start();

        Thread t2 = new Thread(new CharThread(65, 92, 248));
        //t2.start();

        // put in the start list (i.e. doesnt guarantee that we run!)
        t1.start();  t2.start();

        //wait for threads to complete!
        t1.join();  t2.join();

        System.out.println("Finished!");




    }
}
