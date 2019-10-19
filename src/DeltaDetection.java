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
        if (dedupe(raster,x,y)) return false;
        return diff > threshold;
    }

    private boolean dedupe(Raster raster, int x, int y) {
        if (x < 1 || y < 1 || x > raster.getWidth() - 2 || y > raster.getHeight() - 2)
        {
            return false;
        }
        int middle = getBrightness(raster, x, y);
        int top = getBrightness(raster,x,y - 1);
        int bottom = getBrightness(raster,x,y + 1);
        int left = getBrightness(raster,x - 1, y);
        int right = getBrightness(raster,x + 1, y);
        if (middle < top || middle < bottom || middle < left || middle < right){
            return false;
        }
        //if the pixels are all the same color then let the pixel with x+y least win. left most, top most win.
        if (middle != top)
        {
            return true;
        }
        return false;
    }
}
