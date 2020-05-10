package pattern;

/**
 *
 * @author Nicolás
 */
public class ConcreteCommand implements Command
{

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        receiver.doAction();
    }

    @Override
    public void undo()
    {
        receiver.undoAction();
    }

    public Receiver getReceiver()
    {
        return receiver;
    }

    public void setReceiver(Receiver receiver)
    {
        this.receiver = receiver;
    }

}
