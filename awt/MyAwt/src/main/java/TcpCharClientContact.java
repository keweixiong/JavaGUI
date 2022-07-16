import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;

// read from keyboard and send to server, until key in bye
public class TcpCharClientContact {
        public static void main(String[] args) {
            Socket socket = null;
            BufferedReader socketReader = null;
            BufferedWriter socketWriter = null;
            try {
                // Create a socket that will connect to localhost at port 12900.
                // Note that the server
                // must also be running at localhost and 12900.
                socket = new Socket("localhost", 12900);

                System.out.println("Started client socket at "
                        + socket.getLocalSocketAddress());

                // Create a buffered reader and writer using the socket's
                // input and output streams
                socketReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                socketWriter = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));

                // Create a buffered reader for user's input
                BufferedReader consoleReader
                        = new BufferedReader(new InputStreamReader(System.in));

                String promptMsg = "Please enter a message action(Bye to quit):";
                String outMsg = null;

                System.out.print(promptMsg);
                while ((outMsg = consoleReader.readLine()) != null) {
                    if (outMsg.equalsIgnoreCase("bye")) {
                        break;
                    }
                    // Add a new line to the message to the server,
                    // because the server reads one line at a time.
                    JSONObject json = new JSONObject();
                    json.put("Action",outMsg);
                    json.put("Name","name01");
                    json.put("MobileNo","138001");
                    json.put("Email", "abc@abc.com");
                    json.put("Address", "address@city");
                    outMsg = json.toString();
                    socketWriter.write(outMsg);
                    socketWriter.write("\n");
                    socketWriter.flush();

                    // Read and display the message from the server
                    String inMsg = socketReader.readLine();
                    System.out.println("from Server: " + inMsg);

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
