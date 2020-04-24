package dominio;

/**
 *
 * @author Nicolás
 */
public class Estado
{

    public static final boolean BLOQUEDA = false;
    public static final boolean DESBLOQUEDA = true;

    private boolean estadoActual;

    public Estado(boolean estadoActual)
    {
        this.estadoActual = estadoActual;
    }

    public boolean estaBloquedo()
    {
        return estadoActual == BLOQUEDA;
    }

    public boolean estaDesbloqueado()
    {
        return !estaBloquedo();
    }

    public void setEstadoActual(boolean estadoActual)
    {
        this.estadoActual = estadoActual;
    }

}
