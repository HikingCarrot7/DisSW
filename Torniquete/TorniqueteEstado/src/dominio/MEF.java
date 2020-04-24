package dominio;

/**
 *
 * @author Nicolás
 */
public class MEF
{

    private final Dispositivo dispositivo;
    private Estado estadoActual;

    public MEF(Dispositivo dispositivo)
    {
        this.dispositivo = dispositivo;
    }

    public void moneda()
    {
        estadoActual.moneda(this);
    }

    public void pasar()
    {
        estadoActual.pasar(this);
    }

    public void listo()
    {
        dispositivo.reiniciarAlarma();
        setEstadoActual(Estado.ESTADO_BLOQUEADO);
    }

    public void reset()
    {
        dispositivo.reiniciarAlarma();
    }

    public void setEstadoActual(Estado estadoActual)
    {
        this.estadoActual = estadoActual;
    }

    public Estado getEstado()
    {
        return estadoActual;
    }

    public Dispositivo getDispositivo()
    {
        return dispositivo;
    }

}
