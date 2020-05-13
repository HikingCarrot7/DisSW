package game;

/**
 *
 * @author Nicolás
 */
public class Juego
{

    private Command botonA;
    private Command botonB;
    private Command botonX;
    private Command botonY;

    public Juego()
    {

    }

    public Juego(Command botonA, Command botonB, Command botonX, Command botonY)
    {
        this.botonA = botonA;
        this.botonB = botonB;
        this.botonX = botonX;
        this.botonY = botonY;
    }

    public void ejecutaComando(Boton btn)
    {
        procesaEntrada(btn);
    }

    private void procesaEntrada(Boton btn)
    {
        switch (btn)
        {
            case BOTON_A:
                botonA.execute();
                break;
            case BOTON_B:
                botonB.execute();
                break;
            case BOTON_X:
                botonX.execute();
                break;
            case BOTON_Y:
                botonY.execute();
                break;
            default:
                break;
        }
    }

    public Command getBotonA()
    {
        return botonA;
    }

    public void setBotonA(Command botonA)
    {
        this.botonA = botonA;
    }

    public Command getBotonB()
    {
        return botonB;
    }

    public void setBotonB(Command botonB)
    {
        this.botonB = botonB;
    }

    public Command getBotonX()
    {
        return botonX;
    }

    public void setBotonX(Command botonX)
    {
        this.botonX = botonX;
    }

    public Command getBotonY()
    {
        return botonY;
    }

    public void setBotonY(Command botonY)
    {
        this.botonY = botonY;
    }

}
