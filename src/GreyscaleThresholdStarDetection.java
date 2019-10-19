import java.awt.image.Raster;

public class GreyscaleThresholdStarDetection extends StarDetection
{

    public GreyscaleThresholdStarDetection(int threshold)
    {
        this.threshold = threshold;
    }

    private int threshold;

    //if left is bright or top is bright, then already counted.
    public boolean isProbableStar(Raster data, int x, int y) {
        boolean previousIsBright = dedupe(data, x, y);
        return (!previousIsBright && isBright(getRgb(data, x, y )));
    }

    private boolean dedupe(Raster data, int x, int y) {
        return false;
        /*boolean previousIsBright = false;
        if (y > 1) {
            if (isBright(getRgb(data, x, y - 1)))
            {
                previousIsBright = true;
            }
        }
        if (x > 1) {
            if (isBright(getRgb(data, x - 1, y)))
            {
                previousIsBright = true;
            }
        }
        return previousIsBright;*/
    }

    boolean isBright(StarCounter.RGB rgb)
    {
        return (rgb.r + rgb.g + rgb.b > 3*threshold);
    }

}
