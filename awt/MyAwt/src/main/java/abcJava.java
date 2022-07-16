import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class abcJava {
    public static void main(String[] args) {
        // char and int
        char  cc1 = 'a';   // two bytes   2^16个数字，
        int   ii1 = 10;
        ii1 = cc1 + ii1 ;
        System.out.println("char + int: ii1= " + ii1 );

        //  String - integer
        System.out.println(Integer.parseInt("123"));  // return a value of int;
        System.out.println(Integer.valueOf("123"));    // return an Ojbect Integer

        // int -> String
        System.out.println(String.valueOf(123));
        System.out.println(Integer.toString(123));
        System.out.println(123+ "") ;

        // bytes short integer
        byte[] bytes2 = new byte[50];
        Arrays.fill(bytes2, (byte) 'a');
        System.out.println(" byte short integer");

        // char  Char
        char c = 'a';
        Character C = 'A';

        System.out.println(C.toString());  // for Character, C has more methods than c
        Character.isDigit(C);              // static method   Character.
        //   System.out.println(Character.getNumericValue(c)); //  char -> integer.    ??? unicode

        // Integer
        System.out.println(Integer.toBinaryString(15));   // Integer method() , static
        System.out.println(Integer.toHexString(15));
        System.out.println(Integer.max(12,15));
        System.out.println(Integer.reverse(64));
        System.out.println(Integer.reverseBytes(62));
        System.out.println(Integer.bitCount(7));
        System.out.println(Integer.valueOf("123"));

        // String.
        char[] c1 = new char[100];
        String s1 = "this is a test";
        System.out.println("   s1.method() \ns1.toUpperCase() = "+s1.toUpperCase());
        System.out.println("s1.length() = "+s1.length());
        System.out.println("s1.replace('t'-> 'a') = "+s1.replace('t', 'a'));
        System.out.println("s1.substriing(5) = "+s1.substring(5));
        System.out.println("s1.concat(---) = "+s1.concat("---"));
        System.out.println("s1.equal() = "+s1.equals("this"));
        System.out.println("s1.trim() = "+s1.trim());
        System.out.println("s1.charAt(2) = "+s1.charAt(2));

        System.out.println("String.method() \n String.valueOf()=" + String.valueOf("123"));
        String s6 ;
        s6 = String.format("String.format(): %s %s", s1,s1);
        System.out.println(s6);


        c1= s1.toCharArray();            // string -> char array
        System.out.println("c1= " + c1.toString()+ " " + Arrays.toString(c1));
        System.out.println("String.toString=" + s1.toString());


        System.out.println("Array.method()");



        // byte
         byte  byte1 = 13;
        System.out.println("byte1 = " + byte1);   // byte is like  an integer, which is 8bits.
        byte1 = 98;
        System.out.println("byte1 = " + byte1);
        System.out.println("cast byte to char: " +  (char) byte1) ;     // cast byte to char


        // string -> bytes -> string
        byte bytesAbc[] = "ABC".getBytes(StandardCharsets.UTF_16);
        System.out.println("\n ABC -> UTF_16 bytes: "+ Arrays.toString(bytesAbc));
        String strAbc = new String( bytesAbc, StandardCharsets.UTF_16);
        System.out.println("byte.toString[]="+ bytesAbc.toString());   // only print the reference
        System.out.println("string new from bytes UTF-16: " + strAbc);


        bytesAbc = "ABC".getBytes(StandardCharsets.UTF_8);
        System.out.println(" ABC -> UTF_8 bytes: "+ Arrays.toString(bytesAbc));

        strAbc = new String( bytesAbc, StandardCharsets.UTF_8);
        System.out.println("string new from bytes UTF-8: " + strAbc);

        // merge together into one sentence
        //    convert the string -> bytes  , and from bytes ->   string .
        //    can change to different charset.
        String strabc = new String("Abc".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_16);
        System.out.println("ABC shift charset from UTF-8-> UTF-16" + strabc);




    }
}
