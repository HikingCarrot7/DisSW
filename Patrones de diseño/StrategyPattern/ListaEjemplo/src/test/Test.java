package test;

import model.BubbleSortBehavior;
import model.Lista;

/**
 *
 * @author Nicolás
 */
public class Test
{

    public static void main(String[] args)
    {
        Lista lista = new Lista(new BubbleSortBehavior());
        lista.sort();
    }

}
