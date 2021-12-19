
public class FlowNetwork {

  int [][] capacity;
  int [][] preFlow;
  int [] height;
  int [] excess;
  int s, t;


  public FlowNetwork(int vertices) {
    capacity = new int[vertices][vertices];
    preFlow = new int[vertices][vertices];
    height = new int[vertices];
    excess = new int[vertices];
  }

  public void addEdge(int u, int v, int c) {
    capacity[u][v] = c;
  }

  void initializePreFlow(int s, int t) {
    this.s = s;
    this.t = t;
    int n = capacity.length;
    height[s] = n - 1;
    for (int u = 0; u < n; u ++) {
      preFlow[s][u] = capacity[s][u];
      preFlow[u][s] = -preFlow[s][u];
      excess[u] = capacity[s][u];
    }
  }

  int residualCapacity(int u, int v) {
    return capacity[u][v] - preFlow[u][v];
  }

  boolean isPushable(int u, int v) {
    return height[u] == height[v] + 1 && residualCapacity(u, v) > 0;
  }

  void push(int u, int v) {
    int delta = Math.min(residualCapacity(u, v), excess[u]);
    preFlow[u][v] += delta;
    preFlow[v][u] -= delta;
    excess[u] -= delta;
    excess[v] += delta;
  }

  void relabel(int u) {
    height[u] = Integer.MAX_VALUE;
    for (int v = 0; v < capacity.length; v++) {
      if (
          height[u] > height[v] + 1
          &&
          capacity[u][v] - preFlow[u][v] > 0
      ) {
        height[u] = height[v] + 1;
      }
    }
  }

  int getMaxFlow(int source, int sink) {
    initializePreFlow(source, sink);
    int n = capacity.length;
    int[] stack = new int[n];
    for (int pointer = 0;;) {
      // Find overflowing nodes
      for (int u = 0; u < n; u++)
        if (u != source && u != sink && excess[u] > 0) {
          if (height[u] > height[stack[0]])
            pointer = 0;
          stack[pointer] = u;
          pointer++;
        }
      if (pointer == 0)
        break;
      while (pointer != 0) {
        int u = stack[pointer - 1];
        boolean pushed = false;
        //push to neighbours if possible
        for (int v = 0; v < n && excess[u] != 0; v++) {
          if (isPushable(u, v)) {
            push(u, v);
            pushed = true;
            if (excess[u] == 0) {
              pointer--;
              break;
            }
          }
        }
        // relabel
        if (!pushed) {
          relabel(u);
          if (height[u] > height[stack[0]]) {
            pointer = 0;
            break;
          }
        }
      }
    }
    int maxFlow = 0;
    for (int v = 0; v < n; v++)
      maxFlow += preFlow[source][v];
    return maxFlow;
  }

}
