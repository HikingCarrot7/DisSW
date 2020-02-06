package com.sw.model;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.stream.Collectors;

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

        if (procesoActualEnCPU == null)// Si es el primer proceso que entra.
        {
            procesoActualEnCPU = proceso;
            cambiarContexto(procesoActualEnCPU, procesoActualEnCPU.PCB.tiempoRestanteParaFinalizarProceso());

        } else if (proceso.PCB.getTiempoRafaga() < procesoActualEnCPU.PCB.tiempoRestanteParaFinalizarProceso()) // Si el proceso que acaba de entrar tiene un tiempo ráfaga menor
        //del tiempo que le falta al proceso que está actualmente ocupando el CPU.
        {
            procesos.addLast(procesoActualEnCPU); // Proceso actual se añade a la cola.
            procesoActualEnCPU.PCB.setEstadoProceso(Estado.ESPERA); // Se pone en estado de espera.
            System.out.println("");//Notificamos al controlador que habrá un cambio de contexto.
            procesoActualEnCPU = proceso; // Actualizamos el proceso actual.
            cpu.interrumpirProceso(); //Interrumpimos al CPU.

        } else // En caso de que el proceso que acaba de entrar tenga un tiempo ráfaga mayor o igual al tiempo que le falta al proceso que se está ejecutando actualmente en el CPU, únicamente ordenamos la cola.
            procesos = procesos.stream()
                    .sorted(Comparator.comparing(p -> p.PCB.tiempoRestanteParaFinalizarProceso()))
                    .collect(Collectors.toCollection(ArrayDeque::new));

    }

    @Override
    public void run()
    {
        while (running)
            if (hayProcesosEsperando()) // Mientras hayan procesos en la cola.
            {
                cpu.ejecutarProceso(procesoActualEnCPU); // Ejecutamos el proceso actual.
                esperar(); // Esperamos a que termine el CPU de ejecutarlo o el proceso sea interrumpido.

                if (!cpu.isProcesoInterrumpido()) // Si el proceso no fue interrumpido. (el proceso ha finalizado)
                {
                    procesoActualEnCPU.PCB.setEstadoProceso(Estado.TERMINADO); // Ha terminado por completo el proceso.
                    System.out.println(""); // Notificamos que un proceso ha terminado por complento su ejecución.
                    procesoActualEnCPU = procesos.remove(); // Pasamos al siguiente proceso que esté en la cola.
                    cambiarContexto(procesoActualEnCPU, procesoActualEnCPU.PCB.tiempoRestanteParaFinalizarProceso()); // Cambiamos de contexto.
                }

            }

    }

}
