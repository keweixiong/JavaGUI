/* Eclipse中如何开启断言(Assert),方法有二:
1.Run -> Run Configurations -> Arguments页签 -> VM arguments文本框中加上断言开启的标志:-enableassertions 或者-ea 就可以了
2.在myEclipse中,Windows -> Preferences ->Java ->Installed JREs ->点击正使用的JDK ->Edit ->Default VM Arguments文本框中输入:-ea
package p1;


*/



public class AssertExample {
    public static void main(String[] args) {
        int x = 100;
        if (args.length > 0) {
            try {
                x = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                /* Ignore */
            }
        }
        
        
        System.out.println("Testing assertion... ");
        x = 19;
        assert x == 10 : "Our assertion failed";
        System.out.println("Test passed");
    }
}