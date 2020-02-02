package com.sw.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observer;
import java.util.stream.Collectors;

/**
 *
 * @author HikingCarrot7
 */
public class Calendarizador implements Runnable, Observer
{

    private ArrayList<Proceso> procesosAEntregar;
    private ArrayList<Proceso> procesosTerminados;
    private Despachador despachador;

    public Calendarizador(ArrayList<Proceso> procesosAEntregar, Despachador despachador)
    {
        this.procesosAEntregar = procesosAEntregar;
        this.despachador = despachador;
        procesosTerminados = new ArrayList<>();
        despachador.addObserver(this);
        ordenarProcesosTiempoLlegada();
        entregarProcesosADespachador();
    }

    private void ordenarProcesosTiempoLlegada()
    {
        procesosAEntregar = procesosAEntregar.stream()
                .sorted(Comparator.comparing(Proceso::getTiempoLlegada))
                .collect(Collectors.toCollection(ArrayList::new));

        if (!procesosAEntregar.isEmpty())
            procesosAEntregar.get(0).setTiempoLlegada(0);
    }

    private void entregarProcesosADespachador()
    {
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        despachador.despacharProcesos();

        procesosAEntregar.forEach((proceso) ->
        {
            try
            {
                Thread.sleep(proceso.getTiempoLlegada());
                despachador.aceptarProceso(proceso);

            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        });
    }

    @Override
    public void update(java.util.Observable o, Object notif)
    {
        Notificacion notificacion = (Notificacion) notif;

        if (notificacion.getIdentificador() == Notificacion.PROCESO_HA_FINALIZADO)
            procesosTerminados.add(notificacion.getProceso());
    }

    public boolean todosProcesosTerminados()
    {
        return procesosAEntregar.size() == procesosTerminados.size();
    }

    public void reiniciarCalendarizador()
    {
        procesosAEntregar.clear();
        procesosTerminados.clear();
    }

}
