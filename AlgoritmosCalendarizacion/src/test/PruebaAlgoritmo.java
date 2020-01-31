package test;

import controlador.ControladorVistaPrincipal;
import java.awt.EventQueue;
import java.util.ArrayList;
import modelo.CPU;
import modelo.Calendarizador;
import modelo.DespachadorSRTF;
import modelo.Estado;
import modelo.Proceso;
import modelo.ProcesoSRTF;
import vista.VistaPrincipal;

/**
 *
 * @author HikingC7
 */
public class PruebaAlgoritmo
{

    public static void main(String[] args)
    {
        CPU cpu = new CPU();
        ArrayList<Proceso> procesos = new ArrayList<>();

        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P1", 0, 400, 30));
        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P2", 1, 400, 0));
        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P3", 2, 500, 50));
        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P4", 3, 1000, 30));
        procesos.add(new ProcesoSRTF(Estado.NUEVO, "P5", 4, 800, 10));

        EventQueue.invokeLater(() ->
        {
            VistaPrincipal vista = new VistaPrincipal();
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            ControladorVistaPrincipal control = new ControladorVistaPrincipal(vista);
            DespachadorSRTF d = new DespachadorSRTF(cpu);
            d.addObserver(control);
            new Calendarizador(procesos, d);
        });

        /*CPU cpu = new CPU();
        ArrayList<ProcesoRR> pcs = new ArrayList<>();

        pcs.add(new ProcesoRR(Estado.NUEVO, "P1", 0, 101));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P2", 1, 505));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P3", 2, 732));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P4", 3, 420));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P5", 4, 516));
        pcs.add(new ProcesoRR(Estado.NUEVO, "P6", 5, 115));*/
        //DespachadorRR d = new DespachadorRR(cpu, 50);
    }

}
