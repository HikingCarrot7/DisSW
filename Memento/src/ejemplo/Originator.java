package ejemplo;

import pattern.*;

/**
 *
 * @author Nicolás
 */
public class Originator
{

    private String actualState;

    public void setActualState(String actualState)
    {
        this.actualState = actualState;
    }

    public Memento storeInMemento()
    {
        return new Memento(actualState);
    }

    public String restoreFromMemento(Memento memento)
    {
        actualState = memento.getSavedState();
        return actualState;
    }

}
