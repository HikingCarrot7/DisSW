package com.sw.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author HikingCarrot7
 */
public class TableCellRenderer extends DefaultTableCellRenderer
{

    private ArrayList<Point> puntos;

    public TableCellRenderer()
    {
        puntos = new ArrayList<>();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        System.out.println("hoals");

        if (existePuntoAt(row, column))
        {
            cell.setBackground(Color.RED);
            cell.setForeground(Color.WHITE);

        } else
        {
            cell.setBackground(Color.WHITE);
            cell.setForeground(Color.BLACK);
        }

        return cell;
    }

    public boolean existePuntoAt(int row, int col)
    {
        return puntos.stream().anyMatch((punto) -> (punto.x == col && punto.y == row));
    }

    public void anadirPunto(int row, int col)
    {
        puntos.add(new Point(col, row));
    }

    public void eliminarPunto(int row, int col)
    {
        puntos.removeIf(p -> p.x == col && p.y == row);
    }

    public void eliminarTodosLosPuntos()
    {
        puntos.clear();
    }

}
