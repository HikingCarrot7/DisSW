package test;

import controlador.ControladorVistaPrincipal;
import java.awt.EventQueue;
import java.util.ArrayList;
import modelo.CPU;
import modelo.DespachadorRR;
import modelo.Estado;
import modelo.ProcesoRR;
import vista.VistaPrincipal;

/**
 *
 * @author HikingC7
 */
public class PruebaAlgoritmo
{

    public static void main(String[] args)
    {
        /*CPU cpu = new CPU();
        DespachadorSRTF despachador = new DespachadorSRTF(cpu);

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
        pcs.add(new ProcesoRR(Estado.NUEVO, "P2", 1, 500));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P3", 2, 700));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P4", 3, 400));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P5", 4, 500));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P6", 5, 110));

        DespachadorRR d = new DespachadorRR(cpu, 50);

        EventQueue.invokeLater(() ->
        {
            VistaPrincipal vista = new VistaPrincipal();
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            ControladorVistaPrincipal control = new ControladorVistaPrincipal(vista);
            d.addObserver(control);
            d.entregarProcesos(pcs);
        });

    }

}
