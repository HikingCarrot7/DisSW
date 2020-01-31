package modelo;

import java.util.ArrayDeque;
import java.util.Observable;

/**
 *
 * @author HikingC7
 */
public abstract class Despachador extends Observable implements Runnable
{

    protected CPU cpu;
    protected volatile ArrayDeque<Proceso> procesos;
    protected long tiempoTranscurrido;

    public Despachador(CPU cpu)
    {
        this.cpu = cpu;
        this.procesos = new ArrayDeque<>();
    }

    protected void despacharProcesos()
    {
        new Thread(this).start();
    }

    public Proceso cambiarContexto(Proceso proceso)
    {
        notificar(new Notificacion(Notificacion.PROCESO_ENTRO_CPU, proceso, proceso.PCB.getTiempoRafaga()));
        cpu.ejecutarProceso(proceso);
        proceso.PCB.setEstadoProceso(Estado.EJECUCION);
        System.out.println("El CPU ha recibido el proceso: " + proceso.getIdentificador());
        return proceso;
    }

    public Proceso cambiarContexto(Proceso proceso, long tiempoUsoCPU)
    {
        cpu.ejecutarProceso(proceso, tiempoUsoCPU);
        proceso.PCB.setEstadoProceso(Estado.EJECUCION);
        System.out.println("El CPU ha recibido el proceso: " + proceso.getIdentificador());
        return proceso;
    }

    public abstract void aceptarProceso(Proceso proceso);

    @Override
    public abstract void run();

    public void notificar(Notificacion notificacion)
    {
        setChanged();
        notifyObservers(notificacion);
    }

    protected void esperar()
    {
        while (cpu.isOcupado())
        {
        }
    }

    public boolean hayProcesosEsperando()
    {
        return !procesos.isEmpty();
    }

}
