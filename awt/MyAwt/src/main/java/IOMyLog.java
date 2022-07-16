import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this is a static method created to log :    IOMyLog.mylog(" ");
 */
public class IOMyLog {
    static void mylog(String fileOrConsole,String strInfo)
    {
        String strFileName   = Thread.currentThread().getStackTrace()[2].getFileName();
        String strClassName  = Thread.currentThread().getStackTrace()[2].getClassName();
        String strMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int iLineNumber    = Thread.currentThread().getStackTrace()[2].getLineNumber();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
        System.out.printf("%s %-15s%-15s%-15s%-6d%-40s\n", simpleFormat.format(date),
                strFileName, strClassName, strMethodName, iLineNumber, strInfo);

        if(fileOrConsole.indexOf("console") != -1)
        {
        }
        if(fileOrConsole.indexOf("file") != -1)
        {
            FileWriter fileWriter;
            try
            {
                fileWriter = new FileWriter("log",true);
                fileWriter.write( simpleFormat.format(date).toString() +  " "+
                  strFileName +  " "+
                 strClassName + " " +
                  strMethodName +  " " +
                 iLineNumber + " " +
                 strInfo + "\n");
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if(fileOrConsole.indexOf("html") != -1)
        {

        }
        if(fileOrConsole.indexOf("database") != -1)
        {

        }
    }

    public static void main(String[] args) {
        mylog("consolefile","abc");
    }

}
