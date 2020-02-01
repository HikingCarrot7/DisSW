package com.sw.controller;

import com.sw.view.DibujadorEsquema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import com.sw.model.Notificacion;
import com.sw.view.VistaPrincipal;

/**
 *
 * @author HikingCarrot7
 */
public class ControladorVistaPrincipal implements ActionListener, Observer
{

    private VistaPrincipal vista;
    private final DibujadorEsquema DIBUJADOR_ESQUEMA;

    public ControladorVistaPrincipal(VistaPrincipal vista)
    {
        this.vista = vista;
        DIBUJADOR_ESQUEMA = new DibujadorEsquema(vista.getEsquema());
        initEsquema();
    }

    private void initEsquema()
    {
        DIBUJADOR_ESQUEMA.crearRenderer();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    @Override
    public void update(Observable o, Object arg)
    {
        Notificacion notificacion = (Notificacion) arg;

        switch (notificacion.getIdentificador())
        {
            case Notificacion.PROCESO_HA_FINALIZADO:
                DIBUJADOR_ESQUEMA.mostrarEnProcesadorProcesoActual(
                        notificacion.getProceso().obtenerCopiaProceso(),
                        notificacion.getTiempoUsoCpu());

                DIBUJADOR_ESQUEMA.actualizarDiagramaGantt(
                        notificacion.getProceso().obtenerCopiaProceso(),
                        notificacion.getTiempoTranscurrido());
                break;

            case Notificacion.PROCESO_ENTRO_CPU:
                DIBUJADOR_ESQUEMA.mostrarEnProcesadorProcesoActual(
                        notificacion.getProceso().obtenerCopiaProceso(),
                        notificacion.getTiempoUsoCpu());
                break;

            case Notificacion.PROCESO_DEJO_CPU:
                DIBUJADOR_ESQUEMA.actualizarDiagramaGantt(
                        notificacion.getProceso().obtenerCopiaProceso(),
                        notificacion.getTiempoTranscurrido());
                break;

            default:
                break;
        }

    }

}
