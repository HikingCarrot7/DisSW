package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class ProcesoSJF extends Proceso
{

    public ProcesoSJF(Estado estadoProceso, String identificador, int numProceso, long tiempoRafaga, long tiempoLlegada)
    {
        super(estadoProceso, identificador, tiempoLlegada, numProceso, tiempoRafaga);
    }

    @Override
    public Proceso obtenerCopiaProceso()
    {
        return new ProcesoSJF(
                PCB.getEstadoProceso(),
                getIdentificador(),
                PCB.getNumProceso(),
                PCB.getTiempoRafaga(),
                getTiempoLlegada());
    }

}
