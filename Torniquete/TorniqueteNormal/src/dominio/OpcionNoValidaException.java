package dominio;

/**
 *
 * @author Nicolás
 */
public class OpcionNoValidaException extends RuntimeException
{

    public OpcionNoValidaException()
    {
        super("La opción no es válida.");
    }

}
