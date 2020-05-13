package game;

/**
 *
 * @author Nicolás
 */
public class CommandCorrer implements Command
{

    private Personaje personaje;

    public CommandCorrer(Personaje personaje)
    {
        this.personaje = personaje;
    }

    @Override
    public void execute()
    {
        personaje.correr();
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
