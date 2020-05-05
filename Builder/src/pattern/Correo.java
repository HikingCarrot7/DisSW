package pattern;

public class Correo
{

    private final String titulo;
    private final String destinatarios;
    private final String mensaje;

    public Correo(String titulo, String destinatarios, String mensaje)
    {
        this.titulo = titulo;
        this.destinatarios = destinatarios;
        this.mensaje = mensaje;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getDestinatarios()
    {
        return destinatarios;
    }

    public String getMensaje()
    {
        return mensaje;
    }

    @Override
    public String toString()
    {
        return getDestinatarios() + "\n" + getTitulo() + "\n" + getMensaje();
    }

}
