package com.sw.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
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
                procesos.forEach(System.out::println);
                ArrayList<Notificacion> notificaciones = obtenerBloquesASimular(procesos.stream()
                        .sorted(Comparator.comparing(Proceso::getTiempoLlegada).thenComparing(p -> p.PCB.getNumProceso()))
                        .collect(Collectors.toCollection(ArrayList::new)));

                notificaciones.forEach(System.out::println);

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
        int[] tiemposEspera = new int[procesos.size()];
        long tiempoTotalUsoDelCPU = procesos.get(0).getTiempoLlegada();
        Proceso procesoActual = procesos.remove(0);
        boolean interrumpido = false;

        while (!procesos.isEmpty())
        {
            for (int i = 0; i < procesos.size(); i++)
            {
                Proceso procesoSiguienteEnCola = procesos.get(i);

                if (interrumpe(tiempoTotalUsoDelCPU, procesoSiguienteEnCola, procesoActual))
                {
                    long tiempoEjecutado = procesoSiguienteEnCola.getTiempoLlegada() - tiempoTotalUsoDelCPU;
                    procesoActual.PCB.setEstadoProceso(Estado.ESPERA);
                    procesoActual.PCB.aumentarTiempoEjecutado(tiempoEjecutado);

                    bloques.add(new Notificacion(Notificacion.CAMBIO_CONTEXTO, procesoActual.obtenerCopiaProceso(), tiempoEjecutado, tiemposEspera[procesoActual.PCB.getNumProceso()]));

                    Proceso siguienteProceso = siguienteProceso(procesos, procesoSiguienteEnCola);

                    procesos.add(procesoActual);
                    procesoActual = siguienteProceso;
                    procesos.remove(siguienteProceso);
                    tiempoTotalUsoDelCPU += tiempoEjecutado;
                    interrumpido = true;
                    break;

                } else if (lleganAlMismoTiempo(procesoSiguienteEnCola, procesoActual)
                        || llegaDuranteEjecucionProcesoActual(tiempoTotalUsoDelCPU, procesoSiguienteEnCola, procesoActual))
                {
                    System.out.println("El proceso " + procesoSiguienteEnCola + " se ha ido a estado de espera.");
                    procesoSiguienteEnCola.PCB.setEstadoProceso(Estado.ESPERA);

                }

            }

            if (!interrumpido)
            {
                procesoActual.PCB.setEstadoProceso(Estado.TERMINADO);
                tiempoTotalUsoDelCPU += procesoActual.PCB.tiempoRestanteParaFinalizarProceso();
                aumentarTiemposEspera(procesos, tiemposEspera, procesoActual.PCB.tiempoRestanteParaFinalizarProceso());
                bloques.add(new Notificacion(Notificacion.CAMBIO_CONTEXTO, procesoActual.obtenerCopiaProceso(), procesoActual.PCB.tiempoRestanteParaFinalizarProceso(), tiemposEspera[procesoActual.PCB.getNumProceso()]));
                bloques.add(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO, procesoActual.obtenerCopiaProceso(), 0, tiemposEspera[procesoActual.PCB.getNumProceso()]));
                procesos.remove(procesoActual);

                analizarProcesosEntrantesAlFinalizarUnProceso(procesos, tiempoTotalUsoDelCPU);
                Proceso procesoSig = procesoConMenorTiempoParaFinalizar(procesos);
                procesoActual = procesoSig;
                procesos.remove(procesoSig);
            }

            procesos = ordenarProcesosPorTiempoLlegada(procesos);
            interrumpido = false;
        }

        procesoActual.PCB.setEstadoProceso(Estado.TERMINADO);
        tiempoTotalUsoDelCPU += procesoActual.PCB.tiempoRestanteParaFinalizarProceso();
        bloques.add(new Notificacion(Notificacion.CAMBIO_CONTEXTO, procesoActual.obtenerCopiaProceso(), procesoActual.PCB.tiempoRestanteParaFinalizarProceso(), tiemposEspera[procesoActual.PCB.getNumProceso()]));
        bloques.add(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO, procesoActual.obtenerCopiaProceso(), 0, tiemposEspera[procesoActual.PCB.getNumProceso()]));

        return bloques;
    }

    private void aumentarTiemposEspera(ArrayList<Proceso> procesos, int[] tiemposEspera, long tiempoEspera)
    {
        for (Proceso proceso : procesos)
            if (proceso.PCB.getEstadoProceso().equals(Estado.ESPERA))
                tiemposEspera[proceso.PCB.getNumProceso()] += tiempoEspera;
    }

    private ArrayList<Proceso> ordenarProcesosPorTiempoLlegada(ArrayList<Proceso> procesos)
    {
        return procesos.stream()
                .sorted(Comparator.comparing(Proceso::getTiempoLlegada))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void analizarProcesosEntrantesAlFinalizarUnProceso(ArrayList<Proceso> procesos, long tiempoTotalUsoDelCPU)
    {
        procesos.forEach((p) ->
        {
            if (p.getTiempoLlegada() == tiempoTotalUsoDelCPU)
            {
                System.out.println("El proceso " + p.getIdentificador() + " ha entrado en estado de espera.");
                p.PCB.setEstadoProceso(Estado.ESPERA);
            }
        });
    }

    private Proceso procesoConMenorTiempoParaFinalizar(ArrayList<Proceso> procesos)
    {
        try
        {
            return procesos.stream()
                    .filter(p -> p.PCB.getEstadoProceso().equals(Estado.ESPERA))
                    .min(Comparator.comparing(p -> p.PCB.tiempoRestanteParaFinalizarProceso()))
                    .get();

        } catch (NoSuchElementException ex)
        {
            return null;
        }
    }

    private ArrayList<Proceso> ordenarProcesosPorTiempoParaFinalizar(ArrayList<Proceso> procesos)
    {
        return procesos.stream()
                .sorted(Comparator.comparing(p -> p.PCB.tiempoRestanteParaFinalizarProceso()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     *
     * @param procesoLlegada
     * @param procesoActual
     *
     * @return Si el proceso proceso de llegada puede interrumpir al proceso actual.
     */
    private boolean interrumpe(long tiempoUsoDelCPU, Proceso procesoLlegada, Proceso procesoActual)
    {
        return procesoLlegada.PCB.getEstadoProceso().equals(Estado.LISTO)
                && procesoLlegada.PCB.tiempoRestanteParaFinalizarProceso() < (tiempoUsoDelCPU + procesoActual.PCB.tiempoRestanteParaFinalizarProceso() - procesoLlegada.getTiempoLlegada());

    }

    private Proceso siguienteProceso(ArrayList<Proceso> procesos, Proceso procesoSiguienteEnCola)
    {

        try
        {
            Proceso procesoEnEsperaMinTiempoRestante = procesos.stream()
                    .filter(p -> p.PCB.getEstadoProceso().equals(Estado.ESPERA))
                    .min(Comparator.comparing(p -> p.PCB.tiempoRestanteParaFinalizarProceso()))
                    .get();

            return procesoSiguienteEnCola.PCB.tiempoRestanteParaFinalizarProceso() <= procesoEnEsperaMinTiempoRestante.PCB.tiempoRestanteParaFinalizarProceso()
                    ? procesoSiguienteEnCola : procesoEnEsperaMinTiempoRestante;

        } catch (NoSuchElementException ex)
        {
            return procesoSiguienteEnCola;
        }

    }

    private boolean lleganAlMismoTiempo(Proceso procesoLlegada, Proceso procesoActual)
    {
        return procesoLlegada.getTiempoLlegada() == procesoActual.getTiempoLlegada();
    }

    private boolean llegaDuranteEjecucionProcesoActual(long tiempoUsoDelCPU, Proceso procesoLlegada, Proceso procesoActual)
    {
        return procesoLlegada.PCB.getEstadoProceso().equals(Estado.LISTO)
                && procesoLlegada.getTiempoLlegada() > tiempoUsoDelCPU
                && procesoLlegada.getTiempoLlegada() < tiempoUsoDelCPU + procesoActual.PCB.tiempoRestanteParaFinalizarProceso();
    }

    public void todosProcesosEntregados()
    {
        todosProcesosEntregados = true;
    }

}
