package DesignPattern.patterns.composite;

public class Client {
    public static void main(String[] args) {
        //从大到小的，构造测试数据
        
        //创建学校
        OrganizationComponent university = new University("清华大学", "中国顶级大学");
        //创建学院
        OrganizationComponent computerCollege = new College("计算机学院", "计算机");
        OrganizationComponent musicCollege = new College("音乐学院", "音乐");
        
        //创建各个学院下的系（专业）
        computerCollege.add(new Department("软件工程", "软件"));
        computerCollege.add(new Department("网络工程", "网络"));
        computerCollege.add(new Department("计算机科学与技术", "计算机科学"));

        musicCollege.add(new Department("古典音乐", "古典"));
        musicCollege.add(new Department("现代音乐", "现代"));
        musicCollege.add(new Department("爵士音乐", "爵士"));
        
        //将两个学院加入到学校
        university.add(computerCollege);
        university.add(musicCollege);

        //如果想输出整个学校的：
//        university.print();
        
        //如果只想输出计算机的，可以调计算机学院的 print
        computerCollege.print();
        
    }
}
