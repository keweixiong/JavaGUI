import static java.lang.Thread.sleep;

public class ThreadSynchronizedCounter {

    private static int count = 0;

    /**  looks like all the vars used in the method() will be locked*/
    public synchronized static void increment() {
        int i = count;
        System.out.println("+1 Count is " + i + " @ " + System.nanoTime());
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count= count + 1;
        if ( (count - i) != 1) {
            System.out.println("   +dirtied ori is " + i + " now is " + count+ " @ " + System.nanoTime());

        }
        else System.out.println("+3 ori is " + i + " now is " + count+ " @ " + System.nanoTime());

    }
 //   public synchronized   static void decrement() {

    public     static void decrement() {
        int iCountOri = count;
        System.out.println("-1 ori is " + iCountOri + " @ " + System.nanoTime() );

        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count = count - 1;
        if ((iCountOri - count) != 1 ) {
            System.out.println("    -dirtied  ori is " + iCountOri + " now " + count + " @ " + System.nanoTime() );
        }
        else
            System.out.println("-3 Count is ori = " + iCountOri + " now " + count + " @ " + System.nanoTime() );

    }
}