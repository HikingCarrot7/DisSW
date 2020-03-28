package model;

/**
 *
 * @author Nicolás
 */
public class SimpleQuackBehavior implements IQuackBehavior
{

    @Override
    public void quack()
    {
        System.out.println("Estoy haciendo el mítico quack");
    }

}
