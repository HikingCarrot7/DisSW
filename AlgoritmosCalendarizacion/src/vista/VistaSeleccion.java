/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

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
        setMaximumSize(new java.awt.Dimension(320, 270));
        setMinimumSize(new java.awt.Dimension(320, 270));
        setPreferredSize(new java.awt.Dimension(320, 270));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titile.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        titile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titile.setText("Algoritmo a simular");
        getContentPane().add(titile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 50));

        rr.setText("Round Robin");
        rr.setActionCommand("rr");
        getContentPane().add(rr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 280, 50));

        srtf.setText("Short Remainig Time First");
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
