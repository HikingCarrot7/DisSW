package pattern;

/**
 *
 * @author Nicolás
 */
public abstract class FactoryPublicacionArticulo
{

    public abstract Articulo createArticulo(String autor, String titulo);

    public abstract Publicacion createPublicacion(String nombre);
}
