public class abcGeneric {

    public static void main(String[] args){

        // 实例化泛型类  !!  here is Integer type
        Point<Integer, Integer> p1 = new Point<Integer, Integer>();

        p1.setX(10);
        p1.setY(20);
        System.out.println("Integer: " + p1);

        // here is Double  and String
        Point<Double, String> p2 = new Point<Double, String>();

        p2.setX(25.4);
        p2.setY("东京180度");
         System.out.println("Double & String: " + p2);

        Point<String, String> p3 = new Point<String, String>();
         p3.setX("stra");
        p3.setY("strb");
        System.out.println("String & String: " + p3);


        printNumPoint(p1);    // here p1 must be constructed from Integer,Integer
        printStrPoint(p3);    // here p3 must be constructed from String,String
        printPoint(p2);       //  whatever type is ok
    }


    // 借助通配符限制泛型的范围, here must be number,number
    public static void printNumPoint(Point<? extends Number, ? extends Number> p){
        System.out.println("printNumPoint() x: " + p.getX() + ", y: " + p.getY());
    }

    // must be String,String
    public static void printStrPoint(Point<? extends String, ? extends String> p){
        System.out.println("printStrPoint() GPS: " + p.getX() + "，" + p.getY());
    }

    // any type is ok
    public static void printPoint(Point<?, ?> p){  // 使用通配符   !!!
        System.out.println("printPoint()  This Point is: " + p.getX() + ", " + p.getY());
    }
}

// 定义泛型类  T1, T2 can be any type
class Point<T1, T2>{
    T1 x;
    T2 y;
    public T1 getX() {
        return x;
    }
    public void setX(T1 x) {
        this.x = x;
    }
    public T2 getY() {
        return y;
    }
    public void setY(T2 y) {
        this.y = y;
    }

    // 定义泛型方法
    public <T1, T2> void printPoint(T1 x, T2 y){
        T1 xx = x;
        T2 yy = y;
        System.out.println("This point is：" + xx + ", " + yy);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}