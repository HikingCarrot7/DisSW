package pattern;

/**
 *
 * @author Nicolás
 */
public class Platillo extends ElementoMenu
{

    private double precio;

    public Platillo(String nombre)
    {
        super(nombre);
        precio = 10;
    }

    @Override
    public String toPrint(int espacios)
    {
        return String.format("%" + espacios + "s\n", getNombre() + ", $" + precio);
    }

}
