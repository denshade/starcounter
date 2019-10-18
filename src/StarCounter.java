import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class StarCounter
{

    public static class RGB
    {
        public int r;
        public int g;
        public int b;

        public static RGB fromArray(int[] values)
        {
            int r = values[0];
            int g = values[1];
            int b = values[2];
            RGB rgb = new RGB();
            rgb.r = r;
            rgb.b = b;
            rgb.g = g;
            return rgb;
        }


    }

    public static void main(String[] args)
    {
        for (int i = 50; i < 250; i+=10) {
            System.out.println("Amount of stars: "+ i + " " + count(i));
        }
    }
    public static int count(int threshold)
    {

            StarDetectionStrategy strat = new DeltaDetection(threshold);
            int count = 0;
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("sterren.jpg"));
                Raster data = img.getData();
                for (int x = 0; x < img.getWidth(); x++)
                {
                    for (int y = 0; y < img.getHeight(); y++) {
                        if (strat.isProbableStar(data, x, y))
                        {
                            //System.out.println("Probably a star at " +x + " " + y);
                            count++;
                        }
                    }
                }
            } catch (IOException e) {
            }
        return count;
    }

    public static StarCounter.RGB getRgb(Raster data, int x, int y) {
        int[] values = new int[4];
        data.getPixel(x, y, values);
        return StarCounter.RGB.fromArray(values);
    }


}
