package Utils;

import java.io.*;

class EraserThread implements Runnable {
    private boolean stop;

    public EraserThread() {}

    public void run () {
        stop = true;
        while (stop) {
            System.out.print("\010*");
            try {
                Thread.currentThread().sleep(1);
            } catch(InterruptedException ie) { }
        }
    }

    public void stopMasking() {
        this.stop = false;
    }
}
