// Join() ensure the main thread end after the son thread

// ，一旦线程进入实例的同步方法，没有其他线程可以进入相同实例的同步方法。然而，该实例的其他不同步方法却仍然可以被调用。

// 需要同步的资源是在Callme,  Caller是new Thread
class Callme {
	
// option 1:  synchronized, we are will get [..] [ ..] [..] instead of [....[[ ]]]
	// not ganrenteed [1 ] [2] [3]
    synchronized void call(String msg) {

// option 2:  Not synchronized
//   void call(String msg) {
    	
    	
        System.out.print("in exclusive area now [" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
       }
       System.out.println("]");
    }
}

class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;
    public Caller(Callme targ, String s) {
    	System.out.printf("\nprepare to start the sub thread now %s",s);

        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();  //start the new thread
    }
    public void run() {
    	System.out.printf("\n just started the sub thread now %s\n",msg);
 //       target.call(msg);    // Option1 there is Sync control in Callme
 //   	System.out.println(msg);   // Option2  no Sync control here 
    	
    	
        // Option3 synchronize calls to call()
    	            synchronized(target) { // synchronized block
                target.call(msg);
            }
    }
}

class ThreadSync {
    public static void main(String args[]) {
        Callme target = new Callme();
        
        System.out.println("begin of the main program");
        Caller ob1 = new Caller(target, "   1 Hello");
        Caller ob2 = new Caller(target, "   2 Synchronized");
        Caller ob3 = new Caller(target, "   3 World");
        System.out.println("waiting for sub-thread to end");

        // wait for threads to end, ensure the main thread finished after other threads
        try {
          ob1.t.join();
          ob2.t.join();
          ob3.t.join();
       } catch(InterruptedException e) {
          System.out.println("Interrupted");
       }
        System.out.println("end of the main program");

    }
}