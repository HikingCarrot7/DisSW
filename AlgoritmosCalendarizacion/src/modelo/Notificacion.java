package modelo;

/**
 *
 * @author HikingCarrot7
 */
public class Notificacion
{

    private Identificador identificador;
    private Proceso proceso;
    private long tiempoUsoCpu;

    public Notificacion(Identificador identificador, Proceso proceso, long tiempoUsoCpu)
    {
        this.identificador = identificador;
        this.proceso = proceso;
        this.tiempoUsoCpu = tiempoUsoCpu;
    }

    public long getTiempoUsoCpu()
    {
        return tiempoUsoCpu;
    }

    public void setTiempoUsoCpu(long tiempoUsoCpu)
    {
        this.tiempoUsoCpu = tiempoUsoCpu;
    }

    public Proceso getProceso()
    {
        return proceso;
    }

    public void setProceso(Proceso proceso)
    {
        this.proceso = proceso;
    }

    public Identificador getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(Identificador identificador)
    {
        this.identificador = identificador;
    }

}
