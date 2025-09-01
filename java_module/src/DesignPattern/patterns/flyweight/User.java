package DesignPattern.patterns.flyweight;

//享元模式的外部状态，随着环境的变化而变化
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
