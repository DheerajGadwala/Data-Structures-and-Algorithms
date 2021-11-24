package hashmap;

import java.io.PrintWriter;
import java.util.Objects;
import java.util.Random;

public class HashMap {

  private static final int TABLE_SIZE = 431;
  private final Word[] table;
  private final int prime;

  public HashMap() {
    table = new Word[TABLE_SIZE];
    int[] primes = new int[]{11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541};
    this.prime = new Random().nextInt(primes.length-1);
  }

  private int hash(String word) {
    int hash=0;
    int i = 0;
    for(long c: word.toCharArray()) {
      hash += (c - 96) * (Math.pow(prime, i) % TABLE_SIZE);
      i++;
    }
    //System.out.println(word + " " + hash + " " + hash%TABLE_SIZE);
    return hash % TABLE_SIZE;
    //return (Objects.hashCode(word)%TABLE_SIZE+TABLE_SIZE)%TABLE_SIZE;
  }

  public boolean find(String word) {
    int key = hash(word);
    Word head = table[key];
    while (head != null) {
      if (head.equalsWord(word)) {
        return true;
      }
      head = head.getNext();
    }
    return false;
  }

  public void insert(String word, int value) {
    int key = hash(word);
    Word head = table[key];
    if (this.find(word)) {
      throw new IllegalArgumentException("HashMap already contains the given key");
    }
    else {
      table[key] = new Word(key, value, word, table[key]);
    }
  }

  public void increase(String word) {
    int key = hash(word);
    Word head = table[key];
    if (this.find(word)) {
      while (head != null) {
        if (head.equalsWord(word)) {
          head.incrementCount();
        }
        head = head.getNext();
      }
    }
    else {
      throw new IllegalArgumentException("HashMap does not contain this key");
    }
  }

  public void delete(String word) {
    int key = hash(word);
    Word head = table[key];
    if (this.find(word)) {
      Word prev = null;
      while (head != null) {
        if (head.equalsWord(word)) {
          if (prev == null) {
            table[key] = table[key].getNext();
          }
          else {
            prev.setNext(head.getNext());
          }
        }
        prev = head;
        head = head.getNext();
      }
    }
  }

  public void listAllKeys(PrintWriter out) {

    for (Word head: table) {
      while (head != null) {
        out.println(head.toString());
        head = head.getNext();
      }
    }
  }

  public void printWordsPerRow(PrintWriter out) {
    long i=0;
    for (Word head : table) {
      out.printf("row: %d, words: %d\n", i, head == null ? 0 : head.getLength());
      i++;
    }
  }

}
