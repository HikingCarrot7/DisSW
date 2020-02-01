package com.sw.controller;

import com.sw.view.VistaRecogeDatos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

/**
 *
 * @author HikingCarrot7
 */
public class ControladorRecogeDatos implements ActionListener
{

    public static final int CLAVE_ALGORITMO_SRTF = 0;
    public static final int CLAVE_ALGORITMO_RR = 1;
    private final int CLAVE_ALGORITMO_ACTUAL;

    private final VistaRecogeDatos VISTA_RECOGE_DATOS;
    private final TableManager TABLE_MANAGER;
    private final String REGEX_ENTERO_POSITIVO_VALIDO = "^[0-9]+$";

    private final int COLS_ALGORITMO_RR = 3;
    private final int COLS_ALGORITMO_SRTF = 4;

    public ControladorRecogeDatos(VistaRecogeDatos vistaRecogeDatos, final int CLAVE_ALGORITMO_ACTUAL)
    {
        this.VISTA_RECOGE_DATOS = vistaRecogeDatos;
        this.CLAVE_ALGORITMO_ACTUAL = CLAVE_ALGORITMO_ACTUAL;
        this.TABLE_MANAGER = new TableManager();
        initMyComponents();
    }

    private void initMyComponents()
    {
        VISTA_RECOGE_DATOS.getAceptarNProcesos().addActionListener(this);

        if (CLAVE_ALGORITMO_ACTUAL == CLAVE_ALGORITMO_RR)
            TABLE_MANAGER.eliminarUltimaColumna(VISTA_RECOGE_DATOS.getTablaRecogeDatos());
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "aceptar":
                prepararTabla();
                break;

            case "continuar":

            default:
                break;
        }

    }

    private void prepararTabla()
    {
        JTable tabla = VISTA_RECOGE_DATOS.getTablaRecogeDatos();
        String entradaNProcesos = VISTA_RECOGE_DATOS.getEntradaNProcesos().getText();

        if (esEntradaValida(entradaNProcesos, REGEX_ENTERO_POSITIVO_VALIDO))
        {
            TABLE_MANAGER.limpiarTabla(tabla);
            rellenarTabla(Integer.parseInt(entradaNProcesos));
        }

    }

    private void rellenarTabla(int rows)
    {
        for (int i = 0; i < rows; i++)
        {
            Object[] rowData = TABLE_MANAGER.getEmptyRowData(obtenerColsTablaActual());
            rowData[0] = "P" + (i + 1);
            TABLE_MANAGER.addRow(VISTA_RECOGE_DATOS.getTablaRecogeDatos(), rowData);
        }
    }

    private Object[][] recogerDatos()
    {
        return TABLE_MANAGER.obtenerDatosTabla(VISTA_RECOGE_DATOS.getTablaRecogeDatos());
    }

    private int obtenerColsTablaActual()
    {
        return CLAVE_ALGORITMO_ACTUAL == CLAVE_ALGORITMO_SRTF ? COLS_ALGORITMO_SRTF : COLS_ALGORITMO_RR;
    }

    private boolean esEntradaValida(String text, String regex)
    {
        return text.matches(regex);
    }

}
