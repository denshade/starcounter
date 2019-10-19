import java.awt.image.Raster;

public class CountProbableStarEvent implements ProbableStarEvent
{
    private int count;
    @Override
    public void trigger(Raster data, int x, int y) {
        count++;
    }

    public int getCount()
    {
        return count;
    }
}

