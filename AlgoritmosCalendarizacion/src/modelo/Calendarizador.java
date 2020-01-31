package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *
 * @author HikingCarrot7
 */
public class Calendarizador implements Runnable
{

    private ArrayList<Proceso> procesosAEntregar;
    private Despachador despachador;

    public Calendarizador(ArrayList<Proceso> procesosAEntregar, Despachador despachador)
    {
        this.procesosAEntregar = procesosAEntregar;
        this.despachador = despachador;
        ordenarProcesosTiempoLlegada();
        entregarProcesosADespachador();
    }

    private void ordenarProcesosTiempoLlegada()
    {
        procesosAEntregar = procesosAEntregar.stream()
                .sorted(Comparator.comparing(Proceso::getTiempoLlegada))
                .collect(Collectors.toCollection(ArrayList::new));
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
                System.out.println("Se ha entregado al despachador el proceso " + proceso.getIdentificador());

            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        });
    }

}
