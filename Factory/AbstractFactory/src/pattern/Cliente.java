package pattern;

public class Cliente
{

    private final Articulo art;
    private final Publicacion pub;

    public Cliente(String tipoArticulo, String autor, String tituloArticulo, String tituloRevista, FactoryPublicacionArticulo factory)
    {
        art = factory.createArticulo(autor, tipoArticulo);
        pub = factory.createPublicacion(tituloRevista);
    }

    public Articulo getArt()
    {
        return art;
    }

    public Publicacion getPub()
    {
        return pub;
    }

}
