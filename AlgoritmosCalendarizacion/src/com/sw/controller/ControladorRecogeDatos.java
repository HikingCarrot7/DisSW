package com.sw.controller;

import com.sw.exceptions.NombreNoValidoException;
import com.sw.exceptions.ValorNoValidoException;
import com.sw.view.VistaPrincipal;
import com.sw.view.VistaRecogeDatos;
import com.sw.view.VistaSeleccion;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author HikingCarrot7
 */
public class ControladorRecogeDatos implements ActionListener
{

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
        VISTA_RECOGE_DATOS.getContinuar().addActionListener(this);
        VISTA_RECOGE_DATOS.getAleatorio().addActionListener(this);
        VISTA_RECOGE_DATOS.getRegresar().addActionListener(this);
        VISTA_RECOGE_DATOS.getTablaRecogeDatos().setValueAt("P1", 0, 0);

        if (CLAVE_ALGORITMO_ACTUAL == ControladorSeleccion.CLAVE_ALGORITMO_RR)
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
                if (todosDatosValidos())
                    EventQueue.invokeLater(() ->
                    {
                        VistaPrincipal vistaPrincipal = new VistaPrincipal();
                        vistaPrincipal.setVisible(true);
                        vistaPrincipal.setLocationRelativeTo(null);
                        ControladorVistaPrincipal cvp = new ControladorVistaPrincipal(vistaPrincipal, CLAVE_ALGORITMO_ACTUAL);

                        if (CLAVE_ALGORITMO_ACTUAL == ControladorSeleccion.CLAVE_ALGORITMO_SRTF)
                            cvp.establecerDatosDefecto(VISTA_RECOGE_DATOS.getTablaRecogeDatos());

                        else
                            cvp.establecerDatosDefecto(
                                    VISTA_RECOGE_DATOS.getTablaRecogeDatos(),
                                    (int) VISTA_RECOGE_DATOS.getEntradaNQuantum().getValue());

                        VISTA_RECOGE_DATOS.dispose();
                    });

                break;

            case "regresar":
                if (confirmar("Confirmar acción", "Todos los datos insertados actualmente serán eliminados, ¿Continuar?"))
                    EventQueue.invokeLater(() ->
                    {
                        VistaSeleccion vistaSeleccion = new VistaSeleccion();
                        vistaSeleccion.setVisible(true);
                        vistaSeleccion.setLocationRelativeTo(null);
                        new ControladorSeleccion(vistaSeleccion);
                        VISTA_RECOGE_DATOS.dispose();
                    });

                break;

            case "aleatorios":
                if (confirmar("Confirmar acción", "Todos los datos insertados actualmente serán eliminados, ¿Continuar?"))
                {
                    arreglarNombresProcesos();
                    generarValoresTiempoAleatorios();
                }
                break;

            default:
                break;
        }

    }

    private boolean todosDatosValidos()
    {
        JTable tabla = VISTA_RECOGE_DATOS.getTablaRecogeDatos();

        if (tabla.isEditing())
            return false;

        tabla.clearSelection();

        try
        {
            if (TABLE_MANAGER.existeTabla(tabla))
            {
                nombresProcesosValidos();
                tiemposProcesosValidos();
                return true;

            } else
                mostrarError("Aún no hay datos", "Aún no existe una tabla", JOptionPane.ERROR_MESSAGE);

        } catch (NombreNoValidoException ex)
        {
            if (confirmar(
                    String.format("Error en la fila %s y columna %s", ex.getRow() + 1, ex.getCol() + 1),
                    ex.getMessage()))
            {
                arreglarNombresProcesos();
                return todosDatosValidos();
            }

        } catch (ValorNoValidoException ex)
        {
            mostrarError("Error en la entrada de los datos", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    private boolean nombresProcesosValidos()
    {
        Object[][] data = recogerDatos();

        for (int i = 0; i < data.length; i++)
        {
            String nombre = data[i][COL_NOMBRE_PROCESO].toString().trim();

            if (nombre.equals("") || nombre.equals(" "))
                throw new NombreNoValidoException(i, COL_NOMBRE_PROCESO);
        }

        return true;
    }

    private void arreglarNombresProcesos()
    {
        Object[][] data = recogerDatos();

        for (Object[] row : data)
        {
            String nombre = row[COL_NOMBRE_PROCESO].toString().trim();

            if (nombre.equals("") || nombre.equals(" "))
                row[COL_NOMBRE_PROCESO] = row[COL_NOMBRE_PROCESO - 1];
        }

        TABLE_MANAGER.rellenarTabla(VISTA_RECOGE_DATOS.getTablaRecogeDatos(), data);
    }

    private boolean tiemposProcesosValidos()
    {
        Object[][] data = recogerDatos();

        for (int i = 0; i < data.length; i++)
            for (int j = COL_TIEMPO_RAFAGA; j < data[i].length; j++)
                switch (j)
                {
                    case COL_TIEMPO_RAFAGA:
                        if (!esEntradaValida(data[i][j].toString(), REGEX_ENTERO_POSITIVO_VALIDO))
                            throw new ValorNoValidoException(
                                    String.format("El tiempo ráfaga en la fila %s y columna %s no es válido", i + 1, j + 1), i, j);
                        break;

                    case COL_TIEMPO_LLEGADA:
                        if (!esEntradaValida(data[i][j].toString(), REGEX_ENTERO_POSITIVO_VALIDO))
                            throw new ValorNoValidoException(
                                    String.format("El tiempo de llegada en la fila %s y columna %s no es válido", i + 1, j + 1), i, j);
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

        TABLE_MANAGER.limpiarTabla(tabla);
        rellenarTabla(Integer.parseInt(
                String.valueOf(VISTA_RECOGE_DATOS.getEntradaNProcesos().getValue())));
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

    private void generarValoresTiempoAleatorios()
    {
        final int MIN_VALUE_RAFAGA = 10;
        final int MAX_VALUE_RAFAGA = 8000;

        final int MIN_VALUE_LLEGADA = 5;
        final int MAX_VALUE_LLEGADA = 1000;

        SecureRandom rand = new SecureRandom();
        JTable table = VISTA_RECOGE_DATOS.getTablaRecogeDatos();
        Object[][] data = TABLE_MANAGER.obtenerDatosTabla(table);

        for (Object[] row : data)
        {
            int number = rand.nextInt(MAX_VALUE_RAFAGA - MIN_VALUE_RAFAGA) + MIN_VALUE_RAFAGA;
            row[COL_TIEMPO_RAFAGA] = number;

            if (CLAVE_ALGORITMO_ACTUAL == ControladorSeleccion.CLAVE_ALGORITMO_SRTF)
            {
                number = rand.nextInt(MAX_VALUE_LLEGADA - MIN_VALUE_LLEGADA) + MIN_VALUE_LLEGADA;
                row[COL_TIEMPO_LLEGADA] = number;
            }
        }

        TABLE_MANAGER.rellenarTabla(table, data);
    }

    private void anadirFila()
    {
        JTable tabla = VISTA_RECOGE_DATOS.getTablaRecogeDatos();
        Object[] newRow = TABLE_MANAGER.getEmptyRowData(obtenerColsTablaActual());

        newRow[0] = "P" + (tabla.getRowCount() + 1);
        VISTA_RECOGE_DATOS.getEntradaNProcesos().setValue(String.valueOf(tabla.getRowCount() + 1));
        TABLE_MANAGER.addRow(tabla, newRow);
    }

    private void eliminarFila()
    {
        JTable tabla = VISTA_RECOGE_DATOS.getTablaRecogeDatos();

        if (TABLE_MANAGER.existeTabla(tabla))
        {
            TABLE_MANAGER.eliminarUltimaFila(tabla);
            VISTA_RECOGE_DATOS.getEntradaNProcesos().setValue(
                    String.valueOf(tabla.getRowCount()));
        }
    }

    private void salvarTabla()
    {
        itemsSalvadosTabla = TABLE_MANAGER.obtenerDatosTabla(VISTA_RECOGE_DATOS.getTablaRecogeDatos());
    }

    private int obtenerColsTablaActual()
    {
        return CLAVE_ALGORITMO_ACTUAL == ControladorSeleccion.CLAVE_ALGORITMO_SRTF ? COLS_ALGORITMO_SRTF : COLS_ALGORITMO_RR;
    }

    private Object[][] recogerDatos()
    {
        return TABLE_MANAGER.obtenerDatosTabla(VISTA_RECOGE_DATOS.getTablaRecogeDatos());
    }

    private boolean existenItemsGuardados()
    {
        return itemsSalvadosTabla != null;
    }

    private void mostrarError(String titulo, String text, int tipo)
    {
        JOptionPane.showMessageDialog(VISTA_RECOGE_DATOS, text, titulo, tipo);
    }

    private boolean confirmar(String titulo, String text)
    {
        return JOptionPane.showConfirmDialog(VISTA_RECOGE_DATOS, text, titulo, JOptionPane.YES_NO_OPTION) == 0;
    }

    private boolean esEntradaValida(String text, String regex)
    {
        return text.matches(regex);
    }

}
