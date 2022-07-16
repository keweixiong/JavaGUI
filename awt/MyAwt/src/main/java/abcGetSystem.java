import java.net.URL;

public class abcGetSystem {
    /**
     * get system information
     * @param args
     */
    public static void main(String[] args) {
        // get system path
        System.out.println("---------------------PATH:");
        System.out.println(abcJava.class.getResource("").getPath());//        获取项目路径
        System.out.println(abcJava.class.getResource("/").getPath());//       获取根路径
        System.out.println("user.dir:"+System.getProperty("user.dir"));//获取当前工程路径
        System.out.println("java.class.path:" + System.getProperty("java.class.path"));//获取所有的类路径 包括jar包的路径

        System.out.println("\ngetFileName()="+Thread.currentThread().getStackTrace()[1].getFileName()) ;
        System.out.println("\ngetLineNumber()=" + Thread.currentThread().getStackTrace()[1].getLineNumber()) ;


        URL t1 = Thread.currentThread().getContextClassLoader().getResource("templates/page1.html");
        System.out.println("Thread getResource:"  +t1.toString());

        System.out.println("class loader:"+abcJava.class.getClassLoader());
        URL url1 = abcJava.class.getResource("templates/page1.html");
        System.out.println("get resource:"+url1.toString());

        IOMyLog.mylog("consolefile", "Received from client");

    }
}
