package org.example;

import org.example.UI.LoginManagerGUI;
import javax.swing.SwingUtilities;

public class MultiThreads implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " is running");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginManagerGUI.main(null);
            }
        });
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MultiThreads());
        Thread thread2 = new Thread(new MultiThreads());

        thread1.start();
        thread2.start();
    }
}
