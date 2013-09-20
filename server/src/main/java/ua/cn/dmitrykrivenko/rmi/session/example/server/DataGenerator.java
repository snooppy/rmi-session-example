package ua.cn.dmitrykrivenko.rmi.session.example.server;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class DataGenerator implements Runnable {

    private final LinkedList<Integer> data = new LinkedList<Integer>();
    private volatile boolean stop;

    public void run() {
        try {
            initData();
            while (!stop) {
                Thread.sleep(1000);
                changeData();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void initData() {
        synchronized (data) {
            for (int i = 1; i <= 5; i++) {
                data.add(i);
            }
        }
    }

    private void changeData() {
        synchronized (data) {
            int last = data.getLast();
            last++;
            data.removeFirst();
            data.add(last);
        }
    }

    public List<Integer> getData() {
        synchronized (data) {
            return new LinkedList<Integer>(data);
        }
    }

    public void stop() {
        stop = true;
    }
}
