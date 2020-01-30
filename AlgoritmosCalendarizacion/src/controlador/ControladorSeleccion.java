package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaSeleccion;

/**
 *
 * @author HikingC7
 */
public class ControladorSeleccion implements ActionListener
{

    private VistaSeleccion vistaSeleccion;

    public ControladorSeleccion(VistaSeleccion vistaSeleccion)
    {
        this.vistaSeleccion = vistaSeleccion;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "srtf":

                break;

            case "rr":

                break;

            default:
                throw new AssertionError();

        }

    }

}
