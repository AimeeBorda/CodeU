package week4.unionFind;


import java.util.Arrays;
import week4.IslandCountI;

public class IslandCountUF implements IslandCountI {

  /*
   * We perform a union find, where for every set cell we try to merge it with neighbouring (set)
   * cells. The UF is held as flat as possible to make retrieving the root more efficient. This made
   * sense since we are going through the cells in a sequential order.
   *
   * Time Complexity: O(nm) - iterate over the map 3 times to set array, main process, count roots
   * Space Complexity: O(nm) - size of the UF
   */
  public int countIslands(boolean[][] map) {

    if (map == null || map.length == 0) {
      return 0;
    }

    int rows = map.length;
    int cols = map[0].length;
    int[] uf = new int[rows * cols];

    Arrays.setAll(uf, i -> i); //O(n*m)

    for (int r = 0; r < rows; r++) {                                               //O(n)
      for (int c = 0; c < cols; c++) {                                             //O(m)
        int index = index(r, c, cols);

        if (map[r][c]) {                                                           //O(1)
          if (r > 0 && map[r - 1][c]) { //O(1)
            merge(uf, index(r - 1, c, cols), index);                               //O(1)
          }

          if (c > 0 && map[r][c - 1]) {
            merge(uf, index(r, c - 1, cols), index);
          }

          if (r < rows - 1 && map[r + 1][c]) {
            merge(uf, index, index(r + 1, c, cols));
          }

          if (c < cols - 1 && map[r][c + 1]) {
            merge(uf, index, index(r, c + 1, cols));
          }
        } else {
          uf[index(r, c, cols)] = -1;
        }
      }
    }

    //counts roots/islands
    int count = 0;
    for (int i = 0; i < uf.length; i++) {                                          //O(n*m)
      if (uf[i] == i) {
        count++;
      }
    }

    return count;
  }

  /*
   * gets root of current cluster and flattens the structure
   *
   * Time Complexity: O(1) due to flatten
   * Space Complexity: O(1)
   */
  private int root(int[] uf, int index) {
    if (uf[index] != index) {
      uf[index] = root(uf, uf[index]);
    }

    return uf[index];
  }

  /*
   * converts the index from 2D map to 1D for uf
   *
   * Time Complexity: O(1)
   * Space Complexity: O(1)
   */
  private int index(int r, int c, int cols) {
    return r * cols + c;
  }

  /*
   * Merges two clusters/islands
   *
   * Time Complexity: O(1)
   * Space Complexity: O(1)
   */
  private void merge(int[] uf, int source, int dest) {
    uf[root(uf, dest)] = root(uf, source);
  }
}