package game;

/**
 *
 * @author Nicolás
 */
public class CommandBrincar implements Command
{

    private Personaje personaje;

    public CommandBrincar(Personaje personaje)
    {
        this.personaje = personaje;
    }

    @Override
    public void execute()
    {
        personaje.brincar();
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
