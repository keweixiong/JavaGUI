package objectoriented;
// Polymorphism 
//  and what is instanceof 


public class Polymorphism2 {

    public static void main(String[] args){
        // 借助多态，主人可以给很多动物喂食
        Master ma = new Master();
        ma.feed(new Animal2(), new Food());
        ma.feed(new Cat2(), new Fish());
        ma.feed(new Dog2(), new Bone());
    }
}
//Master类
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


// Animal2类及其子类
class Animal2{
    public void eat(Food f){
        System.out.println("我是一个小动物，正在吃" + f.getFood());
    }
}
class Cat2 extends Animal2{
    public void eat(Food f){
        System.out.println("我是一只小猫咪，正在吃" + f.getFood());
    }
}
class Dog2 extends Animal2{
    public void eat(Food f){
        System.out.println("我是一只狗狗，正在吃" + f.getFood());
    }
}
// Food及其子类
class Food{
    public String getFood(){
        return "事物";
    }
}
class Fish extends Food{
    public String getFood(){
        return "鱼";
    }
}
class Bone extends Food{
    public String getFood(){
        return "骨头";
    }
}
