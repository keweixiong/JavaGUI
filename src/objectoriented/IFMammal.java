package objectoriented;


public class IFMammal implements IFAnimal{
 
   public void eat(){
      System.out.println("Mammal eats");
   }
 
   public void travel(){
      System.out.println("Mammal travels");
   } 
 
   public int noOfLegs(){
      return 0;
   }
 
   public static void main(String args[]){
      IFMammal m = new IFMammal();
      m.eat();
      m.travel();
   }
}