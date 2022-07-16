import java.io.*;

public class IOFileCharBufCopy {

// Write texts to file using OutputStreamWriter specifying its charset encoding.
// Read byte-by-byte using FileInputStream.
// Read char-by-char using InputStreamReader specifying its charset encoding.

    public static void main(String[] args) {
        String message = "Hi,您好!\nthis is the 2nd row ";   // with non-ASCII chars
        System.out.println("the message is: ["+ message+"]");
        // Java internally stores char in UCS-2/UTF-16
        // Print the characters stored with Hex codes

        System.out.println("\nthe message in Hex in Java program with UTF-16 is: ");

        for (int i = 0; i < message.length(); ++i) {
            char aChar = message.charAt(i);
            System.out.printf("[%d]'%c'(%04X) ", (i+1), aChar, (int)aChar);
        }
        System.out.println();

        // Try these charsets for encoding text file
        String[] csStrs = {"UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16", "GB2312", "GBK", "BIG5"};
        String outFileExt = "-Out.txt";   // Output filenames are "charset-out.txt"

        // Write text file in the specified file encoding charset
        System.out.println("\nthe charset would be : " );
        for (int i = 0; i < csStrs.length; ++i) {
            try (OutputStreamWriter out =
                         new OutputStreamWriter(
                                 new FileOutputStream("IOFileChar"+csStrs[i] + outFileExt), csStrs[i]);
                 BufferedWriter bufOut = new BufferedWriter(out)) {  // Buffered for efficiency
                System.out.println(out.getEncoding());  // Print file encoding charset
                bufOut.write(message);
                bufOut.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Wirte file finished\n");

        // Read raw bytes from various encoded files
        //   to check how the characters were encoded.
        System.out.println("\nto read raw bytes from file, the content are different...");

        for (int i = 0; i < csStrs.length; ++i) {
            try (BufferedInputStream in = new BufferedInputStream(  // Buffered for efficiency
                    new FileInputStream("IOFileChar"+ csStrs[i] + outFileExt))) {
                System.out.printf("%10s", csStrs[i]);    // Print file encoding charset
                int inByte;
                while ((inByte = in.read()) != -1) {
                    System.out.printf("%02X ", inByte);   // Print Hex codes
                }
                System.out.println();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Read text file with character-stream specifying its encoding.
        // The char will be translated from its file encoding charset to
        //   Java internal UCS-2.
        System.out.println("\nto read char from file with different encode, the content are the same...");

        for (int i = 0; i < csStrs.length; ++i) {
            try (InputStreamReader in =
                         new InputStreamReader(
                                 new FileInputStream("IOFileChar"+csStrs[i] + outFileExt), csStrs[i]);
                 BufferedReader bufIn = new BufferedReader(in)) {  // Buffered for efficiency
                System.out.println(in.getEncoding());  // print file encoding charset
                int inChar;
                int count = 0;
                while ((inChar = in.read()) != -1) {
                    ++count;
                    System.out.printf("[%d]'%c'(%04X) ", count, (char)inChar, inChar);
                }
                System.out.println();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // read line by line
        System.out.println("\nto read the String from file row by row...");

         for (int i = 0; i < csStrs.length; ++i) {
            try (InputStreamReader in =
                         new InputStreamReader(
                                 new FileInputStream("IOFileChar"+csStrs[i] + outFileExt), csStrs[i]);
                 BufferedReader bufIn = new BufferedReader(in)) {  // Buffered for efficiency
                System.out.println(in.getEncoding());  // print file encoding charset
                int inChar;
                String strDataRead;
                int count = 0;
                while (( strDataRead = bufIn.readLine()) != null) {
                    ++count;
                    System.out.printf("  row %d: %s\n", count, strDataRead);
                }
                System.out.println();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}