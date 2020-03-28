package model;

import static java.lang.System.out;

/**
 *
 * @author Nicolás
 */
public class SwordBehavior implements WeaponBehavior
{

    @Override
    public void useWeapon()
    {
        out.println("Uso una sword para defenderme!");
    }

}
