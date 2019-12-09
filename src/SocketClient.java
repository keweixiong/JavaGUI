

import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String args[]){
        String s = null;
        Socket mySocket;
        
        DataInputStream in = null;
        DataOutputStream out = null;
        
        try{
            mySocket = new Socket("localhost",4441);
            System.out.println("I have connected to server");
            in = new DataInputStream(mySocket.getInputStream());
            out = new DataOutputStream(mySocket.getOutputStream());
            
            out.writeUTF("Hi I am client!");
            
            while(true){
            	System.out.println("I am reading from the server");
                s = in.readUTF();
                
                System.out.println("after readiing from the server");
                if(s==null) {
                	System.out.println("s is null");
                	break;
                }
                else 
                	System.out.printf("Message from Server: %s", s);
            }
            
            System.out.printf("\n I will close socket and 88");
            mySocket.close();
        }catch(IOException e){
            System.out.println("error can¡¯t connect");
        }
    }
}
