package modelo;

/**
 *
 * @author HikingC7
 */
public class Proceso
{

    private String identificador;
    private long tiempoLlegada;
    private long tiempoRafaga; // ticks en milisegundos.

    public Proceso(String identificador, long tiempoRafaga, long tiempoLlegada)
    {
        this.identificador = identificador;
        this.tiempoRafaga = tiempoRafaga;
        this.tiempoLlegada = tiempoLlegada;
    }

    public String getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(String identificador)
    {
        this.identificador = identificador;
    }

    public long getTiempoRafaga()
    {
        return tiempoRafaga;
    }

    public void setTiempoRafaga(long tiempoRafaga)
    {
        this.tiempoRafaga = tiempoRafaga;
    }

    public long getTiempoLlegada()
    {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(long tiempoLlegada)
    {
        this.tiempoLlegada = tiempoLlegada;
    }

}
