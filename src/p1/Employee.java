// no main() in this file, the class Employee will be used by p2.EmployeeTest.java

package p1;

public class Employee{
    String name;
    int age;
    String designation;
    double salary;
    
    // Employee 类的构造方法
    public Employee(String name){
        this.name = name;
    }
    
    // 设置age的值
    public void empAge(int empAge){
        age =  empAge;
    }
    // 设置designation的值
    public void empDesignation(String empDesig){
        designation = empDesig;
    }
    // 设置salary的值
    public void empSalary(double empSalary){
        salary = empSalary;
    }
    // 输出信息
    public void printEmployee(){
        System.out.println("Name:"+ name );
        System.out.println("Age:" + age );
        System.out.println("Designation:" + designation );
        System.out.println("Salary:" + salary);
    }
}
