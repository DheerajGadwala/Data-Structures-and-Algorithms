import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class extractMin {

  @Test
  public void testExtractMin() {
    int i = 0;
    Random rand = new Random();
    BinomialHeap b = new BinomialHeap();
    ArrayList<Integer> ar = new ArrayList<>();
    while(i<100) {
      int x = rand.nextInt(1000);
      b.insert(new Node(x));
      ar.add(x);
      i++;
    }
    Collections.sort(ar);
    i=0;
    while(i < 100) {
      int extracted = b.extractMin().key;
      int expected = ar.get(i);
      assertEquals(expected, extracted);
      i++;
    }
  }
}
