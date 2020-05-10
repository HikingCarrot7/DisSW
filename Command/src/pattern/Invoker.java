package pattern;

/**
 *
 * @author Nicolás
 */
public class Invoker
{

    private Command command;

    public Invoker(Command command)
    {
        this.command = command;
    }

    public void doSomething()
    {
        command.execute();
    }

    public void undoSomething()
    {
        command.undo();
    }

    public Command getCommand()
    {
        return command;
    }

    public void setCommand(Command command)
    {
        this.command = command;
    }

}
