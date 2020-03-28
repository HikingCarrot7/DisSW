package model;

/**
 *
 * @author Nicolás
 */
public class Lista
{

    private ISortBehavior iSortBehavior;

    public Lista(ISortBehavior iSortBehavior)
    {
        this.iSortBehavior = iSortBehavior;
    }

    public void sort()
    {
        iSortBehavior.sort(this);
    }

    public ISortBehavior getiSortBehavior()
    {
        return iSortBehavior;
    }

    public void setiSortBehavior(ISortBehavior iSortBehavior)
    {
        this.iSortBehavior = iSortBehavior;
    }

}
