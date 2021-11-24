public class Node {

  Node parent;
  Node child;
  Node sibling;
  int key;
  int degree;

  public Node(int key) {
      this.key = key;
      this.degree = 0;
  }

  @Override
  public String toString() {
    return String.valueOf(key);
  }
}
