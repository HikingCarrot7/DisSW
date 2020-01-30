package controlador;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import vista.VistaPrincipal;

/**
 *
 * @author HikingCarrot7
 */
public class ControladorVistaPrincipal implements ActionListener, Observer
{

    private VistaPrincipal vista;
    private Esquema esquema;

    public ControladorVistaPrincipal(VistaPrincipal vista)
    {
        this.vista = vista;
        esquema = new Esquema();
        repintarEsquema();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    private void repintarEsquema()
    {
        Rectangle tamanio = vista.getEsquema().getBounds();
        System.out.println(tamanio.width);
        vista.getSoporteEsquema().removeAll();
        vista.setEsquema(new JInternalFrame("Representación de los procesos.", true));
        vista.getSoporteEsquema().add(vista.getEsquema(), JLayeredPane.DEFAULT_LAYER);
        vista.getEsquema().setBounds(tamanio);
        vista.getEsquema().setVisible(true);
        vista.getEsquema().setEnabled(false);
        vista.getEsquema().add(esquema, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        esquema.actualizarEsquema((String) arg);
    }

}
