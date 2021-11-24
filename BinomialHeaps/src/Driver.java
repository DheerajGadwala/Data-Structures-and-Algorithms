import java.util.Scanner;

public class Driver {

  public static void main(String[] args) {
    BinomialHeap b = new BinomialHeap();
    Scanner inp = new Scanner(System.in);
    while(true) {
      System.out.println("Enter Choice:\n#1.Insert(i)\n#2.Extract-Min(e)\n#3.Heap-Minimum(m)\n#4.Decrease-Key(k)\n#5.Delete-key(d)\n#6.Root-List-Degrees(r)\n#7.Print-keys(p)");
      char c = inp.next().charAt(0);
      switch(c) {
        case 'i':
          System.out.println("Enter key:");
          b.insert(new Node(inp.nextInt()));
          break;
        case 'm':
          System.out.println("Result:");
          try {
            System.out.println(b.heapMinimum().toString());
          }
          catch (IllegalStateException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 'e':
          System.out.println("Result:");
          try {
            System.out.println(b.extractMin().toString());
          }
          catch(IllegalStateException ise) {
            System.out.println(ise.getMessage());
          }
          break;
        case 'r':
          b.checkSizes();
          break;
        case 'd':
          System.out.println("Enter key to delete:");
          int k = inp.nextInt();
          try {
            b.delete(k);
            System.out.println("Node with given key deleted.");
          }
          catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
          }
          break;
        case 'k':
          System.out.println("Enter key to be decreased");
          int k1 = inp.nextInt();
          System.out.println("Enter new key");
          int k2 = inp.nextInt();
          try {
            b.decreaseKey(k1, k2);
            System.out.println("Node with given key decreased to new value.");
          }
          catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
          }
          break;
        case 'p':
          System.out.println(b);
          break;
      }
    }
  }

}
