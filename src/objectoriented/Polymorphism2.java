package objectoriented;
// Polymorphism 
//  and what is instanceof 


public class Polymorphism2 {

    public static void main(String[] args){
        // ������̬�����˿��Ը��ܶද��ιʳ
        Master ma = new Master();
        ma.feed(new Animal2(), new Food());
        ma.feed(new Cat2(), new Fish());
        ma.feed(new Dog2(), new Bone());
    }
}
//Master��
class Master{
 public void feed(Animal2 an, Food f){

//to show u what is instanceof 
/*
 	if (an instanceof Animal2) {
 		System.out.println(" I am instance of Animal2 ");
 	}
 	if (an instanceof Cat2) {
 		System.out.println(" I am instance of Cat2 ");
 	}	
 	if (an instanceof Dog2) {
     	System.out.println(" I am instance of Dog2 ");
    	}    	
*/	
     an.eat(f);   // here is quite critical 
 }
}


// Animal2�༰������
class Animal2{
    public void eat(Food f){
        System.out.println("����һ��С������ڳ�" + f.getFood());
    }
}
class Cat2 extends Animal2{
    public void eat(Food f){
        System.out.println("����һֻСè�䣬���ڳ�" + f.getFood());
    }
}
class Dog2 extends Animal2{
    public void eat(Food f){
        System.out.println("����һֻ���������ڳ�" + f.getFood());
    }
}
// Food��������
class Food{
    public String getFood(){
        return "����";
    }
}
class Fish extends Food{
    public String getFood(){
        return "��";
    }
}
class Bone extends Food{
    public String getFood(){
        return "��ͷ";
    }
}
