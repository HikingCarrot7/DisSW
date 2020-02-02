package com.sw.controller;

import com.sw.model.CPU;
import com.sw.model.Calendarizador;
import com.sw.model.Despachador;
import com.sw.model.DespachadorRR;
import com.sw.model.DespachadorSRTF;
import com.sw.model.Estado;
import com.sw.model.Notificacion;
import com.sw.model.Proceso;
import com.sw.model.ProcesoRR;
import com.sw.model.ProcesoSRTF;
import com.sw.view.DibujadorEsquema;
import com.sw.view.VistaPrincipal;
import com.sw.view.VistaRecogeDatos;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private DibujadorEsquema DIBUJADOR_ESQUEMA;
    private final TableManager TABLE_MANAGER;
    private Despachador despachador;
    private long QUANTUMS;

    private final int CLAVE_ALGORITMO_ACTUAL;

    public ControladorVistaPrincipal(final VistaPrincipal VISTA_PRINCIPAL, final int CLAVE_ALGORITMO_ACTUAL)
    {
        this.VISTA_PRINCIPAL = VISTA_PRINCIPAL;
        this.CLAVE_ALGORITMO_ACTUAL = CLAVE_ALGORITMO_ACTUAL;
        DIBUJADOR_ESQUEMA = new DibujadorEsquema(VISTA_PRINCIPAL.getEsquema());
        TABLE_MANAGER = new TableManager();
        initMyComponents();
    }

    private void initMyComponents()
    {
        VISTA_PRINCIPAL.getRegresar().addActionListener(this);
        VISTA_PRINCIPAL.getSimulacion().addActionListener(this);
        initEsquema();
    }

    public void establecerDatosDefecto(JTable table)
    {
        TABLE_MANAGER.copiarTablas(table, VISTA_PRINCIPAL.getTablaResumen());
        VISTA_PRINCIPAL.setTitle("Simulando el algoritmo SRTF");
    }

    public void establecerDatosDefecto(JTable table, final long QUANTUMS)
    {
        TABLE_MANAGER.eliminarUltimaColumna(VISTA_PRINCIPAL.getTablaResumen());
        TABLE_MANAGER.copiarTablas(table, VISTA_PRINCIPAL.getTablaResumen());
        VISTA_PRINCIPAL.setTitle("Simulando el algoritmo Round Robin");
        this.QUANTUMS = QUANTUMS;
    }

    private void initEsquema()
    {
        DIBUJADOR_ESQUEMA.crearRenderer();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "regresar":
                EventQueue.invokeLater(() ->
                {
                    VistaRecogeDatos vistaRecogeDatos = new VistaRecogeDatos();
                    vistaRecogeDatos.setVisible(true);
                    vistaRecogeDatos.setLocationRelativeTo(null);
                    ControladorRecogeDatos controladorRecogeDatos = new ControladorRecogeDatos(vistaRecogeDatos, CLAVE_ALGORITMO_ACTUAL);

                    if (CLAVE_ALGORITMO_ACTUAL == ControladorSeleccion.CLAVE_ALGORITMO_SRTF)
                        controladorRecogeDatos.establecerDatosDefecto(VISTA_PRINCIPAL.getTablaResumen());

                    else
                        controladorRecogeDatos.establecerDatosDefecto(VISTA_PRINCIPAL.getTablaResumen(), QUANTUMS);

                    if (despachador != null)
                        despachador.detenerDespachador();

                    DIBUJADOR_ESQUEMA.destroyRenderer();
                    VISTA_PRINCIPAL.dispose();
                });
                break;

            case "iniciarSimulacion":
                crearSimulacion();
                break;

            default:
                break;
        }
    }

    private void crearSimulacion()
    {
        final CPU CPU = new CPU();
        despachador = null;
        ArrayList<Proceso> procesos = null;

        switch (CLAVE_ALGORITMO_ACTUAL)
        {
            case ControladorSeleccion.CLAVE_ALGORITMO_SRTF:
                procesos = obtenerProcesosSRTF();
                despachador = new DespachadorSRTF(CPU);
                break;
            case ControladorSeleccion.CLAVE_ALGORITMO_RR:
                procesos = obtenerProcesosRR();
                despachador = new DespachadorRR(CPU, QUANTUMS);
                break;
            default:
                throw new AssertionError();
        }

        despachador.addObserver(this); // Nunca será null.
        new Calendarizador(procesos, despachador);
    }

    private ArrayList<Proceso> obtenerProcesosSRTF()
    {
        ArrayList<Proceso> procesos = new ArrayList<>();
        JTable table = VISTA_PRINCIPAL.getTablaResumen();
        Object[][] data = TABLE_MANAGER.obtenerDatosTabla(table);

        for (int i = 0; i < table.getRowCount(); i++)
            procesos.add(new ProcesoSRTF(
                    Estado.NUEVO,
                    data[i][ControladorRecogeDatos.COL_NOMBRE_PROCESO].toString(),
                    (i + 1),
                    Long.parseLong(data[i][ControladorRecogeDatos.COL_TIEMPO_RAFAGA].toString()),
                    Long.parseLong(data[i][ControladorRecogeDatos.COL_TIEMPO_LLEGADA].toString())));

        return procesos;
    }

    private ArrayList<Proceso> obtenerProcesosRR()
    {
        ArrayList<Proceso> procesos = new ArrayList<>();
        JTable table = VISTA_PRINCIPAL.getTablaResumen();
        Object[][] data = TABLE_MANAGER.obtenerDatosTabla(table);

        for (int i = 0; i < table.getRowCount(); i++)
            procesos.add(new ProcesoRR(
                    Estado.NUEVO,
                    data[i][ControladorRecogeDatos.COL_NOMBRE_PROCESO].toString(),
                    (i + 1),
                    Long.parseLong(data[i][ControladorRecogeDatos.COL_TIEMPO_RAFAGA].toString())));

        return procesos;
    }

    private void anadirProcesoTablaTiempoEspera(Proceso proceso, long tiempoTranscurrido)
    {
        TABLE_MANAGER.addRow(VISTA_PRINCIPAL.getTablaEspera(), new Object[]
        {
            proceso.getIdentificador(), tiempoTranscurrido
        });
    }

    private void anadirProcesoTablaFinalizados(Proceso proceso, long tiempoEnQueProcesoFinalizo)
    {
        TABLE_MANAGER.addRow(VISTA_PRINCIPAL.getTablaProcesosFinalizados(), new Object[]
        {
            proceso.getIdentificador(), tiempoEnQueProcesoFinalizo
        });
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Notificacion notificacion = (Notificacion) arg;

        switch (notificacion.getIdentificador())
        {
            case Notificacion.PROCESO_HA_FINALIZADO:

                anadirProcesoTablaFinalizados(notificacion.getProceso(),
                        notificacion.getTiempoEnQueFinalizoProceso());

                anadirProcesoTablaTiempoEspera(notificacion.getProceso(),
                        notificacion.getTiempoEsperaProceso());

                DIBUJADOR_ESQUEMA.mostrarEnProcesadorProcesoActual(
                        notificacion.getProceso().obtenerCopiaProceso(),
                        notificacion.getTiempoUsoCPU());

                DIBUJADOR_ESQUEMA.actualizarDiagramaGantt(
                        notificacion.getProceso().obtenerCopiaProceso(),
                        notificacion.getTiempoEsperaProceso());
                break;

            case Notificacion.PROCESO_ENTRO_CPU:
                DIBUJADOR_ESQUEMA.mostrarEnProcesadorProcesoActual(
                        notificacion.getProceso().obtenerCopiaProceso(),
                        notificacion.getTiempoUsoCPU());
                break;

            case Notificacion.PROCESO_DEJO_CPU:

                anadirProcesoTablaTiempoEspera(notificacion.getProceso(),
                        notificacion.getTiempoEsperaProceso());

                DIBUJADOR_ESQUEMA.actualizarDiagramaGantt(
                        notificacion.getProceso().obtenerCopiaProceso(),
                        notificacion.getTiempoEsperaProceso());
                break;

            default:
                break;
        }

    }

}
