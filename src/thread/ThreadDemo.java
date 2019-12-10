package thread;
// Thread Basic

// Create a second thread.
class MyProcess implements Runnable {
    Thread t;
    MyProcess() {
        // Create a new, second thread
    	System.out.println(" we will create a new thread");
    	
        t = new Thread(this, "Demo Thread");
        System.out.println("Child thread created, not start yet: " + t);
        t.start(); // Start the thread
    	System.out.println("child thread has statrted, here is main thread");

    
    }

    // This is the entry point for the second thread.
    public void run() {
    	System.out.println("	at the begining of Child thread");
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("	Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("	Child interrupted !!!.");
        }
        System.out.println("	Exiting child thread.");
    }
}

class ThreadDemo {
    public static void main(String args[]) {
        new MyProcess(); // create a new thread
        
        System.out.println(" after creating the thread, now just back to the main process");
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
           System.out.println("Main thread interrupted !!!");
        }
        System.out.println("Main thread exiting.");
    }
}