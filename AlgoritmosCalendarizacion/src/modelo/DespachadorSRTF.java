package modelo;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

/**
 *
 * @author HikingCarrot7
 */
public class DespachadorSRTF extends Despachador
{

    private Proceso procesoActual;

    public DespachadorSRTF(CPU cpu)
    {
        super(cpu);
    }

    @Override
    public void aceptarProceso(Proceso proceso)
    {
        System.out.println("El despachador ha recibido el proceso: " + proceso.getIdentificador());
        procesoActual = proceso;
        //notificar("El despachador ha recibido el proceso: " + proceso.getIdentificador());
        procesos.add(proceso);
        procesos = procesos.stream()
                .sorted((p1, p2) -> p1.PCB.compareTo(p2.PCB))
                .peek(p -> p.PCB.setEstadoProceso(Estado.LISTO))
                .collect(Collectors.toCollection(ArrayDeque::new));

        if (!cpu.isOcupado())
            cambiarContexto(procesos.remove());

    }

    @Override
    public void run()
    {
        while (true)
            if (!cpu.isOcupado() && hayProcesosEsperando())
            {
                procesoActual = procesos.remove();
                cambiarContexto(procesoActual);

                esperar();

                procesoActual.PCB.setEstadoProceso(Estado.TERMINADO);
                notificar(new Notificacion(Notificacion.PROCESO_HA_FINALIZADO,
                        procesoActual,
                        procesoActual.PCB.getTiempoRafaga(),
                        tiempoTranscurrido));

                tiempoTranscurrido += procesoActual.PCB.getTiempoRafaga();
            }

    }

}
