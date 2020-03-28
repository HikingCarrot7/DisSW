package model;

import static java.lang.System.out;

/**
 *
 * @author Nicolás
 */
public class KnifeBehavior implements WeaponBehavior
{

    @Override
    public void useWeapon()
    {
        out.println("Uso un Knife para defenderme!");
    }

}
