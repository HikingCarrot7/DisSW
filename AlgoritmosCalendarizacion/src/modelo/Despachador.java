package modelo;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *
 * @author HikingC7
 */
public class Despachador implements Runnable
{

    private CPU cpu;
    private volatile Queue<Proceso> procesos;

    public Despachador(CPU cpu)
    {
        this.cpu = cpu;
        this.procesos = new ArrayDeque<>();
        despachar();
    }

    public void recibirProceso(Proceso proceso)
    {
        System.out.println("El despachador ha recibido el proceso: " + proceso.getIdentificador());
        procesos.add(proceso);
        procesos = procesos.stream()
                .sorted(Comparator.comparing(Proceso::getTiempoRafaga))
                .collect(Collectors.toCollection(ArrayDeque::new));

        if (!cpu.isOcupado())
            cambiarContexto(procesos.remove());
    }

    private void despachar()
    {
        new Thread(this).start();
    }

    public void cambiarContexto(Proceso proceso)
    {
        cpu.ejecutarProceso(proceso);
    }

    @Override
    public void run()
    {
        while (true)
            if (!cpu.isOcupado() && hayProcesosEsperando())
                cambiarContexto(procesos.remove());
    }

    public boolean hayProcesosEsperando()
    {
        return !procesos.isEmpty();
    }

}
