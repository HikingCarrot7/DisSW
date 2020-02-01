package com.sw.view;

import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author HikingCarrot7
 */
public class VistaPrincipal extends javax.swing.JFrame
{

    public VistaPrincipal()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelResumen = new javax.swing.JScrollPane();
        tablaResumen = new javax.swing.JTable();
        panelLlegada = new javax.swing.JScrollPane();
        tablaLlegada = new javax.swing.JTable();
        simulacion = new javax.swing.JButton();
        esquema = new java.awt.Canvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelResumen.setMaximumSize(new java.awt.Dimension(220, 525));
        panelResumen.setMinimumSize(new java.awt.Dimension(220, 525));
        panelResumen.setPreferredSize(new java.awt.Dimension(220, 525));

        tablaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "ID", "Nombre", "Tiem. ráf.", "Tiem. lleg."
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tablaResumen.getTableHeader().setReorderingAllowed(false);
        panelResumen.setViewportView(tablaResumen);
        if (tablaResumen.getColumnModel().getColumnCount() > 0)
        {
            tablaResumen.getColumnModel().getColumn(0).setResizable(false);
            tablaResumen.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaResumen.getColumnModel().getColumn(1).setResizable(false);
            tablaResumen.getColumnModel().getColumn(2).setResizable(false);
            tablaResumen.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(panelResumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, -1, -1));

        panelLlegada.setMaximumSize(new java.awt.Dimension(155, 525));
        panelLlegada.setMinimumSize(new java.awt.Dimension(155, 525));
        panelLlegada.setPreferredSize(new java.awt.Dimension(155, 525));

        tablaLlegada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String []
            {
                "Proceso", "Ejec. a los.."
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tablaLlegada.getTableHeader().setReorderingAllowed(false);
        panelLlegada.setViewportView(tablaLlegada);
        if (tablaLlegada.getColumnModel().getColumnCount() > 0)
        {
            tablaLlegada.getColumnModel().getColumn(0).setResizable(false);
            tablaLlegada.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(panelLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 35, -1, -1));

        simulacion.setText("Iniciar simulación.");
        simulacion.setMaximumSize(new java.awt.Dimension(155, 25));
        simulacion.setMinimumSize(new java.awt.Dimension(155, 25));
        simulacion.setPreferredSize(new java.awt.Dimension(155, 25));
        getContentPane().add(simulacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 5, -1, -1));

        esquema.setMaximumSize(new java.awt.Dimension(580, 525));
        esquema.setMinimumSize(new java.awt.Dimension(580, 525));
        esquema.setName(""); // NOI18N
        esquema.setPreferredSize(new java.awt.Dimension(580, 525));
        getContentPane().add(esquema, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 35, 580, 525));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JScrollPane getPanelResumen()
    {
        return panelResumen;
    }

    public JScrollPane getPanelLlegada()
    {
        return panelLlegada;
    }

    public JButton getSimulacion()
    {
        return simulacion;
    }

    public JTable getTablaLlegada()
    {
        return tablaLlegada;
    }

    public JTable getTablaResumen()
    {
        return tablaResumen;
    }

    public Canvas getEsquema()
    {
        return esquema;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas esquema;
    private javax.swing.JScrollPane panelLlegada;
    private javax.swing.JScrollPane panelResumen;
    private javax.swing.JButton simulacion;
    private javax.swing.JTable tablaLlegada;
    private javax.swing.JTable tablaResumen;
    // End of variables declaration//GEN-END:variables
}
