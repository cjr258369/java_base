package DesignPattern.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2022/10/14
 * 
 * 设计模式七大原则之：迪米特法则【反例】
 */
public class Demeter1 {
    //客户端（使用者）
    public static void main(String[] args) {
        //先创建了学校的管理类
        SchoolManager1 schoolManager1 = new SchoolManager1();
        //输出了学院的员工id 和 学校总部的员工id
        schoolManager1.printAllEmployee(new CollegeManager1());
    }
}

//学校总部的员工类
class Employee1{
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

//学院的员工类
class CollegeEmployee1{
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

//学院员工的管理类
class CollegeManager1{
    //返回学院的所有员工
    public List<CollegeEmployee1> getAllEmployee(){
        List<CollegeEmployee1> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            CollegeEmployee1 emp = new CollegeEmployee1();
            emp.setId("学院的员工，id = " + i);
            list.add(emp);
        }
        return list;
    }
}

//学校员工的管理类
//分析 SchoolManager1 类的直接朋友类有哪些：Employee1、CollegeManager1
//而 CollegeEmployee1 不是直接朋友类，它是一个陌生类，这样就违背了迪米特法则（陌生类最好不要以局部变量的形式出现在类的内部）
class SchoolManager1{
    //返回学校总部的所有员工
    public List<Employee1> getAllEmployee(){
        List<Employee1> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Employee1 emp = new Employee1();
            emp.setId("学院的员工，id = " + i);
            list.add(emp);
        }
        return list;
    }
    
    //该方法完成输出 学校总部 和 学院 的所有员工信息（仅id）
    void printAllEmployee(CollegeManager1 sum){
        //依据上面的分析，结论如下：
        //1. CollegeEmployee1 不是 SchoolManager1 的直接朋友
        //2. CollegeEmployee1 是以局部变量的方式出现在 SchoolManager1 内部
        //3. 因此违反了迪米特法则（陌生类最好不要以局部变量的形式出现在类的内部）
        List<CollegeEmployee1> list1 = sum.getAllEmployee();
        System.out.println("--------------学院员工---------------");
        for (CollegeEmployee1 e : list1) {
            System.out.println(e.getId());
        }
        
        List<Employee1> list2 = this.getAllEmployee();
        System.out.println("--------------学校总部员工---------------");
        for (Employee1 e : list2) {
            System.out.println(e.getId());
        }
    }
}
