public class Driver {
  public static void main(String[] args) {

    int[][] capacity = {
        { 0, 4, 2, 1, 2, 7},
        { 0, 0, 2, 1, 0, 3},
        { 0, 0, 0, 1, 3, 5},
        {0, 0, 0, 0, 4, 0},
        {0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0}
    };
    int n = capacity.length;
    FlowNetwork flow = new FlowNetwork(n);
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        if (capacity[i][j] != 0)
          flow.addEdge(i, j, capacity[i][j]);
    System.out.println(flow.getMaxFlow(0, 5));

  }
}
