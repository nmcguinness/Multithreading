package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        //create and start a thread
        NumberThread r1 = new NumberThread(1000, 50, 25);
        Thread t1 = new Thread(r1);
        t1.start();

        //create and start another!
        new Thread(new NumberThread(500, 500, 100)).start();

        t1.
    }
}
