package pattern;

public abstract class Publicacion
{

    private String nombre;

    public Publicacion(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

}
