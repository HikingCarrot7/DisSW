package modelo;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class DespachadorRR extends Despachador
{

    private final long QUANTUM;
    private ArrayList<Proceso> procesosTerminados;

    public DespachadorRR(CPU cpu, ArrayList<ProcesoRR> procesos, long QUANTUM)
    {
        super(cpu);
        this.QUANTUM = QUANTUM;
        procesosTerminados = new ArrayList<>();
        entregarProcesos(procesos);
    }

    private void entregarProcesos(ArrayList<ProcesoRR> procesos)
    {
        procesos.forEach((proceso) ->
        {
            aceptarProceso(proceso);
        });
    }

    @Override
    public void aceptarProceso(Proceso proceso)
    {
        System.out.println("El despachador ha recibido el proceso: " + proceso.getIdentificador());
        proceso.PCB().setEstadoProceso(Estado.LISTO);
        procesos.add(proceso);

        if (!cpu.isOcupado())
            cambiarContexto(procesos.remove());
    }

    private void revisarEstadoProceso(Proceso proceso)
    {
        if (proceso.PCB().getTiempoRafaga() - proceso.PCB().getTiempoEjecutado() <= 0)
        {
            proceso.PCB().setEstadoProceso(Estado.TERMINADO);
            System.out.println("El CPU ha terminado de ejecutar el proceso: " + proceso.getIdentificador());
            procesosTerminados.add(proceso);
        }

    }

    @Override
    public void run()
    {
        while (true)
            if (hayProcesosEsperando())
                try
                {

                    Thread.sleep(QUANTUM);
                    Proceso proceso = cpu.getProcesoActual();
                    cpu.interrumpirProceso();
                    proceso.PCB().setEstadoProceso(Estado.ESPERA);

                    revisarEstadoProceso(proceso);

                    if (!proceso.procesoTerminado())
                        procesos.addLast(proceso);

                    cambiarContexto(procesos.remove());

                } catch (InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }

    }

}
