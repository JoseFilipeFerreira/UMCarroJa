package Utils;

class EraserThread implements Runnable {
    private boolean stop;

    public EraserThread() {}

    public void run () {
        stop = true;
        while (stop) {
            System.out.print("\010*");
            try {
                Thread.sleep(1);
            } catch(InterruptedException ignored) { }
        }
    }

    public void stopMasking() {
        this.stop = false;
    }
}
