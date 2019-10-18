import java.awt.image.Raster;

public interface StarDetectionStrategy
{
    public boolean isProbableStar(Raster data, int x, int y);
}
