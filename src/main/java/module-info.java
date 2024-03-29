module ch.malbun.fulldrive {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.json;
  requires java.desktop;
  requires javafx.swing;


  opens ch.malbun.fulldrive to javafx.fxml;
  exports ch.malbun.fulldrive;
  exports ch.malbun.fulldrive.images;
  opens ch.malbun.fulldrive.images to javafx.fxml;

}