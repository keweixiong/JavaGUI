
// to show inherited 

public class People {

    String name;
    int age;
    int height;
   
    void say(){
        System.out.println("I tell you:" + name + "��Age " + age + "��Height" + height);
    }
}


class Teacher extends People{
    String school;  // ����ѧУ
    String subject;  // ѧ��
    int seniority;  // ����
   
    // ���� People ���е� say() ����
    void say(){
        System.out.println("Teacher tell you" + name + "��at " + school + " on " + subject + "��with " + seniority + " years ");
    }
   
    void lecturing(){
        System.out.println("I am still teaching ");
    }
}