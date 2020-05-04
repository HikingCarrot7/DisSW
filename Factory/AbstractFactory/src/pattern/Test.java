package pattern;

/**
 *
 * @author Nicolás
 */
public class Test
{

    public static void main(String[] args)
    {
        Cliente cliente = new Cliente("Cientifico", "woods", "Foreign Forever", "Journal Migration", new FactoryDivulgacion());
        System.out.println(cliente.getArt().getClass().getSimpleName());
        System.out.println(cliente.getPub().getClass().getSimpleName());
    }

}
