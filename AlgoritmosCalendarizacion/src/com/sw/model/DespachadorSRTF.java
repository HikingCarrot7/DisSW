package com.sw.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *
 * @author HikingC7
 */
public class DespachadorSRTF extends Despachador
{

    private volatile boolean todosProcesosEntregados;

    public DespachadorSRTF(final CPU CPU)
    {
        super(CPU);
    }

    @Override
    public void run()
    {
        while (running)
            if (todosProcesosEntregados)
            {
                ArrayList<Notificacion> notificaciones = obtenerBloquesASimular(procesos.stream().collect(Collectors.toCollection(ArrayList::new)));

                for (int i = 0; i < notificaciones.size(); i++)
                {
                    cpu.ejecutarProceso(notificaciones.get(i).getProceso(), notificaciones.get(i).getTiempoUsoCPU());
                    notificar(notificaciones.get(i));
                    esperar();
                }

                break;
            }

    }

    private ArrayList<Notificacion> obtenerBloquesASimular(ArrayList<Proceso> procesos)
    {
        ArrayList<Notificacion> bloques = new ArrayList<>();
        long tiempoEsperaPorProceso = 0;
        long tiempoTotalUsoDelCPU = procesos.get(0).getTiempoLlegada();
        Proceso procesoActual = procesos.remove(0);
        boolean interrumpido = false;

        while (!procesos.isEmpty())
        {
            for (int i = 0; i < procesos.size(); i++)
            {
                Proceso procesoSiguiente = procesos.get(i);

                if (puedeInterrumpir(tiempoTotalUsoDelCPU, procesoSiguiente, procesoActual))
                {
                    long tiempoEjecutado = procesoSiguiente.getTiempoLlegada() - tiempoTotalUsoDelCPU;
                    procesoActual.PCB.setEstadoProceso(Estado.ESPERA);
                    procesoActual.PCB.aumentarTiempoEjecutado(tiempoEjecutado);
                    procesos.add(procesoActual);
                    bloques.add(new Notificacion(Notificacion.CAMBIO_CONTEXTO, procesoActual.obtenerCopiaProceso(), tiempoEjecutado, tiempoEsperaPorProceso));
                    procesoActual = procesos.get(obtenerIndexSiguienteProceso(tiempoTotalUsoDelCPU, procesoActual, procesos));
                    interrumpido = true;
                    break;
                }

            }

            if (!interrumpido)
            {
                procesoActual.PCB.setEstadoProceso(Estado.TERMINADO);
                tiempoTotalUsoDelCPU += (procesoActual.PCB.getTiempoRafaga() - procesoActual.PCB.getTiempoEjecutado());
                bloques.add(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO, procesoActual.obtenerCopiaProceso(), 0, tiempoEsperaPorProceso));
                procesos.remove(procesoActual);
                procesos = ordenarProcesosPorTiempoRafaga(procesos);
                interrumpido = false;
            }

        }

        //Mañana revisamos los tiempos de espera.
        return bloques;
    }

    private ArrayList<Proceso> ordenarProcesosPorTiempoRafaga(ArrayList<Proceso> procesos)
    {
        return procesos.stream()
                .sorted(Comparator.comparing(p -> p.PCB.getTiempoRafaga()))
                .collect(Collectors.toCollection(ArrayList::new));
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
        return p1.PCB.getTiempoRafaga() < (tiempoUsoDelCPU + p2.PCB.getTiempoRafaga() - p1.getTiempoLlegada())
                && p1.PCB.getEstadoProceso().equals(Estado.NUEVO);
    }

    private int obtenerIndexSiguienteProceso(long tiempoUsoDelCPU, Proceso proceso, ArrayList<Proceso> procesos)
    {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < procesos.size(); i++)
            if (puedeInterrumpir(tiempoUsoDelCPU, procesos.get(i), proceso))
                indices.add(i);

        int indexMenor = 0;

        for (int i = 0; i < indices.size(); i++)
            if (procesos.get(indices.get(i)).PCB.getTiempoRafaga() < procesos.get(indexMenor).PCB.getTiempoRafaga())
                indexMenor = i;

        return indexMenor;
    }

    public void todosProcesosEntregados()
    {
        todosProcesosEntregados = true;
    }

}
