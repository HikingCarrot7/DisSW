package dominio;

/**
 *
 * @author Nicolás
 */
public class Torniquete
{

    private final Pantalla pantalla;
    private final Teclado teclado;
    private Estado estado;

    public Torniquete(Estado estado)
    {
        this.estado = estado;
        this.pantalla = new Pantalla();
        this.teclado = new Teclado();
    }

    public boolean moneda()
    {
        if (estado.estaDesbloqueado())
        {
            pantalla.mostrarMensaje("Ya ha insertado una moneda!");
            return false;
        }

        estado.setEstadoActual(Estado.DESBLOQUEDA);
        pantalla.mostrarMensaje("La máquina está desbloqueda, puede pasar.");
        return true;
    }

    public boolean pasar()
    {
        if (estado.estaBloquedo())
        {
            pantalla.mostrarMensaje("No puede pasar, primero debe insertar una moneda.");
            return false;
        }

        estado.setEstadoActual(Estado.BLOQUEDA);
        pantalla.mostrarMensaje("Nos vemos.");
        return true;
    }

    public Estado getEstado()
    {
        return estado;
    }

    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }

    public Pantalla getPantalla()
    {
        return pantalla;
    }

    public Teclado getTeclado()
    {
        return teclado;
    }

    public boolean estaEncendido()
    {
        return true;
    }

}
