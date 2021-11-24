package trees;

import java.util.NoSuchElementException;

import static trees.Color.RED;
import static trees.Color.BLACK;

public class RedBlackTree {

  Node root;
  Node nil;

  public RedBlackTree() {
    root = Sentinel.getInstance();
    nil = Sentinel.getInstance();
  }

  public void insert(int key) {
    Node y = this.nil;
    Node x = this.root;
    Node z = new ElementNode(key, RED);
    while (x != this.nil) {
      y = x;
      if (z.getKey() < x.getKey()) {
        x = x.getLeft();
      }
      else {
        x = x.getRight();
      }
    }
    z.setParent(y);
    if (y == this.nil) {
      this.root = z;
    }
    else if (z.getKey() < y.getKey()) {
      y.setLeft(z);
    }
    else {
      y.setRight(z);
    }
    fixInsert(z);
  }

  private void fixInsert(Node z) {
    while (z.getParent().isRed()) {
      Node uncle = z.getUncle();
      if (z.getGrandParent().isLeftChild(z.getParent())) {
        if(uncle.isRed()) {
          z.getParent().setColor(BLACK);
          uncle.setColor(BLACK);
          z.getGrandParent().setColor(RED);
          z = z.getGrandParent();
        }
        else if (z.getParent().isRightChild(z)) {
          z = z.getParent();
          leftRotate(z);
        }
        else if (z.getParent().isLeftChild(z)) {
          z.getParent().setColor(BLACK);
          z.getGrandParent().setColor(RED);
          rightRotate(z.getGrandParent());
        }
      }
      else {
        if(uncle.isRed()) {
          z.getParent().setColor(BLACK);
          uncle.setColor(BLACK);
          z.getGrandParent().setColor(RED);
          z = z.getGrandParent();
        }
        else if (z.getParent().isLeftChild(z)) {
          z = z.getParent();
          rightRotate(z);
        }
        else if (z.getParent().isRightChild(z)) {
          z.getParent().setColor(BLACK);
          z.getGrandParent().setColor(RED);
          leftRotate(z.getGrandParent());
        }
      }
    }
    this.root.setColor(BLACK);
  }

  private void rightRotate(Node x) {
    Node y = x.getLeft();
    x.setLeft(y.getRight());
    y.getRight().setParent(x);
    System.out.println(y + " " + x);
    if (x.getParent().isLeftChild(x)) {
      x.getParent().setLeft(y);
    }
    else {
      x.getParent().setRight(y);
    }
    y.setParent(x.getParent());
    y.setRight(x);
    x.setParent(y);
    if (x == this.root) {
      root = y;
    }
  }

  private void leftRotate(Node x) {
    Node y = x.getRight();
    x.setRight(y.getLeft());
    y.getLeft().setParent(x);
    if (x.getParent().isLeftChild(x)) {
      x.getParent().setLeft(y);
    }
    else {
      x.getParent().setRight(y);
    }
    y.setParent(x.getParent());
    y.setLeft(x);
    x.setParent(y);
    if (x == this.root) {
      root = y;
    }
  }

  private void inOrderHelper(Node node) {
    if(node.isElementNode()) {
      inOrderHelper(node.getLeft());
      System.out.print(node.getKey() + " ");
      inOrderHelper(node.getRight());
    }
  }

  public void inOrder() {
    inOrderHelper(root);
    System.out.println();
  }

  public String printTree() {
    return TreePrinter.print(root);
  }

  public boolean search(int key) {
    Node ser = root;
    while(!ser.isSentinel()) {
      if (ser.getKey() == key) {
        return true;
      }
      else if (ser.getKey() > key) {
        ser = ser.getLeft();
      }
      else {
        ser = ser.getRight();
      }
    }
    return false;
  }

  public int min() {
    return minNode().getKey();
  }

  public int max() {
    return maxNode().getKey();
  }

  private Node minNode() {
    if (root.isSentinel()) {
      throw new NoSuchElementException("No elements in the tree");
    }
    return root.min();
  }

  public Node maxNode() {
    if (root.isSentinel()) {
      throw new NoSuchElementException("No elements in the tree");
    }
    return root.max();
  }

  public int height() {
    return root.height();
  }

  public int blackHeight() {
    return root.blackHeight();
  }

  public Node successor(Node x) {
    if(x.getRight().isSentinel()) {
      Node z = x;
      Node y = z.getParent();
      while(!y.isSentinel() && y.isRightChild(z)) {
        z = y;
        y = z.getParent();
      }
      if(y.isSentinel()) {
        throw new NoSuchElementException("This is the largest element in the tree");
      }
      return y;
    }
    else {
      return x.getRight().min();
    }
  }

  public Node predecessor(Node x) {
    if(x.getLeft().isSentinel()) {
      Node z = x;
      Node y = z.getParent();
      while(!y.isSentinel() && y.isLeftChild(z)) {
        z = y;
        y = z.getParent();
      }
      if(y.isSentinel()) {
        throw new NoSuchElementException("This is the smallest element in the tree");
      }
      return y;
    }
    else {
      return x.getLeft().max();
    }
  }

  public Node getRootNode() {
    return root;
  }

}
