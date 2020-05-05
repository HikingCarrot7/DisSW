package pattern;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nicolás
 */
public class CorreoBuilder implements Builder
{

    private String titulo;
    private final Set<String> destinatarios;
    private String saludo;
    private String texto;
    private String despedida;

    public CorreoBuilder()
    {
        destinatarios = new HashSet<>();
    }

    public CorreoBuilder addTitle(String titulo)
    {
        this.titulo = titulo;
        return this;
    }

    public CorreoBuilder addDestinatario(String destinatario)
    {
        destinatarios.add(destinatario);
        return this;
    }

    public CorreoBuilder addSaludo(String saludo)
    {
        this.saludo = saludo;
        return this;
    }

    public CorreoBuilder addTexto(String texto)
    {
        this.texto = texto;
        return this;
    }

    public CorreoBuilder addDespedida(String despedida)
    {
        this.despedida = despedida;
        return this;
    }

    @Override
    public Correo build()
    {
        return new Correo(String.format("%s\n%s", titulo, saludo),
                destinatarios.stream().reduce("", (t, u) -> t + "\n" + u),
                String.format("%s\n%s", texto, despedida));
    }

}
