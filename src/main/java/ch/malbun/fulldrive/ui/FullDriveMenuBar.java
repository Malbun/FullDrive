package ch.malbun.fulldrive.ui;

import ch.malbun.fulldrive.App;
import ch.malbun.fulldrive.components.Components;
import ch.malbun.fulldrive.images.ImageLoader;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FullDriveMenuBar extends VBox {
  public FullDriveMenuBar() {
    MenuBar bar = new MenuBar();
    getChildren().add(bar);

    Menu file = new Menu("Datei");
    bar.getMenus().add(file);

    MenuItem close = new MenuItem("Schliessen");
    file.getItems().add(close);
    close.setOnAction(e -> {
      Stage requestCloseStage = new Stage();
      VBox main = new VBox();

      Text information1Text = new Text("Wollen sie FullDrive wirklich schliessen?");
      information1Text.setTextAlignment(TextAlignment.CENTER);
      main.getChildren().add(information1Text);

      Text information2Text = new Text("Ungespeicherte Änderungen gehen verloren!");
      information2Text.setTextAlignment(TextAlignment.CENTER);
      main.getChildren().add(information2Text);

      HBox buttonBox = new HBox();

      Button yes = new Button("Ja");
      yes.setAlignment(Pos.CENTER_LEFT);
      yes.setOnAction(actionEvent -> Platform.exit());
      buttonBox.getChildren().add(yes);

      buttonBox.setAlignment(Pos.CENTER);
      buttonBox.getChildren().add(new Separator());

      Button no = new Button("Nein");
      no.setAlignment(Pos.CENTER_RIGHT);
      no.setOnAction(actionEvent -> requestCloseStage.close());
      no.setCancelButton(true);
      buttonBox.getChildren().add(no);

      main.getChildren().add(buttonBox);

      Image icon = ImageLoader.load(Components.WEICHE2);

      Scene scene = new Scene(main);
      requestCloseStage.setScene(scene);
      requestCloseStage.setTitle("Wirklich Schliessen");
      requestCloseStage.getIcons().add(icon);
      requestCloseStage.show();
    });

    MenuItem save = new MenuItem("Speichern");
    file.getItems().add(save);
    save.setOnAction(e -> {
      FileChooser chooser = new FileChooser();
      chooser.setTitle("Speicherort wählen");
      chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Gleisplan Datei", "*.gplx"));
      chooser.setInitialFileName("Gleisplan");
      File savedFile = chooser.showSaveDialog(new Popup());
      App.getTileGrid().save(savedFile);
    });

    MenuItem load = new MenuItem("Laden");
    file.getItems().add(load);
    load.setOnAction(e -> {
      FileChooser chooser = new FileChooser();
      chooser.setTitle("Datei öffnen");
      chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Gleisplan Datei", "*.gplx"));
      File openedFile = chooser.showOpenDialog(new Popup());
      App.getTileGrid().load(openedFile);
    });

    Menu export = new Menu("Export");
    bar.getMenus().add(export);

    MenuItem png = new MenuItem("Als Bild");
    export.getItems().add(png);
    png.setOnAction(e -> {
      FileChooser chooser = new FileChooser();
      chooser.setTitle("Speichern");
      chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG-Bild", "*.png"));
      chooser.setInitialFileName("Gleisplan");
      File exportFile = chooser.showSaveDialog(new Popup());
      try {
        App.getTileGrid().export(exportFile);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }

    });

  }
}
