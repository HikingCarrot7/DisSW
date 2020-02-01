package com.sw.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.sw.model.CPU;
import com.sw.model.Calendarizador;
import com.sw.model.Despachador;
import com.sw.model.DespachadorRR;
import com.sw.model.DespachadorSRTF;
import com.sw.model.Estado;
import com.sw.model.Proceso;
import com.sw.model.ProcesoRR;
import com.sw.model.ProcesoSRTF;
import com.sw.view.VistaPrincipal;
import com.sw.view.VistaSeleccion;

/**
 *
 * @author HikingC7
 */
public class ControladorSeleccion implements ActionListener
{

    private VistaSeleccion vistaSeleccion;

    public ControladorSeleccion(VistaSeleccion vistaSeleccion)
    {
        this.vistaSeleccion = vistaSeleccion;
        this.vistaSeleccion.getRr().addActionListener(this);
        this.vistaSeleccion.getSrtf().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        final ArrayList<Proceso> procesos = new ArrayList<>();
        final CPU cpu = new CPU();

        switch (e.getActionCommand())
        {
            case "srtf":
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
                    Despachador despachador = new DespachadorSRTF(cpu);
                    despachador.addObserver(control);
                    new Calendarizador(procesos, despachador);
                });
                break;

            case "rr":
                procesos.add(new ProcesoRR(Estado.NUEVO, "P1", 0, 101));
                procesos.add(new ProcesoRR(Estado.NUEVO, "P2", 1, 505));
                procesos.add(new ProcesoRR(Estado.NUEVO, "P3", 2, 732));
                procesos.add(new ProcesoRR(Estado.NUEVO, "P4", 3, 420));
                procesos.add(new ProcesoRR(Estado.NUEVO, "P5", 4, 516));
                procesos.add(new ProcesoRR(Estado.NUEVO, "P6", 5, 115));

                EventQueue.invokeLater(() ->
                {
                    VistaPrincipal vista = new VistaPrincipal();
                    vista.setVisible(true);
                    vista.setLocationRelativeTo(null);
                    ControladorVistaPrincipal control = new ControladorVistaPrincipal(vista);
                    Despachador despachador = new DespachadorRR(cpu, 50);
                    despachador.addObserver(control);
                    new Calendarizador(procesos, despachador);
                });

                break;

            default:
                throw new AssertionError();

        }

        vistaSeleccion.dispose();

    }

}
