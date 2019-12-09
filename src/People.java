
// to show inherited 

public class People {

    String name;
    int age;
    int height;
   
    void say(){
        System.out.println("I tell you:" + name + "，Age " + age + "，Height" + height);
    }
}


class Teacher extends People{
    String school;  // 所在学校
    String subject;  // 学科
    int seniority;  // 教龄
   
    // 覆盖 People 类中的 say() 方法
    void say(){
        System.out.println("Teacher tell you" + name + "，at " + school + " on " + subject + "，with " + seniority + " years ");
    }
   
    void lecturing(){
        System.out.println("I am still teaching ");
    }
}