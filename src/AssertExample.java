/* Eclipse����ο�������(Assert),�����ж�:
1.Run -> Run Configurations -> Argumentsҳǩ -> VM arguments�ı����м��϶��Կ����ı�־:-enableassertions ����-ea �Ϳ�����
2.��myEclipse��,Windows -> Preferences ->Java ->Installed JREs ->�����ʹ�õ�JDK ->Edit ->Default VM Arguments�ı���������:-ea
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