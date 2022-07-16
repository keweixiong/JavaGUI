import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientGet2 {
        public static void main(String[] args) {
            String urlStr = "http://httpbin.org/get?id=1234";
            //String urlStr = "http://localhost:9000/echoget?id=1234";
            urlStr = "https://postman-echo.com/time/now";
            String content = getURLContent(urlStr);
            System.out.println(content);
        } /* w  w   w  .d  e    m  o 2  s   .  co    m*/

        public static String getURLContent(String urlStr) {
            BufferedReader br = null;
            try {
                URL url = new URL(urlStr);
HttpURLConnection hc = (HttpURLConnection) url.openConnection();
hc.setDoOutput(true);
hc.setDoInput(true);
                hc.setRequestProperty("id", "1234"); // it will occur in RequestHeader in Server side
                System.out.println("Request Properties/header is " + hc.getRequestProperties());

                // Display request method.
                hc.setRequestMethod("GET");
                System.out.println("Request method is " + hc.getRequestMethod());
                String strReqBody ="id=1234&name=" +
                        URLEncoder.encode("from demo2s.com @#$%^&&* ", "utf-8");

// don't wirte uncessory data into Req body, it will cause 404 or 403 ... error

//                System.out.println("Request Body is " + strReqBody);
//                OutputStream os = hc.getOutputStream();
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//                bw.write(strReqBody);
//                bw.flush();
//                bw.close();




                System.out.println("Response HeaderFields .getHeadersFields: " + hc.getHeaderFields());
                // Get a list of the header fields and a set
                // of the header keys.
                Map<String, List<String>> hdrMap = hc.getHeaderFields();
                Set<String> hdrField = hdrMap.keySet();

                System.out.println("\nResponse header fields parsed:");

                // Display all header keys and values..
                for (String k : hdrField) {
                    System.out.println("Key: " + k + "  Value: " + hdrMap.get(k));
                }


//-------------------
                System.out.println("\nResponse code is " + hc.getResponseCode());

                // Get the input stream wrapped into a BufferedReader
// way1 OK                br = new BufferedReader(new InputStreamReader(url.openStream()));
// way2
InputStream is = url.openConnection().getInputStream();
//is = hc.getInputStream();   // this can't work, ????
br = new BufferedReader( new InputStreamReader(is));


                StringBuilder sb = new StringBuilder();
                String msg = null;
                while ((msg = br.readLine()) != null) {
                    sb.append(msg);
                    sb.append("\n"); // Append a new line
                }

                return sb.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            // If we get here it means there was an error
            return null;
        }

    }