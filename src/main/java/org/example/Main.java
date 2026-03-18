package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws InterruptedException {

        //create and start a thread
        NumberThread r1 = new NumberThread(25, 50, 100);
        Thread t1 = new Thread(r1);
        t1.start();

        //create and start another!
        new Thread(new NumberThread(500, 500, 100)).start();

        Thread t2 = new Thread(new CharThread(65, 92, 250));
        t2.start();

        System.out.println("done");

    }
}
