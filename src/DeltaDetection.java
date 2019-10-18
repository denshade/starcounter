import java.awt.image.Raster;

public class DeltaDetection extends StarDetection
{
    private int threshold;
    public DeltaDetection(int threshold)
    {
        this.threshold = threshold;
    }
    @Override
    public boolean isProbableStar(Raster data, int x, int y) {
        return isBrighter(data,x,y);
    }

    private boolean isBrighter(Raster raster, int x, int y) {
        int middle = getBrightness(raster,x,y);
        int diff = 0;
        if (y > 1) {
            int leftDiff = getBrightness(raster,x,y - 1);
            diff = middle - leftDiff;
        }
        if (x > 1) {
            int leftDiff = getBrightness(raster,x - 1,y);
            diff = middle - leftDiff;
        }
        return diff > threshold;
    }
}
