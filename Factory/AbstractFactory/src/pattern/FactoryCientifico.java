package pattern;

/**
 *
 * @author Nicolás
 */
public class FactoryCientifico extends FactoryPublicacionArticulo
{

    @Override
    public Articulo createArticulo(String autor, String nombre)
    {
        return new ArticuloCientifico(autor, nombre);
    }

    @Override
    public Publicacion createPublicacion(String nombre)
    {
        return new RevistaCientifica(nombre);
    }

}
