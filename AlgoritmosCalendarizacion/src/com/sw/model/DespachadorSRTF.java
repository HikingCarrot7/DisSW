package com.sw.model;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

/**
 *
 * @author HikingCarrot7
 */
public class DespachadorSRTF extends Despachador
{

    public DespachadorSRTF(final CPU CPU)
    {
        super(CPU);
    }

    @Override
    public void aceptarProceso(Proceso proceso)
    {
        super.aceptarProceso(proceso);

        procesos = procesos.stream()
                .sorted((p1, p2) -> p1.PCB.compareTo(p2.PCB))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    @Override
    public void run()
    {
        while (running)
            if (!CPU.isOcupado() && hayProcesosEsperando())
            {
                Proceso procesoActual = procesos.remove();
                cambiarContexto(procesoActual);

                esperar();

                procesoActual.PCB.setEstadoProceso(Estado.TERMINADO);

                System.out.println("El CPU ha terminado de ejecutar el proceso: " + procesoActual.getIdentificador());

                notificar(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO,
                        procesoActual,
                        0,
                        tiempoEsperaProceso(procesoActual),
                        tiempoTotalUsoCPU + procesoActual.PCB.getTiempoRafaga()));

                tiempoTotalUsoCPU += procesoActual.PCB.getTiempoRafaga();
            }

    }

}
