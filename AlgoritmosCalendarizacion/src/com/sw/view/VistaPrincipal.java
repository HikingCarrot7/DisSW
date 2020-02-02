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
        tablaEspera = new javax.swing.JTable();
        simulacion = new javax.swing.JButton();
        soporteTablaFinalizados = new javax.swing.JScrollPane();
        tablaProcesosFinalizados = new javax.swing.JTable();
        esquema = new java.awt.Canvas();
        regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelResumen.setMaximumSize(new java.awt.Dimension(220, 525));
        panelResumen.setMinimumSize(new java.awt.Dimension(220, 525));
        panelResumen.setPreferredSize(new java.awt.Dimension(220, 250));

        tablaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "ID", "Proceso", "Tiem. ráf.", "Tiem. lleg."
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

        tablaEspera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Proceso", "Tiem. Esp."
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
        tablaEspera.getTableHeader().setReorderingAllowed(false);
        panelLlegada.setViewportView(tablaEspera);
        if (tablaEspera.getColumnModel().getColumnCount() > 0)
        {
            tablaEspera.getColumnModel().getColumn(0).setResizable(false);
            tablaEspera.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(panelLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 35, -1, -1));

        simulacion.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        simulacion.setText("Iniciar simulación");
        simulacion.setActionCommand("iniciarSimulacion");
        simulacion.setMaximumSize(new java.awt.Dimension(155, 25));
        simulacion.setMinimumSize(new java.awt.Dimension(155, 25));
        simulacion.setPreferredSize(new java.awt.Dimension(155, 25));
        getContentPane().add(simulacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 5, -1, -1));

        soporteTablaFinalizados.setMaximumSize(new java.awt.Dimension(175, 210));
        soporteTablaFinalizados.setMinimumSize(new java.awt.Dimension(175, 210));
        soporteTablaFinalizados.setPreferredSize(new java.awt.Dimension(175, 210));

        tablaProcesosFinalizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Proceso", "Tiem. fin."
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        soporteTablaFinalizados.setViewportView(tablaProcesosFinalizados);
        if (tablaProcesosFinalizados.getColumnModel().getColumnCount() > 0)
        {
            tablaProcesosFinalizados.getColumnModel().getColumn(0).setResizable(false);
            tablaProcesosFinalizados.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(soporteTablaFinalizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 220, 270));

        esquema.setMaximumSize(new java.awt.Dimension(580, 525));
        esquema.setMinimumSize(new java.awt.Dimension(580, 525));
        esquema.setName(""); // NOI18N
        getContentPane().add(esquema, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 35, 580, 525));

        regresar.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        regresar.setText("<- Regresar");
        regresar.setActionCommand("regresar");
        getContentPane().add(regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, -1));

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

    public JTable getTablaEspera()
    {
        return tablaEspera;
    }

    public JTable getTablaResumen()
    {
        return tablaResumen;
    }

    public Canvas getEsquema()
    {
        return esquema;
    }

    public JButton getRegresar()
    {
        return regresar;
    }

    public JTable getTablaProcesosFinalizados()
    {
        return tablaProcesosFinalizados;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas esquema;
    private javax.swing.JScrollPane panelLlegada;
    private javax.swing.JScrollPane panelResumen;
    private javax.swing.JButton regresar;
    private javax.swing.JButton simulacion;
    private javax.swing.JScrollPane soporteTablaFinalizados;
    private javax.swing.JTable tablaEspera;
    private javax.swing.JTable tablaProcesosFinalizados;
    private javax.swing.JTable tablaResumen;
    // End of variables declaration//GEN-END:variables
}
