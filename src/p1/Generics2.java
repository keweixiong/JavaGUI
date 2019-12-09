package p1;

public class Generics2 {
    public static void main(String[] args){
        PointABC<Integer, Integer> p1 = new PointABC<Integer, Integer>();
        p1.setX(10);
        p1.setY(20);
        printNumPoint(p1);
      
        PointABC<String, String> p2 = new PointABC<String, String>();
        p2.setX("东京180度");
        p2.setY("北纬210度");
        printStrPoint(p2);
    }
   
    // 借助通配符限制泛型的范围
    public static void printNumPoint(PointABC<? extends Number, ? extends Number> p){
        System.out.println("x: " + p.getX() + ", y: " + p.getY());
    }
   
    public static void printStrPoint(PointABC<? extends String, ? extends String> p){
        System.out.println("GPS: " + p.getX() + "，" + p.getY());
    }
}

class PointABC<T1, T2>{
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
}