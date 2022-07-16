import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpByteClient {
        public static void main(String[] args) {
            int iPortServer = 12901;
            Socket socket = null;
            BufferedReader socketReader = null;
            BufferedWriter socketWriter = null;
            byte[] bytes = new byte[2048];
            String MsgOut ;
            try {
                // Create a socket that will connect to localhost at port 12900.
                // Note that the server
                // must also be running at localhost and 12900.
                socket = new Socket("localhost", iPortServer);

                System.out.println("Started client socket at "
                        + socket.getLocalSocketAddress());

                // Create a buffered reader and writer using the socket's
                // input and output streams
                InputStream is =  socket.getInputStream();
                OutputStream os = socket.getOutputStream();


                // Create a buffered reader for user's input
                BufferedReader consoleReader
                        = new BufferedReader(new InputStreamReader(System.in));

                String promptMsg = "Please enter a message (Bye to quit):";
                String MsgFromConsole = null;

                System.out.print(promptMsg);
                while ((MsgFromConsole = consoleReader.readLine()) != null) {
                    if (MsgFromConsole.equalsIgnoreCase("bye")) {
                        break;
                    }
                    // Add a new line to the message to the server,
                    // because the server reads one line at a time.
                    MsgOut = MsgFromConsole + "你好";
                    os.write((MsgOut).getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    System.out.println("Message sent to server:" + MsgOut);
                    // Read and display the message from the server
                    int read = is.read(bytes);
                    System.out.println(read +" bytes from Server: " + new String(bytes,0,read));

                    System.out.println(); // Print a blank line
                    System.out.print(promptMsg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Finally close the socket
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
