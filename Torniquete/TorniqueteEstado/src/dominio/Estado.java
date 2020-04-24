package dominio;

/**
 *
 * @author Nicolás
 */
public interface Estado
{

    public Estado ESTADO_BLOQUEADO = new EstadoBloqueado();
    public Estado ESTADO_DESBLOQUEADO = new EstadoDesbloqueado();
    public Estado ESTADO_VIOLACION = new EstadoViolacion();

    public void moneda(MEF mef);

    public void pasar(MEF mef);
}
