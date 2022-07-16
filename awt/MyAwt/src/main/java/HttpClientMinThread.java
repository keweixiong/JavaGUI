

// Demonstrate HttpURLConnection.

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientMinThread {


    static void claVisitServer(  ) throws IOException {
        URL hp = new URL("https://www.demo2s.com"); // should this be placed as public var? thread safe?
         hp = new URL("http://localhost:9000/"); // should this be placed as public var? thread safe?

        HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();

        // Display request method.
        //System.out.println("Request method is " + hpCon.getRequestMethod());

        // Display response code.
        System.out.println(   " Thread ID="+Thread.currentThread().getId() +  ", Response code is " + hpCon.getResponseCode());

        // Display response message.
        //System.out.println("Response Message is " + hpCon.getResponseMessage());

        // Get a list of the header fields and a set
        // of the header keys.
        Map<String, List<String>> hdrMap = hpCon.getHeaderFields();
        Set<String> hdrField = hdrMap.keySet();

        //System.out.println("\nHere is the Response header:");

        // Display all header keys and values..
        for (String k : hdrField) {
        //    System.out.println("Key: " + k + "  Value: " + hdrMap.get(k));
        }//  w   w   w .   de    m o  2   s . c    o  m
    }
    public static void main(String args[]) throws Exception {
        // Create a new thread to handle the new connection

        Runnable runnable = () -> {
            try {
                claVisitServer(   );
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        for (  int i  = 0; i  < 10 ; i ++) {
            new Thread(runnable).start(); // start a new thread
        }

    }


}