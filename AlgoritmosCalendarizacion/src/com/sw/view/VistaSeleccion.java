package com.sw.view;

import javax.swing.JButton;

/**
 *
 * @author HikingC7
 */
public class VistaSeleccion extends javax.swing.JFrame
{

    public VistaSeleccion()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        titile = new javax.swing.JLabel();
        rr = new javax.swing.JButton();
        srtf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Seleccione un algoritmo");
        setMaximumSize(new java.awt.Dimension(305, 225));
        setMinimumSize(new java.awt.Dimension(305, 225));
        setPreferredSize(new java.awt.Dimension(305, 225));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titile.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        titile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titile.setText("Algoritmo a simular");
        getContentPane().add(titile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 50));

        rr.setText("Round Robin");
        rr.setToolTipText("Simular el algoritmo RR.");
        rr.setActionCommand("rr");
        getContentPane().add(rr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 280, 50));

        srtf.setText("Short Remainig Time First");
        srtf.setToolTipText("Simular el algoritmo SRTF.");
        srtf.setActionCommand("srtf");
        getContentPane().add(srtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 280, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getRr()
    {
        return rr;
    }

    public JButton getSrtf()
    {
        return srtf;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton rr;
    private javax.swing.JButton srtf;
    private javax.swing.JLabel titile;
    // End of variables declaration//GEN-END:variables
}
