package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import modelo.Notificacion;
import vista.VistaPrincipal;

/**
 *
 * @author HikingCarrot7
 */
public class ControladorVistaPrincipal implements ActionListener, Observer
{

    private VistaPrincipal vista;
    private DibujadorEsquema dibujadorEsquema;

    public ControladorVistaPrincipal(VistaPrincipal vista)
    {
        this.vista = vista;
        dibujadorEsquema = new DibujadorEsquema(vista.getEsquema());
        initEsquema();
    }

    private void initEsquema()
    {
        new Ticker(dibujadorEsquema);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (arg instanceof Notificacion)
        {
            Notificacion notificacion = (Notificacion) arg;

            switch (notificacion.getIdentificador())
            {
                case PROCESO_ENTRO_CPU:
                    dibujadorEsquema.dibujarProcesoActual(notificacion.getProceso(),
                            notificacion.getTiempoUsoCpu());
                    break;

                case PROCESO_DEJO_CPU:
                    dibujadorEsquema.dibujarProcesoActual(null, 0);
                    break;

                default:
                    throw new AssertionError();
            }
        }

    }

}
