package week4.unionFind;


import java.util.Arrays;
import week4.IslandCountI;

public class IslandCountUF implements IslandCountI {

  /*
   * We go through each cell, if it is the top-left corner of an island,
   * we increase the count if it is part of an island, we merge it in the UF DS.
   * We do a second pass through the map to correct over-estimated count
   * (they are connected through right or bottom tile)
   *
   * Time Complexity: O(nm) - pass through the map twice
   * Space Complexity: O(nm) - the size of the map = size of the UF DS
   */
  public int countIslands(boolean[][] map) {

    if (map == null || map.length == 0) {
      return 0;
    }

    int count = 0;
    int rows = map.length;
    int cols = map[0].length;
    int[] uf = new int[rows * cols];

    Arrays.setAll(uf, i -> i);

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (map[r][c]) {
          if (r > 0 && map[r - 1][c]) {
            merge(uf, index(r, c, cols), index(r - 1, c, cols));
          } else if (c > 0 && map[r][c - 1]) {
            merge(uf, index(r, c, cols), index(r, c - 1, cols));
          } else {
            count++;
          }
        }
      }
    }

    //corrects count of islands that are connected with tile below or to its right only
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (map[r][c]) {
          if (r < rows - 1
              && map[r + 1][c]
              && uf[index(r + 1, c, cols)] != uf[index(r, c, cols)]) {
            merge(uf, index(r, c, cols), index(r + 1, c, cols));
            count--;
          } else if (c < cols - 1
              && map[r][c + 1]
              && uf[index(r, c + 1, cols)] != uf[index(r, c, cols)]) {
            count--;
            merge(uf, index(r, c, cols), index(r, c + 1, cols));
          }
        }
      }
    }
    return count;
  }

  private int index(int r, int c, int cols) {
    return r * cols + c;
  }

  private void merge(int[] uf, int dest, int source) {
    uf[dest] = uf[source];
  }

}
