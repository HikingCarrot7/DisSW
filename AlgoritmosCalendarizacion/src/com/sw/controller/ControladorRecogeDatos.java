package com.sw.controller;

import com.sw.exceptions.NombreNoValido;
import com.sw.exceptions.ValorNoValido;
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

    private final int COL_NOMBRE_PROCESO = 1;
    private final int COL_TIEMPO_RAFAGA = 2;
    private final int COL_TIEMPO_LLEGADA = 3;

    private Object[][] itemsSalvadosTabla;

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
        VISTA_RECOGE_DATOS.getAumentarFila().addActionListener(this);
        VISTA_RECOGE_DATOS.getEliminaFila().addActionListener(this);

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
                procesarDatos();
                break;

            case "anadirFila":
                anadirFila();
                break;

            case "eliminarFila":
                eliminarFila();
                break;

            case "regresar":

                break;

            default:
                break;
        }

    }

    private void procesarDatos()
    {
        try
        {

            todosDatosValidos();

        } catch (NombreNoValido ex)
        {

        } catch (ValorNoValido ex)
        {

        }

    }

    private boolean todosDatosValidos()
    {
        Object[][] data = recogerDatos();

        for (int i = 0; i < data.length; i++)
            for (int j = 1; j < data[0].length; j++)
                switch (j)
                {
                    case COL_NOMBRE_PROCESO:
                        String nombre = data[i][j].toString().trim();
                        if (nombre.equals("") || nombre.equals(" "))
                            throw new NombreNoValido(i, j);
                        break;
                    case COL_TIEMPO_RAFAGA:
                        if (!esEntradaValida(data[i][j].toString(), REGEX_ENTERO_POSITIVO_VALIDO))
                            throw new ValorNoValido(String.format("El tiempo ráfaga en la fila %s y columna %s no es válido", i, j), i, j);
                        break;

                    case COL_TIEMPO_LLEGADA:
                        if (!esEntradaValida(data[i][j].toString(), REGEX_ENTERO_POSITIVO_VALIDO))
                            throw new ValorNoValido(String.format("El tiempo de llegada en la fila %s y columna %s no es válido", i, j), i, j);
                        break;

                    default:
                        break;
                }

        return true;
    }

    private void prepararTabla()
    {

        JTable tabla = VISTA_RECOGE_DATOS.getTablaRecogeDatos();

        if (TABLE_MANAGER.existeTabla(tabla))
            salvarTabla();

        String entradaNProcesos = VISTA_RECOGE_DATOS.getEntradaNProcesos().getText();

        if (esEntradaValida(entradaNProcesos, REGEX_ENTERO_POSITIVO_VALIDO))
        {
            TABLE_MANAGER.limpiarTabla(tabla);
            rellenarTabla(Integer.parseInt(entradaNProcesos));
        }

    }

    private void rellenarTabla(int rows)
    {
        Object[] rowData;

        for (int i = 0; i < rows; i++)
        {
            if (existenItemsGuardados() && itemsSalvadosTabla.length > i)
                rowData = itemsSalvadosTabla[i];

            else
                rowData = TABLE_MANAGER.getEmptyRowData(obtenerColsTablaActual());

            rowData[0] = "P" + (i + 1);
            TABLE_MANAGER.addRow(VISTA_RECOGE_DATOS.getTablaRecogeDatos(), rowData);
        }

        itemsSalvadosTabla = null;
    }

    private void anadirFila()
    {
        JTable tabla = VISTA_RECOGE_DATOS.getTablaRecogeDatos();
        Object[] newRow = TABLE_MANAGER.getEmptyRowData(obtenerColsTablaActual());

        newRow[0] = "P" + (tabla.getRowCount() + 1);
        VISTA_RECOGE_DATOS.getEntradaNProcesos().setText(String.valueOf(tabla.getRowCount() + 1));
        TABLE_MANAGER.addRow(tabla, newRow);
    }

    private void eliminarFila()
    {
        JTable tabla = VISTA_RECOGE_DATOS.getTablaRecogeDatos();

        if (TABLE_MANAGER.existeTabla(tabla))
        {
            TABLE_MANAGER.eliminarUltimaFila(tabla);
            VISTA_RECOGE_DATOS.getEntradaNProcesos().setText(
                    String.valueOf(tabla.getRowCount() == 0 ? "" : tabla.getRowCount()));
        }
    }

    private void salvarTabla()
    {
        itemsSalvadosTabla = TABLE_MANAGER.obtenerDatosTabla(VISTA_RECOGE_DATOS.getTablaRecogeDatos());
    }

    private int obtenerColsTablaActual()
    {
        return CLAVE_ALGORITMO_ACTUAL == CLAVE_ALGORITMO_SRTF ? COLS_ALGORITMO_SRTF : COLS_ALGORITMO_RR;
    }

    private Object[][] recogerDatos()
    {
        return TABLE_MANAGER.obtenerDatosTabla(VISTA_RECOGE_DATOS.getTablaRecogeDatos());
    }

    private boolean existenItemsGuardados()
    {
        return itemsSalvadosTabla != null;
    }

    private boolean esEntradaValida(String text, String regex)
    {
        return text.matches(regex);
    }

}
