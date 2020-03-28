package model;

/**
 *
 * @author Nicolás
 */
public class Duck
{

    private IQuackBehavior quackBehavior;

    public Duck(IQuackBehavior quackBehavior)
    {
        this.quackBehavior = quackBehavior;
    }

    public void quack()
    {
        this.quackBehavior.quack();
    }

    public void setQuackBehavior(IQuackBehavior newQuackBehavior)
    {
        this.quackBehavior = newQuackBehavior;
    }

}
