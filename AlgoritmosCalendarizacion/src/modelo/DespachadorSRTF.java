package modelo;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

/**
 *
 * @author HikingCarrot7
 */
public class DespachadorSRTF extends Despachador
{

    public DespachadorSRTF(CPU cpu)
    {
        super(cpu);
        despacharProcesos();
    }

    @Override
    public void aceptarProceso(Proceso proceso)
    {
        System.out.println("El despachador ha recibido el proceso: " + proceso.getIdentificador());
        procesos.add(proceso);
        procesos = procesos.stream()
                .sorted((p1, p2) -> p1.PCB().compareTo(p2.PCB()))
                .peek(p -> p.PCB().setEstadoProceso(Estado.LISTO))
                .collect(Collectors.toCollection(ArrayDeque::new));

        if (!cpu.isOcupado())
            cambiarContexto(procesos.remove());
    }

    @Override
    public void run()
    {
        while (true)
            if (!cpu.isOcupado() && hayProcesosEsperando())
                cambiarContexto(procesos.remove());
    }

}
