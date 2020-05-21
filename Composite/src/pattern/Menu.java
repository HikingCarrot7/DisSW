package pattern;

import java.util.ArrayList;

/**
 *
 * @author Nicolás
 */
public class Menu extends ElementoMenu
{

    private ArrayList<ElementoMenu> subMenus;

    public Menu(String nombre)
    {
        super(nombre);
        subMenus = new ArrayList<>();
    }

    @Override public String toPrint(int espacios)
    {
        String result = String.format("%" + espacios + "s\n", getNombre() + " >");
        return subMenus.stream().map((menu) -> menu.toPrint(espacios + 10)).reduce(result, String::concat);
    }

    public void agregarElementoAlMenu(ElementoMenu elementoMenu)
    {
        subMenus.add(elementoMenu);
    }

    public void eliminarElementoMenu(ElementoMenu elementoMenu)
    {
        subMenus.remove(elementoMenu);
    }

}
