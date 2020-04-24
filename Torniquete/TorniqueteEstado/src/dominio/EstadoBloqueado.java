package dominio;

/**
 *
 * @author Nicolás
 */
public class EstadoBloqueado implements Estado
{

    @Override
    public void moneda(MEF mef)
    {
        mef.getDispositivo().desbloquear();
        mef.setEstadoActual(ESTADO_DESBLOQUEADO);
    }

    @Override
    public void pasar(MEF mef)
    {
        mef.getDispositivo().alarma();
        mef.setEstadoActual(ESTADO_VIOLACION);
    }

}
