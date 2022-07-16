import java.util.concurrent.*;

public class CallableThreadPoolTest {
    public static void main(String[] args) {
        //int numWorkers = Integer.parseInt(args[0]);
        int numWorkers = 3;

        ExecutorService pool = Executors.newCachedThreadPool();
        CallableWorkerThread workers[] = new CallableWorkerThread[numWorkers];
        Future[] futures = new Future[numWorkers];

        for (int i = 0; i < numWorkers; ++i) {
            workers[i] = new CallableWorkerThread(i + 1);
            futures[i] = pool.submit(workers[i]);    // 1 dispatch the worker, and 2 return the futures
        }
        for (int i = 0; i < numWorkers; ++i) {
            try {
                System.out.println("begin to wait for result ..., worker " + (i+1) + " may STUCK!!!");

                System.out.println(futures[i].get() + " ended");   // 3 futures.get the return of call(),
                // it will get stuck here coz when waiting for the result if the result is not ready;
                // and other futures will not be invoked ;
                // it means that many worker begin to work, and when to get the result 1 by 1, could be stuck,

                // so I just don't know when we should use this function. !!! ??
                // but futures[i].get(timeout:, unit ) should be ok, it would not be stuck, will wait for sometime.


            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        }
    }
}