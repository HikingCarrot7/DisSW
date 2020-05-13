package game;

/**
 *
 * @author Nicolás
 */
public class CommandInclinarse implements Command
{

    private Personaje personaje;

    public CommandInclinarse(Personaje personaje)
    {
        this.personaje = personaje;
    }

    @Override
    public void execute()
    {
        personaje.inclinarse();
    }

    public Personaje getPersonaje()
    {
        return personaje;
    }

    public void setPersonaje(Personaje personaje)
    {
        this.personaje = personaje;
    }
}
