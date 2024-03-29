package ch.malbun.fulldrive.images;

import ch.malbun.fulldrive.components.Components;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {
  public static Image load(Components components) {
    Image image = null;
    try (InputStream is = ImageLoader.class.getResourceAsStream( components.toString().toLowerCase() + ".png")) {
      assert is != null;
      image = new Image(is);
    } catch (IOException ignored) {}

    return image;
  }
}
