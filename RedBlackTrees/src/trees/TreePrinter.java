package trees;

import java.util.ArrayList;
import java.util.List;

//https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java

public class TreePrinter
{
  /** Node that can be printed */
  public interface PrintableNode
  {
    /** Get left child */
    PrintableNode getLeft();


    /** Get right child */
    PrintableNode getRight();


    /** Get text to be printed */
    String getText();
  }


  /**
   * Print a tree
   *
   * @param root
   *            tree root node
   */
  public static String print(PrintableNode root)
  {
    String output = "";
    List<List<String>> lines = new ArrayList<List<String>>();

    List<PrintableNode> level = new ArrayList<PrintableNode>();
    List<PrintableNode> next = new ArrayList<PrintableNode>();

    level.add(root);
    int nn = 1;

    int widest = 0;

    while (nn != 0) {
      List<String> line = new ArrayList<String>();

      nn = 0;

      for (PrintableNode n : level) {
        if (n == null) {
          line.add(null);

          next.add(null);
          next.add(null);
        } else {
          String aa = n.getText();
          line.add(aa);
          if (aa.length() > widest) widest = aa.length();

          if (n == Sentinel.getInstance()) {
            next.add(null);
            next.add(null);
          }
          else {
            next.add(n.getLeft());
            next.add(n.getRight());
            nn+=2;
          }
        }
      }

      if (widest % 2 == 1) widest++;

      lines.add(line);

      List<PrintableNode> tmp = level;
      level = next;
      next = tmp;
      next.clear();
    }

    int perpiece = lines.get(lines.size() - 1).size() * (widest);
    for (int i = 0; i < lines.size(); i++) {
      List<String> line = lines.get(i);
      int hpw = (int) Math.floor(perpiece / 2f) - 1;

      if (i > 0) {
        for (int j = 0; j < line.size(); j++) {

          // split node
          char c = ' ';
          if (j % 2 == 1) {
            if (line.get(j - 1) != null) {
              c = (line.get(j) != null) ? '┴' : '┘';
            } else {
              if (j < line.size() && line.get(j) != null) c = '└';
            }
          }
          output+=c;

          // lines and spaces
          if (line.get(j) == null) {
            for (int k = 0; k < perpiece - 1; k++) {
              output+=" ";
            }
          } else {

            for (int k = 0; k < hpw; k++) {
              output+=(j % 2 == 0 ? " " : "─");
            }
            output+=(j % 2 == 0 ? "┌" : "┐");
            for (int k = 0; k < hpw; k++) {
              output+=(j % 2 == 0 ? "─" : " ");
            }
          }
        }
        output+=("\n");
      }

      // print line of numbers
      for (int j = 0; j < line.size(); j++) {

        String f = line.get(j);
        if (f == null) f = "";
        int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
        int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

        // a number
        for (int k = 0; k < gap1; k++) {
          output+=(" ");
        }
        output+=(f);
        for (int k = 0; k < gap2; k++) {
          output+=(" ");
        }
      }
      output+=("\n");

      perpiece /= 2;
    }
    return output;
  }
}