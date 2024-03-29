package ch.malbun.fulldrive.ui;

import ch.malbun.fulldrive.components.Components;
import ch.malbun.fulldrive.images.ImageLoader;
import javafx.scene.image.ImageView;

public class MenuImageView extends ImageView {
  public MenuImageView(Components image) {
    super(ImageLoader.load(image));
    setFitHeight(15);
    setFitWidth(15);
  }
}
