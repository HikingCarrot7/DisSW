package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *
 * @author HikingC7
 */
public class Calendarizador implements Runnable
{

    private ArrayList<Proceso> procesos;
    private Despachador despachador;

    public Calendarizador(ArrayList<Proceso> procesos, Despachador despachador)
    {
        this.procesos = procesos;
        this.despachador = despachador;
        ordenarProcesosTiempoLlegada();
        entregarProcesos();
    }

    private void ordenarProcesosTiempoLlegada()
    {
        procesos = procesos.stream()
                .sorted(Comparator.comparing(Proceso::getTiempoLlegada))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void entregarProcesos()
    {
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        procesos.forEach((proceso) ->
        {
            try
            {
                Thread.sleep(proceso.getTiempoLlegada());
                despachador.recibirProceso(proceso);
                System.out.println("Se ha entregado al despachador el proceso " + proceso.getIdentificador());

            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        });
    }

}
