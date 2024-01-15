package hu.modeldriven.astah.core;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.InputStream;

public class ImageLoader {

    public static Image loadImage(String filename) {
        try {
            // Use the class loader to get the resource stream
            InputStream inputStream = ImageLoader.class.getResourceAsStream(filename);

            Image image = ImageIO.read(inputStream);
            inputStream.close();

            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
