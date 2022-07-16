import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class HttpClientPost {
    public static String getURLContent(String urlStr,
                                       String input) throws Exception {
        BufferedReader br = null;//  w   w   w .  d  e   m  o 2  s   . c o  m
        BufferedWriter bw = null;

        URL url = new URL(urlStr);

        //URLConnection connection = url.openConnection();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Must call setDoOutput(true) to indicate that you will write
        // to the connection. By default, it is false.
        // By default, setDoInput() is set to true.
        connection.setDoOutput(true);

        // print the request head
        connection.setRequestProperty("key1", "value1"); // it will occur in RequestHeader in Server side
        System.out.println( "\nRequest Properties/header: "+connection.getRequestProperties());
        connection.setRequestMethod("POST");
        System.out.println("Request method is " + connection.getRequestMethod());

        System.out.println("Request Body is: " + input);

        // Now, connect to the remote object, unnecessorily
        connection.connect();

        // Write data to the URL first before reading the response
        OutputStream ous = connection.getOutputStream();
        bw = new BufferedWriter(new OutputStreamWriter(ous));
        bw.write(input);
        bw.flush();
        bw.close();

        // Must be placed after writing the data. Otherwise, it will
        // result in error, because if write is performed, read
        // must be performed after the write.

        System.out.println("\nResponse code is " + connection.getResponseCode());

        if ( connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            printResponseHeaders(connection);


            InputStream ins = connection.getInputStream();

            // Wrap the input stream into a reader
            br = new BufferedReader(new InputStreamReader(ins));

            StringBuilder sb = new StringBuilder();
            String msg = null;
            while ((msg = br.readLine()) != null) {
                sb.append(msg);
                sb.append("\n"); // Append a new line
            }
            br.close();

            connection.disconnect();

            System.out.println("Resp Body is: " + sb.toString());
            return sb.toString();
        }
        return "response err";

    }

    public static void printResponseHeaders(URLConnection connection) {
        Map headers = connection.getHeaderFields();
        System.out.println("Response Headers are:");
        System.out.println(headers);
        System.out.println();


    }

    public static void main(String[] args) throws Exception {
        String urlStr = "http://www.httpbin.org/post";
      // String urlStr = "http://localhost:9000/echopost";

        String query = null;
        // Encode the query. We need to encode only the value
        // of the name parameter. Other names and values are fine
        query = "id=1234&name=" +
                URLEncoder.encode("from demo2s.com @#$%^&&* ", "utf-8");

        // Get the content and display it on the console
        String content = getURLContent(urlStr, query);

        System.out.println("Returned data from the server is:");
        System.out.println(content);

    }
}