package pattern;

public class Cliente
{

    public void crearCorreo()
    {
        String destinatario = "gortiz@uady.mx";
        String destinatario2 = "jgarcila@yahoo.com";
        String saludo = "Hola Juan";
        String texto = "Favor de calificar las ADA's de Diseño";
        String despedida = "Saludos y Gracias";
        String tituloCorreo = "Seguimiento a Diseño Software";

        CorreoBuilder builder = new CorreoBuilder();
        Correo email = builder.addDestinatario(destinatario)
                .addDestinatario(destinatario2)
                .addTitle(tituloCorreo)
                .addSaludo(saludo)
                .addTexto(texto)
                .addDespedida(despedida).build();

        System.out.println(email);
    }

    public static void main(String[] args)
    {
        new Cliente().crearCorreo();
    }

}
