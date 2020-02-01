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
                cambiarContexto(proceso, obtenerTiempoUsoCPU(proceso));

                esperar(); // Esperamos al proceso.

                revisarEstadoProceso(proceso);

                notificar(new Notificacion(
                        proceso.esProcesoTerminado() ? Notificacion.PROCESO_HA_FINALIZADO : Notificacion.PROCESO_DEJO_CPU,
                        proceso,
                        tiempoUsoCPU,
                        tiempoTranscurrido));

                if (proceso.esProcesoTerminado())
                    System.out.println("El CPU ha terminado de ejecutar el proceso: " + proceso.getIdentificador());

                // procesosTerminados.add(proceso); -> calendarizador
                else
                    procesos.addLast(proceso);

                tiempoTranscurrido += tiempoUsoCPU;
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
