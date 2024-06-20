package DesignPattern.patterns.decorator;

public class Cofee extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
