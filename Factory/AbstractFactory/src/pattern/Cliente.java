package pattern;

public class Cliente
{

    private Articulo art;
    private Publicacion pub;

    public Cliente(String tipoArticulo, String autor, String tituloArticulo, String tituloRevista, FactoryPublicacionArticulo factory)
    {
        art = factory.createArticulo(autor, tipoArticulo);
        pub = factory.createPublicacion(tituloRevista);
    }

    public static void main(String args[])
    {
        new Cliente("Cientifico", "woods", "Foreign Forever", "Journal Migration", new FactoryCientifico());
        new Cliente("Cientifico", "woods", "Foreign Forever", "Journal Migration", new FactoryDivulgacion());
    }

}
