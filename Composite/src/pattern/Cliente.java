package pattern;

/**
 *
 * @author Nicolás
 */
public class Cliente
{

    public static void main(String[] args)
    {
        Menu menu = new Menu("Primer menú");
        menu.agregarElementoAlMenu(new Platillo("Arroz"));

        Menu menu2 = new Menu("Segundo menú");
        menu2.agregarElementoAlMenu(new Platillo("Frijol"));
        menu2.agregarElementoAlMenu(new Platillo("Zanahoria"));

        Menu menu3 = new Menu("Tercer menú");
        menu3.agregarElementoAlMenu(new Platillo("Pan"));
        menu3.agregarElementoAlMenu(new Platillo("Leche"));

        menu2.agregarElementoAlMenu(menu3);

        menu2.agregarElementoAlMenu(new Platillo("Pescado"));
        menu2.agregarElementoAlMenu(new Platillo("Jamón"));
        menu2.agregarElementoAlMenu(new Platillo("Queso"));

        menu.agregarElementoAlMenu(menu2);

        menu.agregarElementoAlMenu(new Platillo("Fideos"));
        menu.agregarElementoAlMenu(new Platillo("Carne"));

        System.out.println(menu.toPrint(1));
    }

}
