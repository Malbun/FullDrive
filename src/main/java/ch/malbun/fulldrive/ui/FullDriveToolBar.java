package ch.malbun.fulldrive.ui;

import ch.malbun.fulldrive.App;
import ch.malbun.fulldrive.components.Components;
import ch.malbun.fulldrive.components.Rotation;
import ch.malbun.fulldrive.components.Tile;
import ch.malbun.fulldrive.components.TileService;
import ch.malbun.fulldrive.images.ImageLoader;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class FullDriveToolBar extends ToolBar {

  int index;
  Tile tile;

  MenuButton type;
  MenuButton rotation;
  Text posText = new Text("0/0");
  public FullDriveToolBar() {
    setStyle("-fx-background-color: black;");
    setOrientation(Orientation.VERTICAL);

    index = 0;
    tile = App.getTileGrid().getTiles().get(index);

    ImageView typeImageView = new ImageView(ImageLoader.load(tile.getComponent()));
    typeImageView.setFitHeight(20);
    typeImageView.setFitWidth(20);
    type = new MenuButton(tile.getComponent().getName(), typeImageView);

    MenuImageView blankImageView = new MenuImageView(Components.BLANK);
    MenuItem blank = new MenuItem("Leer", blankImageView);
    blank.setOnAction(e -> tile.setComponent(Components.BLANK));
    type.getItems().add(blank);

    MenuImageView switchMenuImageView = new MenuImageView(Components.WEICHE);
    Menu switches = new Menu("Weichen", switchMenuImageView);
    type.getItems().add(switches);

    MenuImageView switchImageView = new MenuImageView(Components.WEICHE);
    MenuItem switch1 = new MenuItem("Weiche 1", switchImageView);
    switch1.setOnAction(e -> tile.setComponent(Components.WEICHE));
    switches.getItems().add(switch1);

    MenuImageView switch2ImageView = new MenuImageView(Components.WEICHE2);
    MenuItem switch2 = new MenuItem("Weiche 2", switch2ImageView);
    switch2.setOnAction(e -> tile.setComponent(Components.WEICHE2));
    switches.getItems().add(switch2);

    MenuImageView streckeMenuImageView = new MenuImageView(Components.STRECKE);
    Menu streckeMenu = new Menu("Strecke", streckeMenuImageView);
    type.getItems().add(streckeMenu);

    MenuImageView streckeImageView = new MenuImageView(Components.STRECKE);
    MenuItem strecke = new MenuItem("Strecke 1", streckeImageView);
    strecke.setOnAction(e -> tile.setComponent(Components.STRECKE));
    streckeMenu.getItems().add(strecke);

    MenuImageView strecke2ImageView = new MenuImageView(Components.STRECKE2);
    MenuItem strecke2 = new MenuItem("Strecke 2", strecke2ImageView);
    strecke2.setOnAction(e -> tile.setComponent(Components.STRECKE2));
    streckeMenu.getItems().add(strecke2);

    MenuImageView kurveImageView = new MenuImageView(Components.KURVE);
    MenuItem kurve = new MenuItem("Kurve 1", kurveImageView);
    kurve.setOnAction(e -> tile.setComponent(Components.KURVE));
    streckeMenu.getItems().add(kurve);

    MenuImageView kurve2ImageView = new MenuImageView(Components.KURVE2);
    MenuItem kurve2 = new MenuItem("Kurve 2", kurve2ImageView);
    kurve2.setOnAction(e -> tile.setComponent(Components.KURVE2));
    streckeMenu.getItems().add(kurve2);

    MenuImageView dkwMenuImageView = new MenuImageView(Components.DKW);
    Menu dkwMenu = new Menu("DKW", dkwMenuImageView);
    type.getItems().add(dkwMenu);

    MenuImageView dkwImageView = new MenuImageView(Components.DKW);
    MenuItem dkw = new MenuItem("DKW", dkwImageView);
    dkw.setOnAction(e -> tile.setComponent(Components.DKW));
    dkwMenu.getItems().add(dkw);

    MenuImageView dkw2ImageView = new MenuImageView(Components.DKW2);
    MenuItem dkw2 = new MenuItem("DKW 2", dkw2ImageView);
    dkw2.setOnAction(e -> tile.setComponent(Components.DKW2));
    dkwMenu.getItems().add(dkw2);

    MenuImageView kreuzungMenuImageView = new MenuImageView(Components.KREUZUNG);
    Menu kreuzungMenu = new Menu("Kreuzungen", kreuzungMenuImageView);
    type.getItems().add(kreuzungMenu);

    MenuImageView kreuzungImageView = new MenuImageView(Components.KREUZUNG);
    MenuItem kreuzung = new MenuItem("Kreuzung 1", kreuzungImageView);
    kreuzung.setOnAction(e -> tile.setComponent(Components.KREUZUNG));
    kreuzungMenu.getItems().add(kreuzung);

    MenuImageView kreuzung2ImageView = new MenuImageView(Components.KREUZUNG2);
    MenuItem kreuzung2 = new MenuItem("Kreuzung 2", kreuzung2ImageView);
    kreuzung2.setOnAction(e -> tile.setComponent(Components.KREUZUNG2));
    kreuzungMenu.getItems().add(kreuzung2);

    MenuImageView kreuzung3ImageView = new MenuImageView(Components.KREUZUNG3);
    MenuItem kreuzung3 = new MenuItem("Kreuzung 3", kreuzung3ImageView);
    kreuzung3.setOnAction(e -> tile.setComponent(Components.KREUZUNG3));
    kreuzungMenu.getItems().add(kreuzung3);

    MenuImageView kreuzung4ImageView = new MenuImageView(Components.KREUZUNG4);
    MenuItem kreuzung4 = new MenuItem("Kreuzung 1", kreuzung4ImageView);
    kreuzung4.setOnAction(e -> tile.setComponent(Components.KREUZUNG4));
    kreuzungMenu.getItems().add(kreuzung4);

    MenuImageView ueberfuehrungenMenuImageView = new MenuImageView(Components.UEBERFUEHRUNG);
    Menu ueberfuehrungMenu = new Menu("Überführungen", ueberfuehrungenMenuImageView);
    type.getItems().add(ueberfuehrungMenu);

    MenuImageView ueberfuehrung1ImageView = new MenuImageView(Components.UEBERFUEHRUNG);
    MenuItem ueberfuehrung1 = new MenuItem("Überführung 1", ueberfuehrung1ImageView);
    ueberfuehrung1.setOnAction(e -> tile.setComponent(Components.UEBERFUEHRUNG));
    ueberfuehrungMenu.getItems().add(ueberfuehrung1);

    MenuImageView ueberfuehrung2ImageView = new MenuImageView(Components.UEBERFUEHRUNG2);
    MenuItem ueberfuehrung2 = new MenuItem("Überführung 2", ueberfuehrung2ImageView);
    ueberfuehrung2.setOnAction(e -> tile.setComponent(Components.UEBERFUEHRUNG2));
    ueberfuehrungMenu.getItems().add(ueberfuehrung2);

    MenuImageView ueberfuehrung3ImageView = new MenuImageView(Components.UEBERFUEHRUNG3);
    MenuItem ueberfuehrung3 = new MenuItem("Überführung 3", ueberfuehrung3ImageView);
    ueberfuehrung3.setOnAction(e -> tile.setComponent(Components.UEBERFUEHRUNG3));
    ueberfuehrungMenu.getItems().add(ueberfuehrung3);

    MenuImageView ueberfuehrung4ImageView = new MenuImageView(Components.UEBERFUEHRUNG4);
    MenuItem ueberfuehrung4 = new MenuItem("Überführung 4", ueberfuehrung4ImageView);
    ueberfuehrung4.setOnAction(e -> tile.setComponent(Components.UEBERFUEHRUNG4));
    ueberfuehrungMenu.getItems().add(ueberfuehrung4);

    MenuImageView ueberfuehrung5ImageView = new MenuImageView(Components.UEBERFUEHRUNG5);
    MenuItem ueberfuehrung5 = new MenuItem("Überführung 5", ueberfuehrung5ImageView);
    ueberfuehrung5.setOnAction(e -> tile.setComponent(Components.UEBERFUEHRUNG5));
    ueberfuehrungMenu.getItems().add(ueberfuehrung5);

    MenuImageView ueberfuehrung6ImageView = new MenuImageView(Components.UEBERFUEHRUNG6);
    MenuItem ueberfuehrung6 = new MenuItem("Überführung 6", ueberfuehrung6ImageView);
    ueberfuehrung6.setOnAction(e -> tile.setComponent(Components.UEBERFUEHRUNG6));
    ueberfuehrungMenu.getItems().add(ueberfuehrung6);

    MenuImageView verschiedesMenuImageView = new MenuImageView(Components.BUE);
    Menu verschiedenesMenu = new Menu("Verschiedenes", verschiedesMenuImageView);
    type.getItems().add(verschiedenesMenu);

    MenuImageView bueImageView = new MenuImageView(Components.BUE);
    MenuItem bue = new MenuItem("Bahnübergang", bueImageView);
    bue.setOnAction(e -> tile.setComponent(Components.BUE));
    verschiedenesMenu.getItems().add(bue);

    MenuImageView signalImageView = new MenuImageView(Components.SIGNAL);
    MenuItem signal = new MenuItem("Signal", signalImageView);
    signal.setOnAction(e -> tile.setComponent(Components.SIGNAL));
    verschiedenesMenu.getItems().add(signal);

    MenuImageView perronImageView = new MenuImageView(Components.BAHNSTEIG);
    MenuItem perron = new MenuItem("Bahnsteig", perronImageView);
    perron.setOnAction(e -> tile.setComponent(Components.BAHNSTEIG));
    verschiedenesMenu.getItems().add(perron);

    getItems().add(type);

    rotation = new MenuButton("NORD");

    MenuItem up = new MenuItem("NORD");
    up.setOnAction(e -> tile.setRotation(Rotation.UP));
    rotation.getItems().add(up);

    MenuItem down = new MenuItem("SÜD");
    down.setOnAction(e -> tile.setRotation(Rotation.DOWN));
    rotation.getItems().add(down);

    MenuItem left = new MenuItem("WEST");
    left.setOnAction(e -> tile.setRotation(Rotation.LEFT));
    rotation.getItems().add(left);

    MenuItem right = new MenuItem("OST");
    right.setOnAction(e -> tile.setRotation(Rotation.RIGHT));
    rotation.getItems().add(right);

    getItems().add(rotation);

    posText.setFill(Color.WHITE);
    getItems().add(posText);

    Button dummyPlaceholder = new Button("                                       ");
    dummyPlaceholder.setVisible(false);
    getItems().add(dummyPlaceholder);
  }

  public void update(int index) {
    this.index = index;
    tile = App.getTileGrid().getTiles().get(index);
    ImageView updateView = new ImageView(ImageLoader.load(tile.getComponent()));
    updateView.setFitWidth(20);
    updateView.setFitHeight(20);
    type.setGraphic(updateView);
    type.setText(tile.getComponent().getName());

    switch (tile.getRotation()) {
      case UP -> rotation.setText("NORD");
      case DOWN -> rotation.setText("SÜD");
      case LEFT -> rotation.setText("WEST");
      case RIGHT -> rotation.setText("OST");
    }
  }

  public void updateCoords(int index) {
    int[] coords = TileService.getPosByIndex(App.getTileGrid().getSize(), index);
    posText.setText(coords[1] + "/" + coords[0]);

  }

}
