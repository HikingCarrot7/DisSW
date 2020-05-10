package ejemplo;

import pattern.Command;

/**
 *
 * @author Nicolás
 */
public class ControlRemoto
{

    private Command command;

    public ControlRemoto(Command command)
    {
        this.command = command;
    }

    public void press()
    {
        command.execute();
    }

    public void pressUndo()
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
