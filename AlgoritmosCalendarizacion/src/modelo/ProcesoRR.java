package modelo;

/**
 *
 * @author HikingCarrot7
 */
public class ProcesoRR extends Proceso
{

    public ProcesoRR(Estado estadoProceso, String identificador, int numProceso, long tiempoRafaga)
    {
        super(estadoProceso, identificador, 0, numProceso, tiempoRafaga);
    }

    @Override
    public Proceso obtenerCopiaProceso()
    {
        return new ProcesoRR(
                PCB.getEstadoProceso(),
                getIdentificador(),
                PCB.getNumProceso(),
                PCB.getTiempoRafaga());
    }

}
