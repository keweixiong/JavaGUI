// Demonstrate HttpURLConnection.

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
//HTTP请求主要为 GET请求 和 POST请求 两种，
// GET请求的参数会通过以跟随在URL后边以键值对的方式进行传递（例：key1=a&key2=b&key3...)；
// 而POST请求的参数会通过HEADER进行传递? body ?

public class HttpClientGet {
    public static void main(String args[]) throws Exception {

        BufferedReader bufferedReader = null;
              HttpURLConnection hc =null;
        URL hp = new URL("https://www.demo2s.com");
        URL hp2 = new URL( "https://postman-echo.com/time/now");
        URL hp3 = new URL( "https://postman-echo.com/status/402");
        URL hp4 = new URL("http://localhost:9000/echoget"+"?a=1&b=2&c=3");
        URL hp5 = new URL("http://httpbin.org/get?id=1234");

        try  {
        hc = (HttpURLConnection) hp2.openConnection();
        hc.setDoOutput(true);
        hc.setDoInput(true);


        hc.setRequestProperty("id", "1234"); // it will occur in RequestHeader in Server side
        System.out.println("Request Properties/header is " + hc.getRequestProperties());

        // Display request method.
        hc.setRequestMethod("GET");
        System.out.println("Request method is " + hc.getRequestMethod());

        // Request Body
        String strReqBody ="id=1234&name=" +
                URLEncoder.encode("from demo2s.com @#$%^&&* ", "utf-8");

        // don't wirte uncessory data into Req body, it will cause 404 or 403 ... error

//      System.out.println("Request Body is " + strReqBody);
//      OutputStream os = hc.getOutputStream();
//      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//      bw.write(strReqBody);
//      bw.flush();
//      bw.close();

        hc.connect();      // this is unnecessorily, coz invoked autoly when needed.

        //   RESP
        // here auto  connect()
        // why not need to hpCon.connect(); coz it will invoke connect() automatically when calling
        // getHeaderFields/...();
        // Display response code.
        System.out.println("\nResponse code is " + hc.getResponseCode());
        System.out.println("Response HeaderFields .getHeadersFields: " + hc.getHeaderFields());
//        // Display response message.
        System.out.println("    Response Message is " + hc.getResponseMessage() + "(200->OK, 404->not found)");
//
//        // Get a list of the header fields and a set
//        // of the header keys.
        Map<String, List<String>> hdrMap = hc.getHeaderFields();
        Set<String> hdrField = hdrMap.keySet();

        System.out.println("\nResponse header fields parsed:");

       // Display all header keys and values..
       for (String k : hdrField) {
           System.out.println("Key: " + k + "  Value: " + hdrMap.get(k));
       }

//        if (hc.getResponseCode() == HttpURLConnection.HTTP_OK || true) {



            System.out.println("to read the Resp body...");

 //         not sure why we need to openconnection again() ???, otherwise exception
 //                hc = (HttpURLConnection) hp5.openConnection();
 //                InputStream ins = hc.getInputStream();
 //                bufferedReader = new BufferedReader(new InputStreamReader(ins, StandardCharsets.UTF_8));

                bufferedReader = new BufferedReader(new InputStreamReader(hp2.openStream()));


            // 存放数据
                StringBuilder sb = new StringBuilder();
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    sb.append(temp);
                    sb.append(System.lineSeparator());  // 这里需要追加换行符，默认读取的流没有换行符，需要加上才能符合预期
                }
                String strRespBody = sb.toString();
                System.out.println("\nRespBody: " + strRespBody);
            }  catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


        if (hc != null) {
            hc.disconnect();
        }

    }
}
