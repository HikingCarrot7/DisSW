package pattern;

public class Articulo
{

    private String autor;
    private String titulo;

    public Articulo(String autor, String titulo)
    {
        this.autor = autor;
        this.titulo = titulo;
    }

    public String getAutor()
    {
        return autor;
    }

    public void setAutor(String autor)
    {
        this.autor = autor;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

}
