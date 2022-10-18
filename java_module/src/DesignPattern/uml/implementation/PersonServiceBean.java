package DesignPattern.uml.implementation;

/**
 * @date 2022/10/18
 */
public class PersonServiceBean implements PersonService{
    @Override
    public void delete(Integer id) {
        System.out.println("delete..");
    }
}
