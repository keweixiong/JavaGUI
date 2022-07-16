import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpByteServer {

    public static void main(String[] args) {
        int iPort =  12901;
     try {
        // Create a Server socket
        ServerSocket serverSocket
                = new ServerSocket(iPort, 100,
                InetAddress.getByName("localhost"));

        System.out.println("Server byte Read/Write started at: " + serverSocket);

        // Keep accepting client connections in an infinite loop
        while (true) {
            System.out.println("Waiting for a connection...");

            // Accept a connection
            final Socket activeSocket = serverSocket.accept();

            System.out.println("Received a connection from " +
                    activeSocket);

            // Create a new thread to handle the new connection
            Runnable runnable = () -> handleClientRequest(activeSocket);

            new Thread(runnable).start(); // start a new thread
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public static void handleClientRequest(Socket socket) {
        InputStream is  = null;
        OutputStream os = null;

        try {
        // Create a buffered reader and writer for the socket
        is =  socket.getInputStream();
        os = socket.getOutputStream();
        int iLen = 0 ;
        byte[] bytes = new byte[2048];
        while (( iLen = is.read(bytes)) != 0) {
            System.out.println(iLen+" Bytes Received from client: " + new String(bytes,0,iLen));

            // Echo the received message to the client

            //os.write(bytes);
            os.write(bytes,0, iLen);
            os.flush();
        }
        } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}
