package ch.malbun.fulldrive.components;

public enum Components {
  BAHNSTEIG("Bahnsteig"),
  BLANK("Leer"),
  BUE("Bahnübergang"),
  DKW("DKW 1"),
  DKW2("DKW 2"),
  KREUZUNG("Kreuzung 1"),
  KREUZUNG2("Kreuzung 2"),
  KREUZUNG3("Kreuzung 3"),
  KREUZUNG4("Kreuzung 4"),
  KURVE("Kurve 1"),
  KURVE2("Kurve 2"),
  SIGNAL("Signal"),
  STRECKE("Strecke 1"),
  STRECKE2("Strecke 2"),
  UEBERFUEHRUNG("Überführung 1"),
  UEBERFUEHRUNG2("Überführung 2"),
  UEBERFUEHRUNG3("Überführung 3"),
  UEBERFUEHRUNG4("Überführung 4"),
  UEBERFUEHRUNG5("Überführung 5"),
  UEBERFUEHRUNG6("Überführung 6"),
  WEICHE("Weiche 1"),
  WEICHE2("Weiche 2");

  private String name;

  Components(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
