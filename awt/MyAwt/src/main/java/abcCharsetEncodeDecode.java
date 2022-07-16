import java.nio.ByteBuffer;
import java.nio.charset.Charset;


// todo
//     the dir structure may be different.
//     save to github.     src -> local depository (just add some mark) -> cloud (can select the specific folder)
//     take the note/ lesson
//     file - > database



public class abcCharsetEncodeDecode {
    public static void main(String[] args) {
        // Try these charsets for encoding
        String[] charsetNames = {"US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16",
                "UTF-16BE", "UTF-16LE", "GBK", "BIG5"};

        String message = "Hi,您好!";  // message with non-ASCII characters
        // Print UCS-2 in hex codes
        System.out.printf("%10s: ", "UCS-2");
        for (int i = 0; i < message.length(); i++) {
            System.out.printf("%04X ", (int)message.charAt(i));    // each char with 2 bytes: FFFF
        }
        System.out.println();

        for (String charsetName: charsetNames) {
            // Get a Charset instance given the charset name string
            Charset charset = Charset.forName(charsetName);
            System.out.printf("%10s: ", charset.name());

            // Encode the Unicode UCS-2 characters into a byte sequence in this charset.
            ByteBuffer bb = charset.encode(message);
            while (bb.hasRemaining()) {
                System.out.printf("%02X ", bb.get());  // Print hex code
            }
            System.out.println();
            bb.rewind();
/*
            while (bb.hasRemaining()) {
                System.out.printf("%c ", bb.get());  // Print char code
            }
            System.out.println();
            bb.rewind();
*/
        }

        System.out.println("\nThe default charset is " + Charset.defaultCharset());
        System.out.println("The default charset is " + System.getProperty("file.encoding"));
        System.out.println("available charset "+Charset.availableCharsets());
        System.out.println("\nsystem properties " + System.getProperties());


          message = "Hi,您好!";   // with non-ASCII chars
        // Java internally stores char in UCS-2/UTF-16
        // Print the characters stored with Hex codes
        System.out.println("\nmessage length ( 6 )   =" + message.length());
        for (int i = 0; i < message.length(); ++i) {
            char aChar = message.charAt(i);
            System.out.printf("[%d]'%c'(%04X) ", (i+1), aChar, (int)aChar);
        }


    }


}