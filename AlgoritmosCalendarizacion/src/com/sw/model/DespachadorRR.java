package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class DespachadorRR extends Despachador
{

    private final long QUANTUM;

    public DespachadorRR(final CPU CPU, final long QUANTUM)
    {
        super(CPU);
        this.QUANTUM = QUANTUM;
    }

    @Override
    public void run()
    {
        while (running)
            if (hayProcesosEsperando())
            {
                Proceso proceso = procesos.remove();
                long tiempoUsoCPU = obtenerTiempoUsoCPU(proceso);
                cambiarContexto(proceso, tiempoUsoCPU);

                esperar(); // Esperamos al proceso.

                revisarEstadoProceso(proceso);

                if (proceso.esProcesoTerminado())
                {
                    notificar(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO, proceso, 0, tiempoEsperaProceso(proceso), tiempoTotalUsoCPU + tiempoUsoCPU));
                    System.out.println("El CPU ha terminado de ejecutar el proceso: " + proceso.getIdentificador());

                } else
                {
                    notificar(new Notificacion(Notificacion.PROCESO_DEJO_CPU, proceso, 0, tiempoEsperaProceso(proceso)));
                    procesos.addLast(proceso);
                }
                // procesosTerminados.add(proceso); -> calendarizador
                tiempoTotalUsoCPU += tiempoUsoCPU;
            }

    }

    private long obtenerTiempoUsoCPU(Proceso proceso)
    {
        return tiempoRestanteProceso(proceso) >= QUANTUM ? QUANTUM : tiempoRestanteProceso(proceso);
    }

    private void revisarEstadoProceso(Proceso proceso)
    {
        proceso.PCB.setEstadoProceso(tiempoRestanteProceso(proceso) <= 0 ? Estado.TERMINADO : Estado.ESPERA);
    }

    private long tiempoRestanteProceso(Proceso proceso)
    {
        return proceso.PCB.getTiempoRafaga() - proceso.PCB.getTiempoEjecutado();
    }

}
