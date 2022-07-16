import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class IOScannerFormatter {
    public static void main(String[] args) {

   /** Scanner
        scanner to read the file just like read from keyboard
        in and out  not only for keyboard input, but a quick way to Read character file。
 Other than scanning System.in (keyboard), you can connect your Scanner to scan any input sources,
  such as a disk file or a network socket, and use the same set of methods nextInt(), nextDouble(),
  next(), nextLine() to parse the next int, double, String and line.
    */
        /**
         *  use Formatter to format the string and output to console/file
         */
        Scanner in = null;  // Construct a Scanner to scan a text file
        try {
            in = new Scanner(new File("file1.txt"));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

        /**   scanner input from system.in/console    */
        //Scanner in = new Scanner(System.in);
        System.out.println("\nplease input anything");

        /** input from in */
        System.out.println("System.in. next()" + in.next());

        // output  wither Formatter
        System.out.printf("%02x", 13);
        System.out.println(String.format("%02X", 13));


        Formatter fm = new Formatter();
        System.out.println(fm.format("%2d %-6s", 13, "abc"));

        try (Formatter out = new Formatter(new File("fileo.txt"))) {
            // Formatter out = new Formatter(System.out);
            out.format("Hi %s,%n", " wonderland");
            out.format("Hi %s", in.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
