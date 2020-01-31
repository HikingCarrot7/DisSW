package modelo;

/**
 *
 * @author HikingCarrot7
 */
public class ProcesoSRTF extends Proceso
{

    public ProcesoSRTF(Estado estadoProceso, String identificador, int numProceso, long tiempoRafaga, long tiempoLlegada)
    {
        super(estadoProceso, identificador, tiempoLlegada, numProceso, tiempoRafaga);
    }

}
