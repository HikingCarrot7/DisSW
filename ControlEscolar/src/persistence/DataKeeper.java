package persistence;

/**
 *
 * @author HikingCarrot7
 */
@FunctionalInterface
public interface DataKeeper<S>
{

    public void saveData(S item);
}
