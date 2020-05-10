package pattern;

/**
 *
 * @author Nicolás
 */
public class Client
{

    public static void main(String[] args)
    {
        Invoker invoker = new Invoker(new ConcreteCommand(new Receiver()));
        invoker.doSomething();
        invoker.undoSomething();
    }

}
