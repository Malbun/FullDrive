package ch.malbun.fulldrive.components;

import ch.malbun.fulldrive.App;
import ch.malbun.fulldrive.images.ImageLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class Tile extends HBox {
  private ImageView mainView;
  Rotation rotation = Rotation.UP;
  int index;
  Components component = Components.BLANK;
  boolean selected = false;
  Border standardBorder;
  public Tile(int index) {
    mainView = new ImageView(ImageLoader.load(Components.BLANK));

    BorderStrokeStyle borderStrokeStyle = new BorderStrokeStyle(
            StrokeType.INSIDE,
            StrokeLineJoin.MITER,
            StrokeLineCap.BUTT,
            10,
            0,
            null
    );
    BorderStroke borderStroke = new BorderStroke(Color.BLACK, borderStrokeStyle, new CornerRadii(0), new BorderWidths(1));
    standardBorder = new Border(borderStroke);
    setBorder(standardBorder);

    this.index = index;
    mainView.setFitHeight(35);
    mainView.setFitWidth(35);

    getChildren().add(mainView);

    setOnMouseClicked(e -> {
      App.getTileGrid().resetBorder();
      selected = true;
      App.getTileGrid().selectedIndex = this.index;
      App.getToolBar().update(this.index);
      BorderStrokeStyle selectedBorderStrokeStyle = new BorderStrokeStyle(
              StrokeType.INSIDE,
              StrokeLineJoin.MITER,
              StrokeLineCap.BUTT,
              10,
              0,
              null
      );
      BorderStroke selectedBorderStroke = new BorderStroke(Color.VIOLET, selectedBorderStrokeStyle, new CornerRadii(0), new BorderWidths(1));
      setBorder(new Border(selectedBorderStroke));

    });

    setOnMouseEntered(e -> {
      App.getToolBar().updateCoords(index);
    });
  }

  public void update() {
    mainView.setImage(ImageLoader.load(component));
    setRotate(rotation.getDeg());
    App.getToolBar().update(index);

  }

  public void resetBorder() {
    setBorder(standardBorder);
    selected = false;
  }

  public Rotation getRotation() {
    return rotation;
  }

  public void setRotation(Rotation rotation) {
    this.rotation = rotation;
    update();
  }

  public int getIndex() {
    return index;
  }

  public Components getComponent() {
    return component;
  }

  public void setComponent(Components component) {
    this.component = component;
    update();
  }
}
