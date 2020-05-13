package game;

/**
 *
 * @author Nicolás
 */
public class Cliente
{

    public static void main(String[] args)
    {
        Personaje p = new Personaje();
        Juego juego = new Juego();

        juego.setBotonA(new CommandCorrer(p));
        juego.setBotonB(new CommandBrincar(p));
        juego.setBotonX(new CommandDisparar(p));
        juego.setBotonY(new CommandInclinarse(p));

        juego.ejecutaComando(Boton.BOTON_A);
    }
}
