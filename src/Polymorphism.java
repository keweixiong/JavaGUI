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
    // ����Ľ���
    public void cry(){
        System.out.println("��֪����ô��");
    }
   
}
class Cat extends Animal{
    // è�Ľ���
    public void cry(){
        System.out.println("����~");
    }
}
class Cow extends Animal{
    // ���Ľ���
    public void cry(){
        System.out.println("����~");
    }
}