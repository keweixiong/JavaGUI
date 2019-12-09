// show what is Polymorphism, basic idea
// father can be animal, and he can also be Cat or Cow 

public class Polymorphism {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" here is Polymorphism");
	      Animal obj = new Animal();
	        obj.cry();
	        obj = new Cat();  // Now I become the Cat
	        obj.cry();
	        obj = new Cow();  // Now i becomre the Cow
	        obj.cry();
	}

}



class Animal{
    // 动物的叫声
    public void cry(){
        System.out.println("不知道怎么叫");
    }
   
}
class Cat extends Animal{
    // 猫的叫声
    public void cry(){
        System.out.println("喵喵~");
    }
}
class Cow extends Animal{
    // 狗的叫声
    public void cry(){
        System.out.println("汪汪~");
    }
}