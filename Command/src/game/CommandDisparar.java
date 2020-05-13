package game;

/**
 *
 * @author Nicolás
 */
public class CommandDisparar implements Command
{

    private Personaje personaje;

    public CommandDisparar(Personaje personaje)
    {
        this.personaje = personaje;
    }

    @Override
    public void execute()
    {
        personaje.disparar();
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
