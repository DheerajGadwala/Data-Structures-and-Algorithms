package hashmap;

public class Word {
  private int key;
  private int count;
  private String word;
  private Word next;

  public Word(int key, int count, String word, Word next) {
    this.key = key;
    this.count = count;
    this.word = word;
    this.next = next;
  }

  public int getKey() {
    return key;
  }

  public int getCount() {
    return count;
  }

  public Word getNext() {
    return next;
  }

  public String getWord() {
    return word;
  }

  public void setNext(Word next) {
    this.next = next;
  }

  public void incrementCount() {
    count++;
  }

  public boolean equalsWord(String that) {
    return this.getWord().equals(that);
  }

  public int getLength() {
    return next == null ? 1 : 1 + next.getLength();
  }

  @Override
  public String toString() {
    return String.format("word: %s, count: %d", getWord(), getCount());
  }

  public String getAll() {
    return word + " " + (next == null ? "" : next.getAll());
  }
}