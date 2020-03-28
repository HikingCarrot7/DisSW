package model;

import static java.lang.System.out;

/**
 *
 * @author Nicolás
 */
public class AxeBehavior implements WeaponBehavior
{

    @Override
    public void useWeapon()
    {
        out.println("Uso un Axe para defenderme!");
    }

}
