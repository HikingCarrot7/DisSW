package test;

import model.CantQuackBehavior;
import model.Duck;

/**
 *
 * @author Nicolás
 */
public class Test
{

    public static void main(String[] args)
    {
        Duck duck = new Duck(new CantQuackBehavior());
        duck.quack();
    }

}
