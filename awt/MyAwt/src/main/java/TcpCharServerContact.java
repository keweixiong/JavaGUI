import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpCharServerContact {

public static void main(String[] args) {
    try {
        // Create a Server socket
        ServerSocket serverSocket
                = new ServerSocket(12900, 100,
                InetAddress.getByName("localhost"));
        System.out.println("Server started at: " + serverSocket);

        // Keep accepting client connections in an infinite loop
        while (true) {
            System.out.println("Waiting for a new connection...");

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
    BufferedReader socketReader = null;
    BufferedWriter socketWriter = null;

    try {
        // Create a buffered reader and writer for the socket
        socketReader
                = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        socketWriter
                = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));
        String inMsg = null;
        while ((inMsg = socketReader.readLine()) != null) {
            System.out.println("Received from client: " + inMsg);
            // Echo the received message to the client
            String outMsg = inMsg;

            JSONObject json = new JSONObject( );
            json = JSON.parseObject(inMsg);
            String strAction = (String) json.get("Action")==null?"":(String) json.get("Action");
            String strName = (String) json.get("Name")==null?"":(String) json.get("Name");
            String strMobileNo = (String) json.get("MobileNo")==null?"":(String) json.get("MobileNo");
            String strEmail = (String) json.get("Email")==null?"":(String) json.get("Email");
            String strAddress = (String) json.get("Address")==null?"": (String) json.get("Address");

            claContact contact = new claContact(strName,strMobileNo,strEmail,strAddress);

            JSONObject jsonOut= new JSONObject();
            jsonOut.put("ReturnCode","TIME");
            jsonOut.put("ReturnMesg","Time out");

            JSONObject jsonOut2 = new JSONObject();
            // outMsg =  jsonOutR.toString();
            String strFindRs = new String() ;
            // for find(), return the info we get from the record
            // for inert()/update/delete， just return ok or not, no more messages.
            if (strAction.equalsIgnoreCase("I")) {
                System.out.println("to Action I ...");
                if (contact.dbinsert() == true ) {
                    JSONPath.set(jsonOut, "ReturnCode", "0000");
                    JSONPath.set(jsonOut, "ReturnMesg", "insert ok");
                    outMsg = jsonOut.toString();
                }
                else {
                    JSONPath.set(jsonOut, "ReturnCode", "I001");
                    JSONPath.set(jsonOut, "ReturnMesg", "insert fail");
                }

            }else if ( strAction.equalsIgnoreCase("F")) {
                System.out.println("to Action F ...");
                strFindRs =  contact.dbfind();
                System.out.println("return from dbfind():" +  strFindRs.length()+ " " +  strFindRs );
/*                if (  strFindRs.length() != 0 ) {
                    // outMsg ="find ok";
                    JSONPath.set(jsonOut,"ReturnCode", "0000") ;
                    JSONPath.set(jsonOut,"ReturnMesg", "find ok") ;
                    jsonOut2 = JSON.parseObject(strFindRs);
                    jsonOut.putAll(jsonOut2);
                  //  JSON.p
                    outMsg = jsonOut.toString() ;
                }
                else {
                    JSONPath.set(jsonOut,"ReturnCode", "F001") ;
                    JSONPath.set(jsonOut,"ReturnMesg", "not found") ;
                }   */
            }else if ( strAction.equalsIgnoreCase("U")) {
                System.out.println("to Action U ...");
                if ( contact.update() == true ) {
                    JSONPath.set(jsonOut, "ReturnCode", "0000");
                    JSONPath.set(jsonOut, "ReturnMesg", "update ok");
                    outMsg = jsonOut.toString();
                } else {
                    JSONPath.set(jsonOut, "ReturnCode", "U000");
                    JSONPath.set(jsonOut, "ReturnMesg", "update err");
                }
            }else if ( strAction.equalsIgnoreCase("D")) {
                System.out.println("to Action D ...");
                if ( contact.delete() == true ) {
                    JSONPath.set(jsonOut, "ReturnCode", "0000");
                    JSONPath.set(jsonOut, "ReturnMesg", "delete ok");

                    outMsg = jsonOut.toString();
                } else {
                    JSONPath.set(jsonOut, "ReturnCode", "D000");
                    JSONPath.set(jsonOut, "ReturnMesg", "delete err");
                }
             }else {
                System.out.println("to Action not supported");
                JSONPath.set(jsonOut, "ReturnCode", "F001");
                JSONPath.set(jsonOut, "ReturnMesg", "action not support ");
                outMsg = jsonOut.toString();
            }

            outMsg = jsonOut.toString();
             if ( strAction.equalsIgnoreCase("F"))
                 outMsg = strFindRs;
            IOMyLog.mylog("",outMsg);
            socketWriter.write(outMsg);
            socketWriter.write("\n");
            socketWriter.flush();
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
