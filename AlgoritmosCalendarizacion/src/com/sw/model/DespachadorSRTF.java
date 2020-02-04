package com.sw.model;

/**
 *
 * @author HikingC7
 */
public class DespachadorSRTF extends Despachador
{

    private Proceso procesoActualEnCPU;

    public DespachadorSRTF(final CPU CPU)
    {
        super(CPU);
    }

    @Override
    public void aceptarProceso(Proceso proceso)
    {
        super.aceptarProceso(proceso);

        //Necesitamos saber quien tiene el menor tiempo cuando llega un nuevo proceso
        //(¿Es el que se estaba ejecutando o el que acaba de llegar?)
        //Cuando termine un proceso, necesitamos saber quien de la cola tiene el menor tiempo (será el que sigue).
        if (procesoActualEnCPU == null)
            procesoActualEnCPU = proceso;

        else if (proceso.PCB.getTiempoRafaga() < procesoActualEnCPU.PCB.tiempoRestanteParaFinalizarProceso())
        {
            procesos.addLast(procesoActualEnCPU);
            procesoActualEnCPU.PCB.setEstadoProceso(Estado.ESPERA);
            System.out.println("");
            procesoActualEnCPU = proceso;
            cpu.interrumpirProceso();
        }

    }

    @Override
    public void run()
    {
        while (running)
            if (hayProcesosEsperando())
            {
                cpu.ejecutarProceso(procesoActualEnCPU);
                esperar();

                if (!cpu.isProcesoInterrumpido())
                {
                    procesoActualEnCPU.PCB.setEstadoProceso(Estado.TERMINADO);
                    System.out.println("");
                    procesoActualEnCPU = procesos.remove();

                }

            }

    }

}
