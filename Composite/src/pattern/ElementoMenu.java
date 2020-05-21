package pattern;

/**
 *
 * @author Nicolás
 */
public abstract class ElementoMenu
{

    private String nombre;

    public ElementoMenu(String nombre)
    {
        this.nombre = nombre;
    }

    public abstract String toPrint(int espacios);

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

}
