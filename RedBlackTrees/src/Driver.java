import trees.Node;
import trees.RedBlackTree;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    RedBlackTree tree = new RedBlackTree();
    Scanner inp = new Scanner(System.in);
    boolean cont = true;
    while(cont) {
      System.out.println("Enter input:\n#1.INSERT\n#2.SORT\n#3.MAX\n#4.MIN\n#5.SEARCH\n#6.PRINT\n#7.BREAK");
      int n = inp.nextInt();
      switch (n) {
        case 1:
          System.out.println("Enter key to insert:");
          int k = inp.nextInt();
          tree.insert(k);
          System.out.printf("Height: %d, Black height: %d\n", tree.height(), tree.blackHeight());
          break;
        case 2:
          tree.inOrder();
          System.out.printf("Height: %d, Black height: %d\n", tree.height(), tree.blackHeight());
          break;
        case 3:
          System.out.println(tree.max());
          System.out.printf("Height: %d, Black height: %d\n", tree.height(), tree.blackHeight());
          break;
        case 4:
          System.out.println(tree.min());
          System.out.printf("Height: %d, Black height: %d\n", tree.height(), tree.blackHeight());
          break;
        case 5:
          System.out.println("Enter key to search:");
          int q = inp.nextInt();
          System.out.println(tree.search(q) ? "Key found" : "Key not found");
          System.out.printf("Height: %d, Black height: %d\n", tree.height(), tree.blackHeight());
          break;
        case 6:
          try(FileWriter fw = new FileWriter("printTree.txt", true);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter out = new PrintWriter(bw))
          {
            System.out.printf("Height: %d, Black height: %d\n", tree.height(), tree.blackHeight());
            out.println(tree.printTree());
          } catch (IOException ignored) {
          }
          break;
        case 7:
          cont = false;
          break;
      }
    }
    Node current = tree.getRootNode();
    System.out.printf("Root Key: %d\n", current.getKey());
    while(true) {
      System.out.println("Enter input:\n#1.SUCCESSOR\n#2.PREDECESSOR");
      int n = inp.nextInt();
      switch (n) {
        case 1:
          try {
            current = tree.successor(current);
            System.out.printf("Key: %d\n", current.getKey());
          }
          catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 2:
          try {
            current = tree.predecessor(current);
            System.out.printf("Key: %d\n", current.getKey());
          }
          catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
      }
    }
  }
}
