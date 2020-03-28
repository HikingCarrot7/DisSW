package model;

import static java.lang.System.out;

/**
 *
 * @author Nicolás
 */
public class BowAndRowBehavior implements WeaponBehavior
{

    @Override
    public void useWeapon()
    {
        out.println("Uso un arco para defenderme!");
    }

}
