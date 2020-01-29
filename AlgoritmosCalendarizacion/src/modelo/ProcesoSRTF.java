package modelo;

/**
 *
 * @author HikingCarrot7
 */
public class ProcesoSRTF extends Proceso
{

    private long tiempoLlegada;

    public ProcesoSRTF(Estado estadoProceso, String identificador, int numProceso, long tiempoRafaga, long tiempoLlegada)
    {
        super(estadoProceso, identificador, numProceso, tiempoRafaga);
        this.tiempoLlegada = tiempoLlegada;
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
