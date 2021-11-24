package skiplist;

public class Node {
  Node up;
  Node down;
  Node left;
  Node right;
  int value;

  Node(int value) {
    this.value = value;
    this.right = null;
    this.left = null;
    this.down = null;
    this.up = null;
  }
}
