package obj;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Bread extends SuperObject {

    public OBJ_Bread() {
        name = "Bread";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/obj/bread.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
