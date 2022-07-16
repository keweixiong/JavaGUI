public class ThreadSyncChaos {

// This program is not synchronized.

    public static void main(String args[]) {
        class MyTask {/* w   w  w .   d e m    o  2 s .    c  o  m*/


 //           synchronized void call(String m) {
             void call(String m) {

                System.out.print("[" + m);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
                System.out.println("]");
            }
        }

        class MyThread implements Runnable {
            String msg;
            MyTask target;
            Thread t;

            public MyThread(MyTask targ, String s) {
                target = targ;
                msg = s;
        //        t = new Thread(this);      Way2      not easy to understand, so update it
        //        t.start();
            }

            public void run() {
                target.call(msg);
            }
        }
        MyTask target = new MyTask();
        MyThread ob1 = new MyThread(target, "A");
        MyThread ob2 = new MyThread(target, "B");
        MyThread ob3 = new MyThread(target, "C");


        // add the following 6 lines to replace the above ones.   Way1
        Thread t1 = new Thread(ob1);
        Thread t2 = new Thread(ob2);
        Thread t3 = new Thread( ob3);
        t1.start();;
        t2.start();
        t3.start();



        // wait for threads to
        /*
        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

         */
    }
}