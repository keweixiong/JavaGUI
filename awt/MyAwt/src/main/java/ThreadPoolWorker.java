public class ThreadPoolWorker implements Runnable {
    private int workerNumber;

    ThreadPoolWorker(int workerNumber) {
        this.workerNumber = workerNumber;
        System.out.println("    Worker created " + workerNumber);

    }

    public void run() {
        // The thread simply prints 1 to 5
        // System.out.println("    Worker begin to work " + workerNumber);
        for (int i = 1; i <= 5; ++i) {
            System.out.printf("Worker %d: %d\n", workerNumber, i);
            try {
                // sleep for 0 to 0.5 second
                Thread.sleep((int)(Math.random() * 500));
            } catch (InterruptedException e) {}
        }
    }
}