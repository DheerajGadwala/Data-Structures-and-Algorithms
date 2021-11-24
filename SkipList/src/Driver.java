import skiplist.SkipList;

import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    SkipList s = new SkipList();
    Scanner inp = new Scanner(System.in);
    while (true) {
      System.out.println("Input:\n#1.Insert\n#2.LookUp\n#3.Delete\n#4.Print");
      int n = inp.nextInt();
      switch (n) {
        case 1:
          System.out.println("Enter key to insert:");
          s.insert(inp.nextInt());
          break;
        case 2:
          System.out.println("Enter key to lookup:");
          System.out.println(s.lookUp(inp.nextInt()) ? "Key found" : "Key not found");
          break;
        case 3:
          System.out.println("Enter key to delete:");
          s.delete(inp.nextInt());
          break;
        case 4:
          System.out.println(s.print());
          break;
      }
    }
  }
}
