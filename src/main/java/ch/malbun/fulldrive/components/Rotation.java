package ch.malbun.fulldrive.components;

public enum Rotation {
  UP(0),
  DOWN(180),
  LEFT(270),
  RIGHT(90);

  private int deg;

  Rotation(int deg) {
    this.deg = deg;
  }

  public int getDeg() {
    return deg;
  }
}
