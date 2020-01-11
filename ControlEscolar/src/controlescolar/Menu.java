package controlescolar;

/**
 *
 * @author HikingCarrot7
 */
public class Menu
{

    private ControlEscolar controlEscolar;

    public Menu()
    {
        controlEscolar = new ControlEscolar();
    }

    public void mostrarMenu()
    {
        controlEscolar.mostrarTodosDatos();
    }

}
