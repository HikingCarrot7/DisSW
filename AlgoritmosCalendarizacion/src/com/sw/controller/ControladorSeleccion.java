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
                break;

            default:
                throw new AssertionError();
        }

        VISTA_SELECCION.dispose();
    }

}
