package DesignPattern.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2022/10/14
 * 
 * 设计模式七大原则之：迪米特法则【正例】
 */
public class Demeter2 {
    //客户端（使用者）
    public static void main(String[] args) {
        //先创建了学校的管理类
        SchoolManager2 schoolManager = new SchoolManager2();
        //输出了学院的员工id 和 学校总部的员工id
        schoolManager.printAllEmployee(new CollegeManager2());
    }
}

//学校总部的员工类
class Employee2{
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

//学院的员工类
class CollegeEmployee2{
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

//学院员工的管理类
class CollegeManager2{
    //返回学院的所有员工
    public List<CollegeEmployee2> getAllEmployee(){
        List<CollegeEmployee2> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            CollegeEmployee2 emp = new CollegeEmployee2();
            emp.setId("学院的员工，id = " + i);
            list.add(emp);
        }
        return list;
    }
    
    //输出学院所有员工的信息
    public void printEmployee(){
        List<CollegeEmployee2> list1 = getAllEmployee();
        System.out.println("--------------学院员工---------------");
        for (CollegeEmployee2 e : list1) {
            System.out.println(e.getId());
        }
    } 
}

//学校员工的管理类
//分析 SchoolManager1 类的直接朋友类有哪些：Employee1、CollegeManager1
//而 CollegeEmployee1 不是直接朋友类，它是一个陌生类，这样就违背了迪米特法则（陌生类最好不要以局部变量的形式出现在类的内部）
class SchoolManager2{
    //返回学校总部的所有员工
    public List<Employee2> getAllEmployee(){
        List<Employee2> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Employee2 emp = new Employee2();
            emp.setId("学院的员工，id = " + i);
            list.add(emp);
        }
        return list;
    }
    
    //该方法完成输出 学校总部 和 学院 的所有员工信息（仅id）
    void printAllEmployee(CollegeManager2 sum){
        //改进 :  将输出学院员工的代码，封装到 CollegeManager2 中，将逻辑封装到类内部，不要在外部有依赖
        //List<CollegeEmployee2> list1 = sum.getAllEmployee();
        //System.out.println("--------------学院员工---------------");
        //for (CollegeEmployee2 e : list1) {
        //    System.out.println(e.getId());
        //}
        sum.printEmployee();
        
        List<Employee2> list2 = this.getAllEmployee();
        System.out.println("--------------学校总部员工---------------");
        for (Employee2 e : list2) {
            System.out.println(e.getId());
        }
    }
}
