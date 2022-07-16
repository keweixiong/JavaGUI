import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public class HttpServerExample {
    static int port = 9000;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress( port), 0);
        System.out.println("server started at " + port);
        server.createContext("/", new RootHandler());  // if nothing matched, it will go here
        server.createContext("/echoheader", new EchoHeaderHandler());
        server.createContext("/echoget", new EchoGetHandler());
        server.createContext("/echopost", new EchoPostHandler());
        server.setExecutor(null);
        server.start();
        /*
void bind(InetSocketAddress addr, int backlog) // 重新绑定地址和端口
InetSocketAddress getAddress() // 获取当前绑定的地址

// 创建监听的上下文, 请求 URI 根路径的匹配, 根据不同的 URI 根路径选择不同的 HttpHandler 处理请求,
// 路径必须以 "/" 开头。路径 "/" 表示匹配所有的请求 URI（没有其他更具体的匹配路径除外）。
        HttpContext createContext(String path)
        HttpContext createContext(String path, HttpHandler handler)
// 移除上下文监听
        void removeContext(HttpContext context)
        void removeContext(String path)
// 设置请求的线程执行器, 设置为 null 表示使用默认的执行器
        void setExecutor(Executor executor)
        Executor getExecutor()
// 启动服务
        void start()
// 最长等待指定时间后停止服务
        void stop(int delay)
*/
    }

    // let it work with multi threads. not carefully verify
    public static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            new Thread(new Runnable() {           // multi thread
                @Override
                public void run() {
                    System.out.println("enter Root Handler");
                    String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " +  port + "</h1>";
                    response += "if not matched, all will go here, pay attention to letter upper/lower case";

                    try {
                        he.sendResponseHeaders(200, response.length());
                        OutputStream os = he.getResponseBody();
                        os.write(response.getBytes());

                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }).start();
        }
    }
    public static class EchoHeaderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("enter EchoHeader handler");

            Headers headers = he.getRequestHeaders();
            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
            String response = "Echo header";
            for (Map.Entry<String, List<String>> entry : entries)
                response += entry.toString() + "\n";

            he.sendResponseHeaders(200, response.length());

            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());

            os.close();
        }
/*
// 获取请求的 URI, 请求链接除去协议和域名端口后的部分, 如: http://www.abc.com/aa/bb, URI 为 /aa/bb
URI getRequestURI()

// 获取请求客户端的 IP 地址
InetSocketAddress getRemoteAddress()

// 获取请求协议, 例如: HTTP/1.1
String getProtocol()

// 获取请求的方法, "GET", "POST" 等
String getRequestMethod()
// 获取所有的请求头
Headers getRequestHeaders()
// 以输入流的方式获取请求内容
InputStream getRequestBody()

// 获取响应头的 Map, 要添加头, 获取到 headers 后调用 add(key, value) 方法添加
Headers getResponseHeaders()
// 发送响应头, 并指定 响应code 和 响应内容的长度
void sendResponseHeaders(int rCode, long responseLength)
// 获取响应内容的输出流, 响应内容写到该流
OutputStream getResponseBody()
// 关闭处理器, 同时将关闭请求和响应的输入输出流（如果还没关闭）
void close()

// 获取此请求对应的上下文对象
HttpContext getHttpContext)
// 获取收到请求的本地地址
InetSocketAddress getLocalAddress()
*/
    }

    public static class EchoGetHandler implements HttpHandler {

        @Override

        public void handle(HttpExchange he) throws IOException {
            System.out.println("\nenter EchoGet handler");
            System.out.println("\ngetRemoteAddress: "+he.getRemoteAddress() +
                    " getLocalAddress: " + he.getLocalAddress() +
                    " getHttpContext: " + he.getHttpContext()+
                    " getPrincipal: " + he.getPrincipal() ) ;
            System.out.println("getRequestMethod: "+he.getRequestMethod());
            System.out.println("getRequestURI: " + he.getRequestURI());
            // print the request head
            System.out.println( "\ngetRequestHeader: "+he.getRequestHeaders().entrySet() );

            // print the request body
            System.out.println("get requestBody(is a stream, can't be display): "+ he.getRequestBody().toString());

            // parse request from the tail of URL
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            System.out.println("request URI is " + requestedUri);
            String query = requestedUri.getRawQuery();
            System.out.println("request query from URI is " + query);

            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String strReqBody = br.readLine();

            System.out.println("request body parsed from stream is: " + strReqBody);

            parseQuery(query, parameters);

            // send response
            String response = "";
            for (String key : parameters.keySet())
                response += key + " = " + parameters.get(key) + "\n";

            System.out.println("\nresponse header is: 200 " + response.length());
            System.out.println("response body is: " + response);

            // send the response header
            he.sendResponseHeaders(200, response.length());

            // send the response body
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());

            os.close();
        }
    }

    public static class EchoPostHandler implements HttpHandler {

        @Override

        public void handle(HttpExchange he) throws IOException {
            System.out.println("\nenter EchoPost handler");

            System.out.println("\ngetRemoteAddress: "+he.getRemoteAddress() +
                    " getLocalAddress: " + he.getLocalAddress() +
                    " getHttpContext: " + he.getHttpContext()+
                    " getPrincipal: " + he.getPrincipal() ) ;

            System.out.println("getRequestMethod: "+he.getRequestMethod());
            System.out.println("getRequestURI: " + he.getRequestURI());
            // print the request head
            System.out.println( "\ngetRequestHeader: "+he.getRequestHeaders().entrySet() );


            // print the request body
            System.out.println("get requestBody(is a stream, can't be display): "+ he.getRequestBody().toString());

            // parse request， paras not get from URL, but from Request body.
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();

            System.out.println("request body parsed from stream is: " + query);

            parseQuery(query, parameters);

            // send response
            String response = "";
            for (String key : parameters.keySet())
                response += key + " = " + parameters.get(key) + "\n";

            System.out.println("\nresponse header is: 200 " + response.length());
            System.out.println("response body is: " + response);

            // send response Header
            he.sendResponseHeaders(200, response.length());

            // send response body
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }
    public static void parseQuery(String query, Map<String,
            Object> parameters) throws UnsupportedEncodingException {

                    if (query != null) {
                        String pairs[] = query.split("[&]");
                        for (String pair : pairs) {
                            String param[] = pair.split("[=]");
                            String key = null;
                            String value = null;
                            if (param.length > 0) {
                                key = URLDecoder.decode(param[0],
                                        System.getProperty("file.encoding"));
                            }

                            if (param.length > 1) {
                                value = URLDecoder.decode(param[1],
                                        System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }
}
