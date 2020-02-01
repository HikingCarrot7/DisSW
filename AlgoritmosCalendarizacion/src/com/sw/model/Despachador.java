package com.sw.model;

import java.util.ArrayDeque;
import java.util.Observable;

/**
 *
 * @author HikingC7
 */
public abstract class Despachador extends Observable implements Runnable
{

    protected final CPU CPU;
    protected volatile ArrayDeque<Proceso> procesos;
    protected long tiempoTranscurrido;
    protected volatile boolean running;

    public Despachador(final CPU CPU)
    {
        this.CPU = CPU;
        this.procesos = new ArrayDeque<>();
    }

    protected void despacharProcesos()
    {
        running = true;
        new Thread(this).start();
    }

    public void cambiarContexto(Proceso proceso)
    {
        cambiarContexto(proceso, proceso.PCB.getTiempoRafaga());
    }

    public void cambiarContexto(Proceso proceso, long tiempoUsoCPU)
    {
        notificar(new Notificacion(Notificacion.PROCESO_ENTRO_CPU, proceso, tiempoUsoCPU));
        CPU.ejecutarProceso(proceso, tiempoUsoCPU);
        proceso.PCB.setEstadoProceso(Estado.EJECUCION);
        System.out.println("El CPU ha recibido el proceso: " + proceso.getIdentificador());
    }

    public void aceptarProceso(Proceso proceso)
    {
        procesos.add(proceso);
        proceso.PCB.setEstadoProceso(Estado.LISTO);
        System.out.println("El despachador ha recibido el proceso: " + proceso.getIdentificador());
    }

    @Override
    public abstract void run();

    public void notificar(Notificacion notificacion)
    {
        setChanged();
        notifyObservers(notificacion);
    }

    protected void esperar()
    {
        while (CPU.isOcupado())
        {
        }
    }

    public boolean hayProcesosEsperando()
    {
        return !procesos.isEmpty();
    }

    public void detenerDespachador()
    {
        running = false;
    }

}
