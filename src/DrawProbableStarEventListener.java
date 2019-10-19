
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class DrawProbableStarEventListener implements ProbableStarEvent
{
    private final String filename;
    private BufferedImage image;
    public DrawProbableStarEventListener(int width, int height, String filename)
    {
        this.filename = filename;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    }
    @Override
    public void trigger(Raster data, int x, int y) {
        image.setRGB(x,y,255*255*255);
    }

    public void write()
    {
        try {
            ImageIO.write(image, "bmp", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
