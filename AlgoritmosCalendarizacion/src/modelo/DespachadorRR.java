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

    public DespachadorRR(CPU cpu, final long QUANTUM)
    {
        super(cpu);
        this.QUANTUM = QUANTUM;
        procesosTerminados = new ArrayList<>();
    }

    public void entregarProcesos(ArrayList<ProcesoRR> procesos)
    {
        procesos.forEach((proceso) ->
        {
            aceptarProceso(proceso);
        });

        despacharProcesos();
    }

    @Override
    public void aceptarProceso(Proceso proceso)
    {
        System.out.println("El despachador ha recibido el proceso: " + proceso.getIdentificador());
        //notificar("El despachador ha recibido el proceso: " + proceso.getIdentificador());
        procesos.add(proceso);
        proceso.PCB.setEstadoProceso(Estado.LISTO);
    }

    @Override
    public void run()
    {
        while (true)
            if (hayProcesosEsperando())
            {
                Proceso proceso = procesos.remove();
                long tiempoUsoCPU = obtenerTiempoUsoCPU(proceso);
                cambiarContexto(proceso, obtenerTiempoUsoCPU(proceso));

                esperar(); // Esperamos al proceso.

                revisarEstadoProceso(proceso);
                notificar(new Notificacion(proceso.esProcesoTerminado()
                        ? Notificacion.PROCESO_HA_FINALIZADO : Notificacion.PROCESO_DEJO_CPU, proceso, tiempoUsoCPU, tiempoTranscurrido));
                tiempoTranscurrido += tiempoUsoCPU;

                if (proceso.esProcesoTerminado())
                {
                    System.out.println("El CPU ha terminado de ejecutar el proceso: " + proceso.getIdentificador());
                    procesosTerminados.add(proceso);

                } else
                    procesos.addLast(proceso);

            }

    }

    private long obtenerTiempoUsoCPU(Proceso proceso)
    {
        return tiempoRestanteProceso(proceso) >= QUANTUM ? QUANTUM : tiempoRestanteProceso(proceso);
    }

    private void revisarEstadoProceso(Proceso proceso)
    {
        proceso.PCB.setEstadoProceso(tiempoRestanteProceso(proceso) <= 0 ? Estado.TERMINADO : Estado.ESPERA);
    }

    private long tiempoRestanteProceso(Proceso proceso)
    {
        return proceso.PCB.getTiempoRafaga() - proceso.PCB.getTiempoEjecutado();
    }

}
