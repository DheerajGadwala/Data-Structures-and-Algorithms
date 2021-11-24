package trees;

import static trees.Color.BLACK;

class Sentinel implements Node {

  private static final Sentinel instance = new Sentinel();

  private Sentinel() {
  }

  public static Node getInstance() {
    return instance;
  }

  @Override
  public Node getParent() {
    return this;
  }

  @Override
  public Node getGrandParent() {
    return this;
  }

  @Override
  public Node getSibling() {
    return this;
  }

  @Override
  public Node getUncle() {
    return this;
  }

  @Override
  public void setParent(Node parent) {
  }

  @Override
  public Node getLeft() {
    return this;
  }

  @Override
  public void setLeft(Node left) {

  }

  @Override
  public Node getRight() {
    return this;
  }

  @Override
  public String getText() {
    return "S";
  }

  @Override
  public void setRight(Node right) {
  }

  @Override
  public Color getColor() {
    return BLACK;
  }

  @Override
  public void setColor(Color color) {

  }

  @Override
  public boolean isLeftChild(Node node) {
    return false;
  }

  @Override
  public boolean isRightChild(Node node) {
    return false;
  }

  @Override
  public boolean isSentinel() {
    return true;
  }

  @Override
  public int getKey() {
    throw new IllegalStateException("Sentinel has no key");
  }

  @Override
  public boolean isRed() {
    return false;
  }

  @Override
  public boolean isBlack() {
    return true;
  }

  @Override
  public Node min() {
    throw new IllegalStateException("Action not possible.");
  }

  @Override
  public Node max() {
    throw new IllegalStateException("Action not possible.");
  }

  @Override
  public int height() {
    return 0;
  }

  @Override
  public int blackHeight() {
    return 0;
  }

  @Override
  public String toString() {
    return getText();
  }
}
