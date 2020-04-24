package dominio;

/**
 *
 * @author Nicolás
 */
public class EstadoDesbloqueado implements Estado
{

    @Override
    public void moneda(MEF mef)
    {
        mef.getDispositivo().gracias();
    }

    @Override
    public void pasar(MEF mef)
    {
        mef.getDispositivo().bloquear();
        mef.setEstadoActual(ESTADO_BLOQUEADO);
    }

}
