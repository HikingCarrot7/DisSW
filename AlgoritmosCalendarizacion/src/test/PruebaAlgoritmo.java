package test;

import java.util.ArrayList;
import modelo.CPU;
import modelo.Calendarizador;
import modelo.Despachador;
import modelo.Proceso;

/**
 *
 * @author HikingC7
 */
public class PruebaAlgoritmo
{

    public static void main(String[] args)
    {
        CPU cpu = new CPU();
        Despachador despachador = new Despachador(cpu);

        ArrayList<Proceso> procesos = new ArrayList<>();

        procesos.add(new Proceso("P1", 100, 50));
        procesos.add(new Proceso("P2", 400, 0));
        procesos.add(new Proceso("P3", 50, 500));
        procesos.add(new Proceso("P4", 1000, 30));
        procesos.add(new Proceso("P5", 800, 10));

        Calendarizador c = new Calendarizador(procesos, despachador);
    }

}
