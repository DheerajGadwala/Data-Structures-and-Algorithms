package trees;

import static trees.Color.BLACK;
import static trees.Color.RED;

class ElementNode implements Node{
  private Node parent;
  private Node left;
  private Node right;
  private int key;
  private Color color;

  ElementNode(int key, Color color) {
    this.key = key;
    this.color = color;
    setLeft(Sentinel.getInstance());
    setRight(Sentinel.getInstance());
    setParent(Sentinel.getInstance());
  }



  @Override
  public boolean isElementNode() {
    return true;
  }

  @Override
  public Node getParent() {
    return parent;
  }

  @Override
  public Node getGrandParent() {
    return getParent().getParent();
  }

  @Override
  public Node getSibling() {
    return getParent().isRightChild(this) ? getParent().getLeft() : getParent().getRight();
  }

  @Override
  public Node getUncle() {
    return getParent().getSibling();
  }

  @Override
  public void setParent(Node parent) {
    this.parent = parent;
  }

  @Override
  public Node getLeft() {
    return left;
  }

  @Override
  public void setLeft(Node left) {
    this.left = left;
  }

  @Override
  public Node getRight() {
    return right;
  }

  @Override
  public String getText() {
    return key+"("+color.toString()+")";
  }

  @Override
  public void setRight(Node right) {
    this.right = right;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public boolean isLeftChild(Node node) {
    return node == this.left;
  }

  @Override
  public boolean isRightChild(Node node) {
    return node == this.right;
  }
  @Override
  public int getKey() {
    return key;
  }

  @Override
  public boolean isRed() {
    return color == RED;
  }

  @Override
  public boolean isBlack() {
    return color == BLACK;
  }

  @Override
  public Node min() {
    return left.isSentinel() ? this : left.min();
  }

  @Override
  public Node max() {
    return right.isSentinel() ? this : right.max();
  }

  @Override
  public int height() {
    return 1 + Math.max(right.height(), left.height());
  }

  @Override
  public int blackHeight() {
    return (this.isBlack() ? 1 : 0) + left.blackHeight();
  }

  @Override
  public String toString() {
    return getText();
  }
}
