package trees;

public enum Color {
  RED, BLACK;

  @Override
  public String toString() {
    if (this == RED) {
      return "R";
    }
    else {
      return "B";
    }
  }
}
