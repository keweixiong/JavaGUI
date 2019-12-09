public class Interface{
    public void test1(INTA INTA) {
        INTA.doSth();
    }
    public static void main(String[] args) {
        Interface d = new Interface();
        INTA INTA = new CLASSB();
        d.test1(INTA);
    }
}

interface INTA {
    public int doSth();
}
class CLASSB implements INTA {
    public int doSth() {
        System.out.println("dosth now in CLASSB");
        return 123;
    }
}