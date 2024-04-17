package ch.malbun.fulldrive.components;

import ch.malbun.fulldrive.App;
import ch.malbun.fulldrive.images.ImageLoader;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class TileGrid extends GridPane {

  ArrayList<Tile> tiles = new ArrayList<>();
  int size;
  int selectedIndex = 0;
  public TileGrid(int size) {

    this.size = size;

    setHgap(0);
    setVgap(0);
    setStyle("-fx-background-color: black;");

    for(int i = 0; i < Math.pow(size, 2); i++) {
      Tile tile = new Tile(i);
      tiles.add(tile);
    }

    tiles.forEach(tile -> {
      int[] pos = TileService.getPosByIndex(size, tile.index);
      add(tile, pos[1], pos[0]);
    });
  }

  public void resetBorder() {
    List<Tile> selectedTiles = tiles.stream().filter(tile -> tile.selected).toList();
    selectedTiles.forEach(Tile::resetBorder);
  }

  public void save(File file) {
    List<JSONObject> jsonObjectList = tiles.stream()
            .filter(tile -> tile.component != Components.BLANK)
            .map(tile -> {
              JSONObject tileObject = new JSONObject();
              tileObject.put("component", tile.component.toString());
              tileObject.put("rotation", tile.rotation.toString());
              tileObject.put("index", tile.index);
              return tileObject;
            })
            .toList();
    JSONObject root = new JSONObject();
    root.put("count", jsonObjectList.size());
    jsonObjectList.forEach(obj -> {
      root.accumulate("tiles", obj);
    });

    try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
      pw.print(root.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  public void load(File file) {
    String[] splitedFilename = file.getName().split("\\.");
    if (Arrays.asList(splitedFilename).contains("gplx")) {

      tiles.stream().filter(tile -> tile.component != Components.BLANK).forEach(tile -> tile.setComponent(Components.BLANK));

      try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
        StringBuilder builder = new StringBuilder();
        br.lines().forEach(builder::append);

        JSONObject root = new JSONObject(builder.toString());
        int count = root.getInt("count");

        if (count == 1) {
          JSONObject tileObj = root.getJSONObject("tiles");
          int index = tileObj.getInt("index");

          Tile selectedTile = tiles.stream().filter(tile -> tile.index == index).toList().get(0);
          selectedTile.setComponent(Components.valueOf(tileObj.getString("component")));
          selectedTile.setRotation(Rotation.valueOf(tileObj.getString("rotation")));

        } else if (count >= 2) {
          JSONArray tilesArray = root.getJSONArray("tiles");
          ArrayList<Integer> indexes = new ArrayList<>();
          ArrayList<JSONObject> tileObjects = new ArrayList<>();
          tilesArray.forEach(tileObj -> {
            JSONObject currentObj = (JSONObject) tileObj;
            tileObjects.add(currentObj);
            indexes.add(currentObj.getInt("index"));
          });

          List<Tile> tilesToUpdate = tiles.stream().filter(tile -> indexes.contains(tile.index)).toList();
          tilesToUpdate.forEach(tile -> {
            JSONObject tileObj = tileObjects.stream().filter(i -> i.getInt("index") == tile.index).toList().get(0);
            tile.setRotation(Rotation.valueOf(tileObj.getString("rotation")));
            tile.setComponent(Components.valueOf(tileObj.getString("component")));
          });

        }

      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    } else {
      Stage fileExtensionErrorPopup = new Stage();
      VBox mainBox = new VBox();

      Text infoText = new Text("Es kann keine Datei mit einer anderen Dateiendung als .gplx geöffnet werden!");
      infoText.setTextAlignment(TextAlignment.CENTER);
      mainBox.getChildren().add(infoText);

      Button submitButton = new Button("Ok");
      submitButton.setAlignment(Pos.CENTER);
      submitButton.setOnAction(e -> fileExtensionErrorPopup.close());
      mainBox.getChildren().add(submitButton);

      Scene scene = new Scene(mainBox);
      fileExtensionErrorPopup.setScene(scene);
      fileExtensionErrorPopup.setTitle("Error");
      fileExtensionErrorPopup.getIcons().add(ImageLoader.load(Components.STRECKE2));
      fileExtensionErrorPopup.show();
    }
  }

  public void export(File file) throws IOException {
    resetBorder();
    WritableImage writableImage = snapshot(new SnapshotParameters(), null);
    BufferedImage bfImage = SwingFXUtils.fromFXImage(writableImage, null);
    ImageIO.write(bfImage, "png", file);

  }

  public void setComponentForTile(int index, Components components) {
    tiles.stream().filter(tile -> tile.index == index).toList().get(0).setComponent(components);
    if (!App.bootnormal) {
      save(new File(App.arguments[0] + ".gplx"));
      try {
        export(new File(App.arguments[0] + ".png"));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void setRotationForTile(int index, Rotation rotation) {
    tiles.stream().filter(tile -> tile.index == index).toList().get(0).setRotation(rotation);
  }

  public ArrayList<Tile> getTiles() {
    return tiles;
  }

  public int getSize() {
    return size;
  }

  public int getSelectedIndex() {
    return selectedIndex;
  }
}
