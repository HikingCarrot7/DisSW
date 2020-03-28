package test;

import static java.lang.System.out;
import java.util.ArrayList;
import model.AddonDecorator;
import model.Beverage;
import model.Caramel;
import model.Chocolate;
import model.Espresso;

/**
 *
 * @author Nicolás
 */
public class Test
{

    public static void main(String[] args)
    {
        Beverage coffee = new Espresso();
        ArrayList<AddonDecorator> lista = new ArrayList<>();

        lista.add(new Caramel(4));
        lista.add(new Chocolate(4));

        for (int i = 0; i < lista.size() - 1; i++)
            lista.get(i).setBeverage(lista.get(i + 1));

        lista.get(lista.size() - 1).setBeverage(coffee);

        out.println(lista.get(0).desc());
    }

}
