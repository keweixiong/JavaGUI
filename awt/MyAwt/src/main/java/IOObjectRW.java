import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class IOObjectRW implements Serializable {

    String name;
    int age;

    public IOObjectRW(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "IOObjectRW{" +
                "name=" + name +
                ", age='" + age + '\'' +
                '}';
    }

    // Write Object and Read Object
    // .WriteObject()  .WriteChars()

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("IOObjectRW2");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        IOObjectRW orw = new IOObjectRW("name1", 123);
        oos.writeObject(orw);

        String strabc =  orw.toString();
        oos.writeChars (strabc);

        oos.close();

        IOObjectRW orw2 ;
        FileInputStream fis = new FileInputStream("IOObjectRW2");
        ObjectInputStream ois = new ObjectInputStream( fis);
        orw2 = (IOObjectRW) ois.readObject();
        System.out.println("Object read from file:");
        System.out.println(orw2.toString());

        byte[] babc = new byte[60] ;
        int i = ois.read(babc);
        System.out.println("bytes read from file: i=" + i + " abc=" + Arrays.toString(babc) );
        System.out.println("bytes -> chars: [" + new String(babc, StandardCharsets.UTF_16) + "]");


        //--
        String s = "Hello";
        try {
            // create a new file with an ObjectOutputStream
            FileOutputStream out = new FileOutputStream("IOObjectRW");
            ObjectOutputStream oout = new ObjectOutputStream(out);
            // write something in the file
            oout.writeChars(s);
            oout.writeChars("World");
            // close the stream
            oout.close();
            // create an ObjectInputStream for the file we created before
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream("IOObjectRW"));
            // read and print what we wrote before
            for (int i2 = 0; i2 < 10; i2++) {
                System.out.print("" + ois2.readChar());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
