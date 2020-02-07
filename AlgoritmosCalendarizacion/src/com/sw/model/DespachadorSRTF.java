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
                ArrayList<Notificacion> notificaciones = obtenerBloquesASimular(procesos.stream()
                        .sorted(Comparator.comparing(Proceso::getTiempoLlegada))
                        .collect(Collectors.toCollection(ArrayList::new)));

                notificaciones.forEach(System.out::println);

                /*System.out.println(notificaciones.size());

                for (int i = 0; i < notificaciones.size(); i++)
                {
                    cpu.ejecutarProceso(notificaciones.get(i).getProceso(), notificaciones.get(i).getTiempoUsoCPU());
                    notificar(notificaciones.get(i));
                    esperar();
                }*/
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
                Proceso procesoSiguienteEnCola = procesos.get(i);

                if (interrumpe(tiempoTotalUsoDelCPU, procesoSiguienteEnCola, procesoActual))
                {
                    System.out.println("Interrumpido -> " + procesoSiguienteEnCola.getIdentificador());
                    long tiempoEjecutado = procesoSiguienteEnCola.getTiempoLlegada() - tiempoTotalUsoDelCPU;
                    procesoActual.PCB.setEstadoProceso(Estado.ESPERA);
                    procesoActual.PCB.aumentarTiempoEjecutado(tiempoEjecutado);

                    bloques.add(new Notificacion(Notificacion.CAMBIO_CONTEXTO, procesoActual.obtenerCopiaProceso(), tiempoEjecutado, tiempoEjecutado)); // Tiempo espera.

                    Proceso siguienteProceso = siguienteProceso(procesos, procesoSiguienteEnCola);

                    System.out.println(siguienteProceso.identificador);

                    procesos.add(procesoActual);
                    procesoActual = siguienteProceso;
                    procesos.remove(siguienteProceso);
                    tiempoTotalUsoDelCPU += tiempoEjecutado;
                    interrumpido = true;
                    break;
                }

            }

            if (!interrumpido)
            {
                procesoActual.PCB.setEstadoProceso(Estado.TERMINADO);
                tiempoTotalUsoDelCPU += procesoActual.PCB.tiempoRestanteParaFinalizarProceso();
                bloques.add(new Notificacion(Notificacion.CAMBIO_CONTEXTO, procesoActual.obtenerCopiaProceso(), procesoActual.PCB.tiempoRestanteParaFinalizarProceso(), procesoActual.PCB.tiempoRestanteParaFinalizarProceso())); // Tiempo espera.
                bloques.add(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO, procesoActual.obtenerCopiaProceso(), 0, tiempoEsperaPorProceso));
                procesos.remove(procesoActual);
                procesos = ordenarProcesosPorTiempoParaFinalizar(procesos);
                procesoActual = procesos.remove(0);
            }

            interrumpido = false;
        }

        procesoActual.PCB.setEstadoProceso(Estado.TERMINADO);
        tiempoTotalUsoDelCPU += procesoActual.PCB.tiempoRestanteParaFinalizarProceso();
        bloques.add(new Notificacion(Notificacion.CAMBIO_CONTEXTO, procesoActual.obtenerCopiaProceso(), procesoActual.PCB.tiempoRestanteParaFinalizarProceso(), procesoActual.PCB.tiempoRestanteParaFinalizarProceso())); // Tiempo espera.
        bloques.add(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO, procesoActual.obtenerCopiaProceso(), 0, tiempoEsperaPorProceso));

        return bloques;
    }

    private ArrayList<Proceso> ordenarProcesosPorTiempoRafaga(ArrayList<Proceso> procesos)
    {
        return procesos.stream()
                .sorted(Comparator.comparing(p -> p.PCB.getTiempoRafaga()))
                .collect(Collectors.toCollection(ArrayList::new));
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
                && procesoLlegada.PCB.getTiempoRafaga() < (tiempoUsoDelCPU + procesoActual.PCB.getTiempoRafaga() - procesoLlegada.getTiempoLlegada());

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

    private boolean llegaDuranteEjecucionProcesoActual(long tiempoUsoDelCPU, Proceso procesoLlegada, Proceso procesoActual)
    {
        return procesoLlegada.PCB.getEstadoProceso().equals(Estado.NUEVO) && procesoLlegada.getTiempoLlegada() > tiempoUsoDelCPU
                && procesoLlegada.getTiempoLlegada() < tiempoUsoDelCPU + procesoLlegada.PCB.getTiempoRafaga();
    }

    public void todosProcesosEntregados()
    {
        todosProcesosEntregados = true;
    }

}
