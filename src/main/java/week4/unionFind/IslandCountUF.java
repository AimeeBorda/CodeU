package week4.unionFind;


import java.util.Arrays;
import java.util.HashSet;
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

    int rows = map.length;
    int cols = map[0].length;
    int[] uf = new int[rows * cols];

    Arrays.setAll(uf, i -> i);
    HashSet<Integer> roots = new HashSet<>();
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (map[r][c]) {

          int index = index(r, c, cols);
          int currentRoot = root(uf, index);

          boolean firstRow = r == 0 || !map[r - 1][c];
          boolean lastRow = r == rows - 1 || !map[r + 1][c];
          boolean firstCol = c == 0 || !map[r][c - 1];
          boolean lastCol = c == cols - 1 || !map[r][c + 1];

          //singleton island
          if (firstRow && lastRow && firstCol && lastCol) {
            roots.add(index);
          }

          if (!firstRow && root(uf, index(r - 1, c, cols)) != currentRoot) {
            merge(uf, index(r - 1, c, cols), index, roots);
          }

          if (!lastRow && root(uf, index(r + 1, c, cols)) != currentRoot) {
            merge(uf, index, index(r + 1, c, cols), roots);
          }

          if (!firstCol && root(uf, index(r, c - 1, cols)) != currentRoot) {
            merge(uf, index(r, c - 1, cols), index, roots);
          }

          if (!lastCol && root(uf, index(r, c + 1, cols)) != currentRoot) {
            merge(uf, index, index(r, c + 1, cols), roots);
          }
        }
      }
    }

    return roots.size();
  }

  private int root(int[] uf, int index) {
    while (index >= 0 && uf[index] != index) {
      index = uf[index];
    }

    return index;
  }

  private int index(int r, int c, int cols) {
    return r * cols + c;
  }

  private void merge(int[] uf, int source, int dest, HashSet<Integer> roots) {
    int rs = root(uf, source);
    int rd = root(uf, dest);

    uf[rd] = rs;

    roots.remove(rd);
    roots.add(rs);
  }
}