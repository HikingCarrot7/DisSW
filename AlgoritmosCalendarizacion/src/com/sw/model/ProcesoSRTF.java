package com.sw.model;

/**
 *
 * @author HikingC7
 */
public class ProcesoSRTF extends Proceso
{

    public ProcesoSRTF(Estado estadoProceso, String identificador, long tiempoLlegada, int numProceso, long tiempoRafaga)
    {
        super(estadoProceso, identificador, tiempoLlegada, numProceso, tiempoRafaga);
    }

    @Override
    public Proceso obtenerCopiaProceso()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
