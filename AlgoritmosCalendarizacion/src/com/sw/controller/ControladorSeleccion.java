package com.sw.controller;

import com.sw.view.VistaRecogeDatos;
import com.sw.view.VistaSeleccion;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author HikingC7
 */
public class ControladorSeleccion implements ActionListener
{

    public static final int CLAVE_ALGORITMO_SRTF = 0;
    public static final int CLAVE_ALGORITMO_RR = 1;

    private final VistaSeleccion VISTA_SELECCION;

    public ControladorSeleccion(VistaSeleccion vistaSeleccion)
    {
        this.VISTA_SELECCION = vistaSeleccion;
        initMyComponents();
    }

    private void initMyComponents()
    {
        VISTA_SELECCION.getRr().addActionListener(this);
        VISTA_SELECCION.getSrtf().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //final ArrayList<Proceso> procesos = new ArrayList<>();
        //final CPU cpu = new CPU();

        switch (e.getActionCommand())
        {
            case "srtf":

                EventQueue.invokeLater(() ->
                {
                    VistaRecogeDatos vistaRecogeDatos = new VistaRecogeDatos();
                    vistaRecogeDatos.setVisible(true);
                    vistaRecogeDatos.setLocationRelativeTo(null);
                    vistaRecogeDatos.setTitle("Preparando datos para simular el algoritmo SRTF");
                    vistaRecogeDatos.getEntradaNQuantum().setVisible(false);
                    vistaRecogeDatos.getEntradaValidaLabel().setVisible(false);
                    vistaRecogeDatos.getLabelQuantum().setVisible(false);
                    new ControladorRecogeDatos(vistaRecogeDatos, CLAVE_ALGORITMO_SRTF);
                });

                /*procesos.add(new ProcesoSRTF(Estado.NUEVO, "P1", 0, 400, 30));
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
                });*/
                break;

            case "rr":

                EventQueue.invokeLater(() ->
                {
                    VistaRecogeDatos vistaRecogeDatos = new VistaRecogeDatos();
                    vistaRecogeDatos.setVisible(true);
                    vistaRecogeDatos.setLocationRelativeTo(null);
                    vistaRecogeDatos.setTitle("Preparando datos para simular el algoritmo Round Robin");
                    new ControladorRecogeDatos(vistaRecogeDatos, CLAVE_ALGORITMO_RR);
                });

                /*procesos.add(new ProcesoRR(Estado.NUEVO, "P1", 0, 101));
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
                });*/
                break;

            default:
                throw new AssertionError();

        }

        VISTA_SELECCION.dispose();

    }

}
