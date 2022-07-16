

import java.io.File;
        import java.io.IOException;
        import java.io.RandomAccessFile;

        // Run the program several times, the count will increase.
public class IOFileRandomRW {
    public static void main(String[] args) throws IOException {
        String fileName = "IOFileRandomRW";
        File fileObject = new File(fileName);

        if (!fileObject.exists()) {
            initialWrite(fileName); //  w   w   w .   d e  m   o 2   s  .  c  o  m
        }
        // Open the file in read-write mode
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            int counter = raf.readInt();
            String msg = raf.readUTF();

            System.out.println("File Read Counter: " + counter);
            System.out.println("File Text: " + msg);
            // Increment the file read counter by 1
            incrementReadCounter(raf);
        }
    }

    public static void incrementReadCounter(RandomAccessFile raf)
            throws IOException {

        long currentPosition = raf.getFilePointer();

        // Set the file pointer in the beginning
        raf.seek(0);
        // Read the counter and increment it by 1
        int counter = raf.readInt();
        counter++;


        raf.seek(0);
        raf.writeInt(counter);

        // Restore the file pointer
        raf.seek(currentPosition);
    }

    public static void initialWrite(String fileName)
            throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            // Write the file read counter as zero
            raf.writeInt(0);

            // Write a message
            raf.writeUTF("Hello world!");
        }
    }
}