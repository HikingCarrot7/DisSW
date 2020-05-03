package test;

/**
 *
 * @author Nicolás
 */
public abstract class AlgoEncriptamiento
{

    public abstract void configurar();

    public abstract String encriptar(String texto);

    public abstract String desencriptar(String texto);
}
