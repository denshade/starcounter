import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
        for (int i = 20; i < 250; i+=10) {
            String filename = "f" + i + ".bmp";
            System.out.println("Amount of stars: "+ i + " " + count(new DeltaDetection(i)));
            draw(filename, new DeltaDetection(i));
        }
    }
    public static int count(StarDetectionStrategy strat)
    {
        Raster data = getRaster();
        CountProbableStarEvent counter = new CountProbableStarEvent();
        walk(strat, Collections.singletonList(counter), data);
        return counter.getCount();
    }

    private static Raster getRaster() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("sterren.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img.getData();
    }

    public static void draw(String filename, StarDetectionStrategy strat)
    {
        Raster data = getRaster();
        DrawProbableStarEventListener counter = new DrawProbableStarEventListener(data.getWidth(), data.getHeight(), filename);
        walk(strat, Collections.singletonList(counter), data);
        counter.write();
    }


    public static int walk(StarDetectionStrategy strat, List<ProbableStarEvent> events, Raster data)
    {
        int count = 0;
            for (int x = 0; x < data.getWidth(); x++)
            {
                for (int y = 0; y < data.getHeight(); y++) {
                    if (strat.isProbableStar(data, x, y))
                    {
                        //System.out.println("Probably a star at " +x + " " + y);
                        for (ProbableStarEvent event : events)
                        {
                            event.trigger(data, x, y);
                        }
                    }
                }
            }
        return count;
    }


}
