package objectoriented;

// all the content in this file are in default package

public class Demo1 {

	static int i;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HelloWorld !");
 
		i = 1 ;
		for (i=1 ; i<=10; i++)
		{
			System.out.print(i + " ");
		}
		System.out.println("");
		
		Dog ObjDog    = new Dog("Íú²Æ", 3 );
		ObjDog.bark();
		ObjDog.bark(1);
		ObjDog.bark( (int) 1.89);

		ObjDog.hungry();
		
		// name is protected, I can still use it because we are in the same package
		System.out.println("My dog's name:" + ObjDog.name);

		String dogname ;
		dogname = ObjDog.name;
		System.out.println("My dog's name:" + dogname);

		
		MyDog ObjMyDog = new MyDog("mydog", 5);
		ObjMyDog.bark();
		

		Integer i;
		i = new Integer(10);
		System.out.printf("\nInteger value is d=%d s = %s", i.intValue(), i.toString());
		
		String string="this is a string of String";
		System.out.printf("\nString value is: %s", string);
		string = "1234567890";
		System.out.printf("\nString value is: s=%s d=%d ", string, Integer.parseInt(string, 10));

		
		
        Teacher t = new Teacher();
        t.name = " Tutor ";
        t.age = 70;
        
        t.school = " Oxford University ";
        t.subject = "Java";
        t.seniority = 12;
        t.say();
        t.lecturing();

        
        System.out.println("\nend of my program");
	}

}

