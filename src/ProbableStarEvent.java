import java.awt.image.Raster;

public interface ProbableStarEvent {

    void trigger(Raster data, int x, int y);
}
