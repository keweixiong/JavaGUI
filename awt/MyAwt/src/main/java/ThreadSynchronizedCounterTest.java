public class ThreadSynchronizedCounterTest {

    public static void main(String[] args) {
        Thread threadIncrement = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    ThreadSynchronizedCounter.increment();

                }
            }
        };

        Thread threadDecrement = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    ThreadSynchronizedCounter.decrement();

                }
            }
        };

        threadIncrement.start();
        threadDecrement.start();
    }
}