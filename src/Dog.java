// all the content in this file are in default package



//the class without private/public/protected 
class  Catxxx {
	int i ;
}

public class Dog {
	protected String name;
    int age;
 
    // ���췽����û�з���ֵ
    Dog(String name1, int age1){
        name = name1;
        age = age1;
        System.out.println("Building the Dog:"+ name);
    }  
    void bark(){  // ������
        System.out.println("I am barking");
    }

    void bark(int i){ // overload bark()
    	System.out.printf("barking int %d   \n", i);
    }
    
    void hungry(){  // ����
        System.out.println("I am hungry");
    }
    

}

class MyDog extends Dog {
    // ���췽�����ܱ��̳У�ͨ��super()����
	MyDog(String name1, int age1) {
		super(name1, age1);
		// TODO Auto-generated constructor stub
	}
	
	// override 
    void bark(){  
        System.out.println("I am son barking, I have replaced the barking of my parent");
    }
}


