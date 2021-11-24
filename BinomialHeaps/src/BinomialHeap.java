import java.io.IOException;

public class BinomialHeap {

  private Node head;

  public BinomialHeap() {
    this.head = null;
  }

  public Node heapMinimum() {
    Node curr = this.head;
    Node min = this.head;
    while (curr != null) {
      if (curr.key < min.key) {
        min = curr;
      }
      curr = curr.sibling;
    }
    if(min == null) {
      throw new IllegalStateException("No objects in this heap");
    }
    return min;
  }

  private void binomialLink(Node y, Node z) {
    y.parent = z;
    y.sibling = z.child;
    z.child = y;
    z.degree++;
  }

  private Node binomialHeapMerge(BinomialHeap A, BinomialHeap B) {
    Node a = A.head;
    Node b = B.head;
    Node head = null;
    if (a == null) {
      head = b;
    }
    else if (b == null) {
      head = a;
    }
    else {
      if (a.degree < b.degree) {
        head = a;
        a = a.sibling;
      }
      else {
        head = b;
        b = b.sibling;
      }
      Node curr = head;
      while (a != null && b != null) {
        if (a.degree < b.degree) {
          curr.sibling = a;
          a = a.sibling;
        }
        else {
          curr.sibling = b;
          b = b.sibling;
        }
        curr = curr.sibling;
      }
      if (a == null) {
        curr.sibling = b;
      }
      else {
        curr.sibling = a;
      }
    }
    return head;
  }

  private BinomialHeap binomialHeapUnion(BinomialHeap A, BinomialHeap B) {
    BinomialHeap H = new BinomialHeap();
    H.head = A.binomialHeapMerge(A, B);
    if (H.head == null) {
      return H;
    }
    Node prevX = null;
    Node x = H.head;
    Node nextX = x.sibling;
    while(nextX != null) {
      if ((x.degree != nextX.degree) || (nextX.sibling != null &&  nextX.sibling.degree == x.degree)) {
        // Case 1: When current and next have the same degree
        // Case 2: When current, next and next.sibling have the same degree
        prevX = x;
        x = nextX;
      }
      else if (x.key <= nextX.key) {
        // Case 3: When x.key is less than nextX.key
        x.sibling = nextX.sibling;
        binomialLink(nextX, x); // nextX linked to X
      }
      else if (prevX == null) {
        // Case 4: When x.key is greater than nextX.key, but previous is null
        H.head = nextX;
        binomialLink(x, nextX); // x linked to nextX
        x = nextX;
      }
      else {
        // Case 4: When x.key is greater than nextX.key
        prevX.sibling = nextX;
        binomialLink(x, nextX); // x linked to nextX
        x = nextX;
      }
      nextX = x.sibling;
    }
    return H;
  }

  // Assumes that key has already been given
  public void insert(Node x) {
    BinomialHeap b = new BinomialHeap();
    b.head = x;
    this.head = binomialHeapUnion(this, b).head;
  }

  //Extract node with minimum key
  public Node extractMin() {
      Node prev = null;
      Node curr = this.head;
      Node minPrev = null;
      Node min = this.head;
      while(curr != null) {
        if (curr.key < min.key) {
          minPrev = prev;
          min = curr;
        }
        prev = curr;
        curr = curr.sibling;
      }
      if (minPrev == null && prev != null) {
        this.head = head.sibling;
      }
      else if (prev != null) {
        minPrev.sibling = min.sibling;
      }
      BinomialHeap b = new BinomialHeap();
      Node y = min.child;
      if(y != null) {
        try{
        min.child = null;
        while (y != null) {
          y.parent = null;
          Node temp = y.sibling;
          y.sibling = b.head;
          b.head = y;
          y = temp;
        }
        this.head = binomialHeapUnion(this, b).head;
        }
      catch (NullPointerException npe) {
        throw new IllegalStateException("No more objects in the heap.");
      }
      }
      return min;
  }

  public void decreaseKey(int oldKey, int key) {
    Node x = findNode(this.head, oldKey);
    if (x == null) {
      throw new IllegalArgumentException("Node with given key not found");
    }
    if (key > x.key) {
      throw new IllegalArgumentException("New key greater than current key");
    }
    x.key = key;
    Node y = x;
    Node z = y.parent;
    while(z != null && y.key < z.key) {
      int temp = y.key;
      y.key = z.key;
      z.key = temp;
      y = z;
      z = y.parent;
    }
  }

  public void delete(int key) {
    this.decreaseKey(key, Integer.MIN_VALUE);
    this.extractMin();
  }

  public void checkSizes() {
    Node curr = head;
    int i = 0;
    while(curr != null) {
      curr = curr.sibling;
      i++;
    }
  }

  private Node findNode(Node x, int key) {
    Node curr = x;
    while(curr != null) {
      if (curr.key == key) {
        return curr;
      }
      Node ret = findNode(curr.child, key);
      if (ret != null) {
        return ret;
      }
      curr = curr.sibling;
    }
    return null;
  }

  private void toStringHelper(Node x, Appendable out) {
    try {
      if (x != null) {
        out.append(String.valueOf(x.key)).append(" ");
        toStringHelper(x.child, out);
        toStringHelper(x.sibling, out);
      }
    }
     catch (IOException ignored) {
    }
  }

  @Override
  public String toString() {
    Appendable out = new StringBuilder();
    toStringHelper(this.head, out);
    return out.toString();
  }

}
