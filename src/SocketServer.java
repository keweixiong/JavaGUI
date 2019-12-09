

import java.io.*;import java.net.*;
public class SocketServer{
    public static void main(String args[]){
        ServerSocket server = null;
        Socket you = null;String s = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        try{
            server = new ServerSocket(4441);
        }catch(IOException e1){
            System.out.println("ERROR:" +e1);
        }
        
        System.out.println(" I am listening ..");
        try{
            you = server.accept();
            
            System.out.println(" I accepted you");

            in = new DataInputStream(you.getInputStream());
            out = new DataOutputStream(you. getOutputStream());
            while(true){
                s = in.readUTF();
                
                System.out.printf(" I am reading and I got %s", s);

                if(s!=null) break;
            }
            
            System.out.printf("\nI have finished listening 88");

            out.writeUTF("客户，你好，我是服务器");
            out.close();
        }
        catch(IOException e){System.out.println("ERROR:"+e);}
    }
}
