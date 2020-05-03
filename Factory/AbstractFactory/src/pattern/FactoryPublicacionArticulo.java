package pattern;

/**
 *
 * @author Nicolás
 */
public abstract class FactoryPublicacionArticulo
{

    public abstract Articulo createArticulo(String autor, String nombre);

    public abstract Publicacion createPublicacion(String nombre);
}
