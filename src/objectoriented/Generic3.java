//  use ? ? in Generic 
package objectoriented;

public class Generic3 {
    public static void main(String[] args){
        PointBCD<Integer, Integer> p1 = new PointBCD<Integer, Integer>();  //!!!!
        p1.setX(10);
        p1.setY(20);
        printPoint(p1);
      
        PointBCD<String, String> p2 = new PointBCD<String, String>();   // !!!
        p2.setX("东京180度");
        p2.setY("北纬210度");
        printPoint(p2);
    }
   
    public static void printPoint(PointBCD<?, ?> p){  // 使用通配符   !!!
        System.out.println("This PointBCD is: " + p.getX() + ", " + p.getY());
    }
}

class PointBCD<T1, T2>{
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