package com.sw.controller;

import com.sw.model.Notificacion;
import com.sw.view.DibujadorEsquema;
import com.sw.view.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;

/**
 *
 * @author HikingCarrot7
 */
public class ControladorVistaPrincipal implements ActionListener, Observer
{

    private final VistaPrincipal VISTA_PRINCIPAL;
    private final DibujadorEsquema DIBUJADOR_ESQUEMA;
    private final TableManager TABLE_MANAGER;

    private final int CLAVE_ALGORITMO_ACTUAL;

    public ControladorVistaPrincipal(final VistaPrincipal VISTA_PRINCIPAL, final int CLAVE_ALGORITMO_ACTUAL)
    {
        this.VISTA_PRINCIPAL = VISTA_PRINCIPAL;
        this.CLAVE_ALGORITMO_ACTUAL = CLAVE_ALGORITMO_ACTUAL;
        DIBUJADOR_ESQUEMA = new DibujadorEsquema(VISTA_PRINCIPAL.getEsquema());
        TABLE_MANAGER = new TableManager();
        initEsquema();
    }

    public void establecerDatosDefecto(JTable table)
    {

    }

    public void establecerDatosDefecto(JTable table, final long QUANTUMS)
    {
        TABLE_MANAGER.eliminarUltimaColumna(VISTA_PRINCIPAL.getTablaResumen());
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
