package ch.malbun.fulldrive;

import ch.malbun.fulldrive.components.Components;
import ch.malbun.fulldrive.components.Rotation;
import ch.malbun.fulldrive.components.TileGrid;
import ch.malbun.fulldrive.images.ImageLoader;
import ch.malbun.fulldrive.ui.FullDriveMenuBar;
import ch.malbun.fulldrive.ui.FullDriveToolBar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class App extends Application {
  static TileGrid tileGrid;
  static FullDriveToolBar toolBar;
  static String[] arguments;
  static boolean bootnormal = false;

  @Override
  public void start(Stage stage) {

    bootnormal = arguments.length == 0;

    BorderPane root = new BorderPane();
    root.setStyle("-fx-background-color: black;");

    ScrollPane contentScrollPane = new ScrollPane();
    contentScrollPane.pannableProperty().set(true);
    contentScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    contentScrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    contentScrollPane.setPrefSize(300, 300);
    contentScrollPane.setLayoutX(50);
    contentScrollPane.setLayoutY(50);

    tileGrid = new TileGrid(50);
    contentScrollPane.setContent(tileGrid);
    root.setCenter(contentScrollPane);

    FullDriveMenuBar menuBar = new FullDriveMenuBar();
    root.setTop(menuBar);

    toolBar = new FullDriveToolBar();
    root.setLeft(toolBar);

    Scene scene = new Scene(root, 1000, 700);

    scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
      if (keyEvent.getCode() == KeyCode.Q) {
        tileGrid.setComponentForTile(tileGrid.getSelectedIndex(), Components.WEICHE);
      } else if (keyEvent.getCode() == KeyCode.W) {
        tileGrid.setComponentForTile(tileGrid.getSelectedIndex(), Components.WEICHE2);
      } else if (keyEvent.getCode() == KeyCode.A) {
        tileGrid.setComponentForTile(tileGrid.getSelectedIndex(), Components.STRECKE);
      } else if (keyEvent.getCode() == KeyCode.S) {
        tileGrid.setComponentForTile(tileGrid.getSelectedIndex(), Components.STRECKE2);
      } else if (keyEvent.getCode() == KeyCode.Y) {
        tileGrid.setComponentForTile(tileGrid.getSelectedIndex(), Components.BAHNSTEIG);
      } else if (keyEvent.getCode() == KeyCode.X) {
        tileGrid.setComponentForTile(tileGrid.getSelectedIndex(), Components.SIGNAL);
      } else if (keyEvent.getCode() == KeyCode.B) {
        tileGrid.setComponentForTile(tileGrid.getSelectedIndex(), Components.BLANK);
      } else if (keyEvent.getCode() == KeyCode.I) {
        tileGrid.setRotationForTile(tileGrid.getSelectedIndex(), Rotation.UP);
      } else if (keyEvent.getCode() == KeyCode.K) {
        tileGrid.setRotationForTile(tileGrid.getSelectedIndex(), Rotation.DOWN);
      } else if (keyEvent.getCode() == KeyCode.J) {
        tileGrid.setRotationForTile(tileGrid.getSelectedIndex(), Rotation.LEFT);
      } else if (keyEvent.getCode() == KeyCode.L) {
        tileGrid.setRotationForTile(tileGrid.getSelectedIndex(), Rotation.RIGHT);
      }
    });

    scene.setFill(Color.BLACK);
    stage.getIcons().add(ImageLoader.load(Components.DKW));
    stage.setTitle("FullDrive");
    stage.setScene(scene);

    if (!bootnormal) {
      stage.setOnCloseRequest(e -> {
        try {
          tileGrid.export(new File(arguments[1] + ".png"));
          tileGrid.save(new File(arguments[1] + ".gplx"));
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        Platform.exit();
        System.exit(0);
      });

    } else {
      stage.setOnCloseRequest(Event::consume);
    }



    stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, windowEvent -> {
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
      no.setOnAction(actionEvent -> {
        requestCloseStage.close();
        windowEvent.consume();
      });
      no.setCancelButton(true);
      buttonBox.getChildren().add(no);

      main.getChildren().add(buttonBox);

      Image icon = ImageLoader.load(Components.WEICHE2);

      Scene closeScene = new Scene(main);
      requestCloseStage.setScene(closeScene);
      requestCloseStage.setResizable(false);
      requestCloseStage.setTitle("Wirklich Schliessen");
      requestCloseStage.getIcons().add(icon);
      requestCloseStage.show();
    });
    if (!bootnormal) {
      if (!Objects.equals(arguments[0], "!!nofile!!")) {
        tileGrid.load(new File(arguments[0] + ".gplx"));
      }
    }
    stage.show();
  }

  public static void main(String[] args) {
    arguments = args;
    launch();
  }

  public static TileGrid getTileGrid() {
    return tileGrid;
  }

  public static FullDriveToolBar getToolBar() {
    return toolBar;
  }
}