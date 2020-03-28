package test;

import model.Character;
import model.King;
import model.SwordBehavior;

/**
 *
 * @author Nicolás
 */
public class Test
{

    public static void main(String[] args)
    {
        Character king = new King(new SwordBehavior());
        king.attack();
    }

}
