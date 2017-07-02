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
          int index = index(r, c, cols);
          int root = root(uf, index);
          boolean temp = root == index;

          if (r > 0 && map[r - 1][c]
              && root(uf, index(r - 1, c, cols)) != root) {
            temp &= merge(uf, index(r - 1, c, cols), index);
          }

          if (r < rows - 1 && map[r + 1][c]
              && root(uf, index(r + 1, c, cols)) != root) {
            temp &= merge(uf, index, index(r + 1, c, cols));
          }

          if (c > 0 && map[r][c - 1]
              && root(uf, index(r, c - 1, cols)) != root) {
            temp &= merge(uf, index(r, c - 1, cols), index);
          }

          if (c < cols - 1 && map[r][c + 1]
              && root(uf, index(r, c + 1, cols)) != root) {
            temp &= merge(uf, index, index(r, c + 1, cols));
          }

          if (temp) {
            count++;
          }
        }
      }
    }

    return count;
  }

  private int root(int[] uf, int index) {
    while (index > 0 && uf[index] != index) {
      index = uf[index];
    }

    return index;
  }

  private int index(int r, int c, int cols) {
    return r * cols + c;
  }

  private boolean merge(int[] uf, int source, int dest) {
    int rs = root(uf, source);
    int rd = root(uf, dest);

    uf[rd] = uf[rs];

    return rs == source && rd == dest;
  }

}
