package model;

/**
 *
 * @author Nicolás
 */
public class CantQuackBehavior implements IQuackBehavior
{

    @Override
    public void quack()
    {
        System.out.println("NO puedo hacer el mítico quack");
    }

}
