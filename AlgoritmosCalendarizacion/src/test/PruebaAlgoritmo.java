package test;

import java.util.ArrayList;
import modelo.CPU;
import modelo.DespachadorRR;
import modelo.Estado;
import modelo.ProcesoRR;

/**
 *
 * @author HikingC7
 */
public class PruebaAlgoritmo
{

    public static void main(String[] args)
    {
        /*DespachadorSRTF despachador = new DespachadorSRTF(cpu);

        ArrayList<ProcesoSRTF> procesos = new ArrayList<>();

        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P1", 0, 400, 30));
        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P2", 1, 400, 0));
        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P3", 2, 500, 50));
        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P4", 3, 1000, 30));
        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P5", 4, 800, 10));

        new Calendarizador(procesos, despachador);*/

        CPU cpu = new CPU();
        ArrayList<ProcesoRR> pcs = new ArrayList<>();

        pcs.add(new ProcesoRR(Estado.NUEVO, "P1", 0, 100));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P2", 1, 50));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P3", 2, 200));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P4", 3, 20));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P5", 4, 0));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P6", 5, 900));

        DespachadorRR d = new DespachadorRR(cpu, pcs, 80);

    }

}
