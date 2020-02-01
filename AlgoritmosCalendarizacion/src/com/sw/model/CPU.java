package com.sw.model;

/**
 *
 * @author HikingC7
 */
public class CPU implements Runnable
{

    private volatile boolean ocupado;
    private volatile boolean procesoInterrumpido;
    private Proceso procesoActual;
    private long tiempoUsoCPU;

    public void ejecutarProceso(Proceso proceso)
    {
        procesoActual = proceso;
        tiempoUsoCPU = proceso.PCB.getTiempoRafaga();
        iniciarEjecucion();
    }

    public void ejecutarProceso(Proceso proceso, long tiempoUsoCPU)
    {
        procesoActual = proceso;
        this.tiempoUsoCPU = tiempoUsoCPU;
        iniciarEjecucion();
    }

    private void iniciarEjecucion()
    {
        ocupado = true;
        procesoInterrumpido = false;
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        try
        {
            System.out.printf("El proceso %s tiene un tiempo ejecutado de: %s\n", procesoActual.getIdentificador(), procesoActual.PCB.getTiempoEjecutado());

            for (int i = 0; i < tiempoUsoCPU; i++)
            {
                Thread.sleep(1);
                procesoActual.PCB.aumentarTiempoEjecutado(1);
            }

            System.out.printf("El proceso %s lleva un tiempo ejecutado de: %s\n", procesoActual.getIdentificador(), procesoActual.PCB.getTiempoEjecutado());

            ocupado = false;

        } catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public boolean isOcupado()
    {
        return ocupado;
    }

    public void setOcupado(boolean ocupado)
    {
        this.ocupado = ocupado;
    }

    public Proceso getProcesoActual()
    {
        return procesoActual;
    }

    public void setProcesoActual(Proceso procesoActual)
    {
        this.procesoActual = procesoActual;
    }

    public void interrumpirProceso()
    {
        procesoInterrumpido = true;
    }

}
