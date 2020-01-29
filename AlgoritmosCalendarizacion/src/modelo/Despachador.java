package modelo;

import java.util.ArrayDeque;

/**
 *
 * @author HikingC7
 */
public abstract class Despachador implements Runnable
{

    protected CPU cpu;
    protected volatile ArrayDeque<Proceso> procesos;

    public Despachador(CPU cpu)
    {
        this.cpu = cpu;
        this.procesos = new ArrayDeque<>();
        despachar();
    }

    private void despachar()
    {
        new Thread(this).start();
    }

    public void cambiarContexto(Proceso proceso)
    {
        proceso.PCB().setEstadoProceso(Estado.EJECUTANDOSE);
        System.out.println("El CPU ha recibido el proceso: " + proceso.getIdentificador());
        cpu.ejecutarProceso(proceso);
    }

    public abstract void aceptarProceso(Proceso proceso);

    @Override
    public abstract void run();

    public boolean hayProcesosEsperando()
    {
        return !procesos.isEmpty();
    }

}
