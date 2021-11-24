import hashmap.HashMap;
import hashmap.Word;

import java.io.*;

public class Driver {

  public static void main(String[] args) throws IOException {
    HashMap H = new HashMap();
    File file = new File("alice_in_wonderland.txt");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    StringBuilder allWords = new StringBuilder();
    while ((st = br.readLine()) != null) {
      allWords.append("\n").append(st);
    }
    for(String k: allWords.toString().toLowerCase().split("[ ,.?!\"\\[\\]()'-_\n#&%$]")) {
      if(!k.isEmpty()) {
        if(H.find(k)) {
          H.increase(k);
        }
        else {
          H.insert(k, 1);
        }
      }
    }
    H.insert("Dheeraj", 5);
    H.delete("Dheeraj");
    try(
        FileWriter f1 = new FileWriter("allWords.txt", true);
        BufferedWriter bw1 = new BufferedWriter(f1);
        PrintWriter allKeys = new PrintWriter(bw1);
        FileWriter f2 = new FileWriter("wordsPerIndex.txt", true);
        BufferedWriter bw2 = new BufferedWriter(f2);
        PrintWriter wordsPerIndex = new PrintWriter(bw2)
    )
    {
      H.listAllKeys(allKeys);
      H.printWordsPerRow(wordsPerIndex);
    } catch (IOException ignored) {
    }

  }
}
