import java.io.File;

public class DirectoryListRecursive {
    public static void main(String[] args) {
        File dir = new File("d:\\自媒体");  // Escape sequence needed for '\'
        listRecursive(dir);
    }

    public static void listRecursive(File dir) {
    //    if (dir.isDirectory()) {
            File[] items = dir.listFiles();
            for (File item : items) {
                System.out.println(item.getAbsoluteFile());
                if (item.isDirectory()) listRecursive(item);  // Recursive call
            }
    //    }
    }
}