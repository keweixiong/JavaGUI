package thread;
// Create multiple threads.
class NewThreadBCD implements Runnable {
    String name; // name of thread
    Thread t;
    NewThreadBCD(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("	New thread: " + t);
        t.start(); // Start the thread
    }

    // This is the entry point for thread.
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
               System.out.println("		Thread "+ name + ": " + i);
               Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("	Thread "+ name + "Interrupted");
        }
        System.out.println("	Thread "+ name + " exiting.");
    }
}

class ThreadMulti {
    public static void main(String args[]) {
        new NewThreadBCD("One"); // start threads
        new NewThreadBCD("Two");
        new NewThreadBCD("Three");
        try {
            // wait for other threads to end, if the time is long enough, the main thread will end prior the sub-thread.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }
}
