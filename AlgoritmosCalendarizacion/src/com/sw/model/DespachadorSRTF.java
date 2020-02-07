package com.sw.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *
 * @author HikingC7
 */
public class DespachadorSRTF extends Despachador
{

    private Proceso procesoActualEnCPU;
    private volatile boolean enEspera;
    private boolean entroPrimerProceso;

    public DespachadorSRTF(final CPU CPU)
    {
        super(CPU);
    }

    @Override
    public void aceptarProceso(Proceso proceso)
    {
        super.aceptarProceso(proceso);

        if (!entroPrimerProceso)//Si es el primer proceso entra directamente
        {
            procesoActualEnCPU = proceso;
            notificar(new Notificacion(Notificacion.CAMBIO_CONTEXTO, proceso, 0, tiempoEsperaProceso(proceso)));
            System.out.println("El primer proceso es : " + proceso.getIdentificador());
            entroPrimerProceso = true;

        } else //En caso contrario, debemos revisar quien tiene el menor tiempo restante para terminar su proceso.
        {
            cpu.interrumpirProceso();

            while (enEspera)
            {
            }

            System.out.println("Ha llegado el proceso : " + proceso.getIdentificador() + " en el tiempo: " + tiempoTotalUsoCPU);

            enEspera = true;
            procesoActualEnCPU.PCB.setEstadoProceso(Estado.ESPERA);
            procesos.addLast(procesoActualEnCPU);

            procesos = procesos.stream()
                    .sorted(Comparator.comparing(p -> p.PCB.tiempoRestanteParaFinalizarProceso()))
                    .collect(Collectors.toCollection(ArrayDeque::new));

            procesoActualEnCPU = procesos.remove();
            notificar(new Notificacion(Notificacion.CAMBIO_CONTEXTO, procesoActualEnCPU, 0, tiempoEsperaProceso(procesoActualEnCPU))); // ------------------> Hacer algo especial.
            enEspera = false;
        }

    }

    @Override
    public void run()
    {
        while (running)
            if (procesoActualEnCPU != null && hayProcesosEsperando()) // Mientras hayan procesos en la cola.
            {
                cambiarContexto(procesoActualEnCPU, procesoActualEnCPU.PCB.tiempoRestanteParaFinalizarProceso()); // Cambiamos de contexto.
                cpu.ejecutarProceso(procesoActualEnCPU); // Ejecutamos el proceso actual.

                enEspera = true;
                esperar(); // Esperamos a que termine el CPU de ejecutarlo o el proceso sea interrumpido.
                tiempoTotalUsoCPU += procesoActualEnCPU.PCB.getTiempoEjecutado(); // Aumentamos el tiempo total del uso del CPU.
                enEspera = false;

                if (!cpu.isProcesoInterrumpido()) // Si el proceso no fue interrumpido. (el proceso ha finalizado)
                {
                    procesoActualEnCPU.PCB.setEstadoProceso(Estado.TERMINADO); // Ha terminado por completo el proceso.
                    notificar(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO, procesoActualEnCPU, 0)); // Notificamos que un proceso ha terminado por complento su ejecución.
                    procesoActualEnCPU = procesos.remove(); // Pasamos al siguiente proceso que esté en la cola.

                } else
                    while (enEspera)
                    {
                    }

                //-------------------------------- el bug del ultimo proceso
            }

    }

    private ArrayList<Notificacion> obtenerBloquesASimular(ArrayDeque<Proceso> procesos)
    {
        ArrayList<Notificacion> bloques = new ArrayList<>();
        long tiempoEsperaPorProceso = 0;
        long tiempoTotalUsoDelCPU = procesos.getFirst().getTiempoLlegada();

        while (!procesos.isEmpty())
        {
            Proceso procesoActual = procesos.removeFirst();
            ArrayDeque<Proceso> procesosEntrantes = procesosQueLleganDuranteEjecucion(tiempoTotalUsoDelCPU, procesoActual.PCB.getTiempoRafaga(), procesos);

            for (int i = 0, nProcesosEntrantes = procesosEntrantes.size(); i < nProcesosEntrantes; i++)
                if (puedeInterrumpir(tiempoTotalUsoDelCPU, procesosEntrantes.removeFirst(), procesoActual))
                {

                }

        }

        return bloques;
    }

    private ArrayDeque<Proceso> ordenarProcesosOrdenadosPorTiempoRafaga(ArrayDeque<Proceso> procesos)
    {
        return procesos.stream()
                .sorted(Comparator.comparing(p -> p.PCB.getTiempoRafaga()))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private boolean todosProcesosTerminados(ArrayDeque<Proceso> procesos)
    {
        return procesos.stream().anyMatch(p -> !p.PCB.getEstadoProceso().equals(Estado.TERMINADO));
    }

    private boolean esProcesoNuevo(Proceso proceso)
    {
        return proceso.PCB.getEstadoProceso().equals(Estado.NUEVO);
    }

    private boolean todosProcesosYaLlegaron(ArrayDeque<Proceso> procesos)
    {
        return procesos.stream().anyMatch(p -> !p.PCB.getEstadoProceso().equals(Estado.NUEVO));
    }

    /**
     *
     * @param p1
     * @param p2
     *
     * @return Si el proceso p1 puede interrumpir al proceso p2.
     */
    private boolean puedeInterrumpir(long tiempoUsoDelCPU, Proceso p1, Proceso p2)
    {
        return p1.PCB.getTiempoRafaga() < (tiempoUsoDelCPU + p2.PCB.getTiempoRafaga() - p1.getTiempoLlegada());
    }

    private ArrayDeque<Proceso> procesosQueLleganDuranteEjecucion(long tiempoMinimo, long tiempoMaximo, ArrayDeque<Proceso> procesos)
    {
        return procesos.stream()
                .filter(p -> p.getTiempoLlegada() > tiempoMinimo && p.getTiempoLlegada() < tiempoMaximo)
                .sorted(Comparator.comparing(Proceso::getTiempoLlegada))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

}
