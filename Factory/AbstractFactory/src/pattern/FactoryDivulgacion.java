package pattern;

/**
 *
 * @author Nicolás
 */
public class FactoryDivulgacion extends FactoryPublicacionArticulo
{

    @Override
    public Articulo createArticulo(String autor, String nombre)
    {
        return new ArticuloDivulgacion(autor, nombre);
    }

    @Override
    public Publicacion createPublicacion(String nombre)
    {
        return new Blog(nombre);
    }

}
