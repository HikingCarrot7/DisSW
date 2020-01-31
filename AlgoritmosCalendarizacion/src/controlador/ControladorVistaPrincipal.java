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
    private final DibujadorEsquema DIBUJADOR_ESQUEMA;

    public ControladorVistaPrincipal(VistaPrincipal vista)
    {
        this.vista = vista;
        DIBUJADOR_ESQUEMA = new DibujadorEsquema(vista.getEsquema());
        initEsquema();
    }

    private void initEsquema()
    {
        new Ticker(DIBUJADOR_ESQUEMA);
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
                case Notificacion.PROCESO_DEJO_CPU:
                case Notificacion.PROCESO_ENTRO_CPU:
                    DIBUJADOR_ESQUEMA.dibujarProcesoActual(notificacion.getProceso(),
                            notificacion.getTiempoUsoCpu(), notificacion.getTiempoTranscurrido());
                    break;

                default:
                    throw new AssertionError();
            }
        }

    }

}
