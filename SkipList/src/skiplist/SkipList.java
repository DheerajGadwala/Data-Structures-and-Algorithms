package skiplist;

import java.util.Random;

public class SkipList {
  Node start;
  Node end;
  Random random = new Random();

  public SkipList() {
    start = new Node(Integer.MIN_VALUE);
    start.right = new Node(Integer.MAX_VALUE);
    start.right.left = start;
    end = start.right;
  }

  private int coinFlips() {
    int count = 1;
    while (random.nextInt(2) == 0) {
      count++;
    }
    return count;
  }

  public boolean lookUp(int key) {
    Node curr = start;
    while (curr != null) {
      if (curr.value == key) {
        return true;
      }
      curr = curr.right.value > key ? curr.down : curr.right;
    }
    return false;
  }

  public void insert(int key) {
    Node curr = start;
    int n = coinFlips();
    Node trace = null;
    while (curr != null) {
      if (curr.right.value == key) {
        return; // Already added
      }
      trace = curr;
      curr = curr.right.value > key ? curr.down : curr.right;
    }
    curr = trace;
    boolean addNow = true;
    Node prevInsert = null;
    while (n > 0) {
      if(addNow) {
        Node k = new Node(key);
        curr.right.left = k;
        k.right = curr.right;
        curr.right = k;
        k.left = curr;
        if (prevInsert != null) {
          prevInsert.up = k;
          k.down = prevInsert;
        }
        addNow = false;
        prevInsert = k;
        n--;
      }
      else if (curr.up != null) {
        curr = curr.up;
        addNow = true;
      }
      else if (curr.left != null){
        curr = curr.left;
      }
      else {
        curr.up = new Node(Integer.MIN_VALUE);
        curr.up.down = curr;
        curr = curr.up;
        curr.right = new Node(Integer.MAX_VALUE);
        curr.right.left = curr;
        curr.right.down = end;
        end.up = curr.right;
        start = curr;
        end = curr.right;
        addNow = true;
      }
    }
  }

  public void delete(int key) {
    Node curr = start;
    while (curr != null) {
      if (curr.value == key) {
        while(curr != null) {
          Node tempL = curr.left;
          Node tempR = curr.right;
          tempL.right = tempR;
          tempR.left = tempL;
          curr = curr.down;
        }
        break;
      }
      curr = curr.right.value > key ? curr.down : curr.right;
    }
    while(start.right == end && start.down != null) {
      start = start.down;
      start.up = null;
      end = end.down;
      end.up = null;
    }
  }

  public String print() {
    StringBuilder ret = new StringBuilder();
    Node curr = start;
    int height = 1;
    int length = 1;
    while (curr.down != null) {
      curr = curr.down;
      height++;
    }
    Node startTemp = curr;
    while(curr.right != null) {
      curr = curr.right;
      length++;
    }
    int[][] keys = new int[length][height];
    int l = 0;
    while (startTemp != null) {
      Node temp = startTemp;
      int h = 0;
      while(temp != null) {
        keys[l][h] = temp.value;
        h++;
        temp = temp.up;
      }
      l++;
      startTemp = startTemp.right;
    }
    for(int i=0; i<height; i++) {
      StringBuilder t = new StringBuilder();
      for(int j=0; j<length; j++) {
        String k;
        if(keys[j][i] == Integer.MAX_VALUE) {
          k = ("i");
        }
        else if(keys[j][i] == Integer.MIN_VALUE) {
          k = ("-i");
        }
        else if (keys[j][i]==0) {
          k = ("");
        }
        else {
          k = String.valueOf(keys[j][i]);
        }
        t.append(String.format("%-5s", k));
      }
      ret.insert(0, t+"\n");
    }
    return ret.toString();
  }

}
