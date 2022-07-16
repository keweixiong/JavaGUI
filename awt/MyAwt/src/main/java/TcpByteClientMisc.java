
import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.Socket;

        // write bytes to server, and print the response from server. then end.
        // it is a once off conversation.
public class TcpByteClientMisc {
    public static void main(String args[]) throws Exception {
        int c;
        // Create a socket connected to internic.net, port 43.
        Socket s = new Socket("whois.internic.net", 43);
        // Socket s = new Socket("localhost", 12900);
        // Obtain input and output streams.
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();

        // Construct a request string.
        String str = "baidu.com\n";
        // Convert to bytes.
        byte buf[] = str.getBytes();

        // Send request.
        out.write(buf);

        // Read and display response.
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
        s.close();
    }



}
