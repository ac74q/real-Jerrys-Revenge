package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Star extends SuperObject{

    public Star() {
        name = "Star";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/star-pixilart.png/"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
