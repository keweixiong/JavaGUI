package p2;

/*  this program just show how to import  another package

we need to access the class in P1, so we need to import p1.*, 
since in one .java file, u can only use the class defined in the same file

*/
import p1.*;

public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       // 创建两个对象
        Employee empOne = new Employee("James Smith");     /* Employee class is defined in p1*/
        Employee empTwo = new Employee("Mary Anne");
        
        // 调用这两个对象的成员方法
        empOne.empAge(26);
        empOne.empDesignation("Senior Software Engineer");
        empOne.empSalary(1000);
        empOne.printEmployee();
        
        System.out.println("");
        empTwo.empAge(21);
        empTwo.empDesignation("Software Engineer");
        empTwo.empSalary(500);
        empTwo.printEmployee();

	}

}
