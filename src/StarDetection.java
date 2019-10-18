import java.awt.image.Raster;

public abstract class StarDetection implements StarDetectionStrategy
{
    public StarCounter.RGB getRgb(Raster data, int x, int y) {
        int[] values = new int[4];
        data.getPixel(x, y, values);
        return StarCounter.RGB.fromArray(values);
    }

    public int getBrightness(Raster data, int x, int y) {
        int[] values = new int[4];
        data.getPixel(x, y, values);
        return values[0]+ values[1] + values[2];
    }


}
