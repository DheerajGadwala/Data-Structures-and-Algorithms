package trees;

public interface Node extends TreePrinter.PrintableNode {

  default boolean isElementNode() {
    return false;
  }
  default boolean isSentinel() {
    return false;
  }

  Node getParent();

  Node getGrandParent();

  Node getSibling();

  Node getUncle();

  void setParent(Node parent);

  Node getLeft();

  void setLeft(Node left);

  Node getRight();

  void setRight(Node right);

  Color getColor();

  void setColor(Color color);

  boolean isLeftChild(Node node);

  boolean isRightChild(Node node);

  int getKey();

  boolean isRed();

  boolean isBlack();

  Node min();

  Node max();

  int height();

  int blackHeight();
}
