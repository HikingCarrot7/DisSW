package ejemplo;

/**
 *
 * @author Nicolás
 */
public class Memento
{

    private final String state;

    public Memento(String state)
    {
        this.state = state;
    }

    public String getSavedState()
    {
        return state;
    }

}
