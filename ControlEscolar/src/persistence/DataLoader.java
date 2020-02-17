package persistence;

/**
 *
 * @author HikingCarrot7
 */
@FunctionalInterface
public interface DataLoader<L>
{

    public L loadData();
}
