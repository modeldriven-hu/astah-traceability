package hu.modeldriven.astah.core;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.InputStream;

public class ImageLoader {

    private ImageLoader(){
    }

    public static Image loadImage(String filename) {
        try (InputStream inputStream = ImageLoader.class.getResourceAsStream(filename)){
            // Use the class loader to get the resource stream
            if (inputStream == null){
                return null;
            }
            return ImageIO.read(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
