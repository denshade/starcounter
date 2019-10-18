import java.awt.image.Raster;

public class ThresholdStarDetection extends StarDetection
{
    public ThresholdStarDetection(int threshold)
    {
        this.threshold = threshold;
    }

    private int threshold;

    //if left is bright or top is bright, then already counted.
    public boolean isProbableStar(Raster data, int x, int y) {
        boolean previousIsBright = false;
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
        return (!previousIsBright && isBright(getRgb(data, x, y )));
    }

    boolean isBright(StarCounter.RGB rgb)
    {
        return (rgb.r > threshold && rgb.g > threshold && rgb.b > threshold);
    }

}
