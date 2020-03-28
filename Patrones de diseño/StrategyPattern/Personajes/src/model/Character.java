package model;

/**
 *
 * @author Nicolás
 */
public abstract class Character
{

    private WeaponBehavior weaponBehavior;

    public Character(WeaponBehavior weaponBehavior)
    {
        this.weaponBehavior = weaponBehavior;
    }

    public void attack()
    {
        weaponBehavior.useWeapon();
    }

    public WeaponBehavior getWeaponBehavior()
    {
        return weaponBehavior;
    }

    public void setWeaponBehavior(WeaponBehavior weaponBehavior)
    {
        this.weaponBehavior = weaponBehavior;
    }
}
