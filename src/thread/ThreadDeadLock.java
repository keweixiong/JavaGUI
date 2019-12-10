package thread;

// An example of ThreadDeadLock.
// to have the effect you expected, please de-note the lines with !!!!!!!!!!!!!!!
class A {
    synchronized void A_First(B b) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered A.A_First");
        
        try {
            Thread.sleep(100);
        } catch(Exception e) {
            System.out.println("A Interrupted");
        }
        
        System.out.println(name + " trying to call B.last()");
        b.last();
    }

    //synchronized void last() {   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    void last() {
        System.out.println("	Inside A.last");
    }
}
class B {
    synchronized void B_First(A a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered B.B_First");
        
        try {
            Thread.sleep(100);
        } catch(Exception e) {
            System.out.println("B Interrupted");
        }
        
        System.out.println(name + " trying to call A.last()");
        a.last();
    }
    // synchronized void last() {    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    void last() {
        System.out.println("Inside B.last");
    }
}
class ThreadDeadLock implements Runnable {
    A a = new A();
    B b = new B();
    ThreadDeadLock() {
        Thread.currentThread().setName("MainThread");
        Thread t = new Thread(this, " 	  RacingThread");
        t.start();
        
        System.out.println("Just start 2nd thread, back to main thread now");
        
        a.A_First(b); // get lock on a in this thread.
        
        System.out.println("Back to main thread");
    }
    public void run() {
    	System.out.println("	new thread begun");
    	
        b.B_First(a); // get lock on b in other thread.
        
        System.out.println("	Back to new thread");
        System.out.println("	new thread end");

    }
    public static void main(String args[]) {
        new ThreadDeadLock();
        System.out.println("Back to main()  main program, main program end");

    }
}