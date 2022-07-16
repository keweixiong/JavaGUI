import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** let multy thread running in the thread pool created in advance  */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //int numWorkers = Integer.parseInt(args[0]);
        //int threadPoolSize = Integer.parseInt(args[1]);

        int numWorkers = 8;
        int threadPoolSize = 4;

        ExecutorService pool =
                Executors.newFixedThreadPool(threadPoolSize);       // 1 create the pool

        ThreadPoolWorker[] workers = new ThreadPoolWorker[numWorkers];
        for (int i = 0; i < numWorkers; ++i) {
            workers[i] = new ThreadPoolWorker(i+1);        // 2 create the thread worker
            pool.execute(workers[i]);                             // 3 let worker run in the pool
        }

        pool.shutdown();
    }
}