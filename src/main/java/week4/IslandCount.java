package week4;


public class IslandCount {

  /*
   * We go through each cell, if cell is part of an island then
   * 1) if it is the top-left corner (cell above it and to its left are false or out-of-bounds)
   *    of an island we increment the counter
   * 2) We need to correct the counter if the island has two "top-left" corners, this happens
   *            false, true
   *            true, false
   *    In this case, counter is incremented for cells (0,1) and (1,0). The counter is decremented
   *    when both cell above it and to its left are true but the cell NW is false. In this example
   *    the counter is decremented for cell (1,1)
   * 3) we do not change the counter for an inner cell of the island
   *
   * Time Complexity: O(n*m)
   * Space Complexity: O(1)
   */
  public static int countIslands(final boolean[][] map) {

    if (map == null || map.length == 0) {
      return 0;
    }

    int count = 0;

    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[r].length; c++) {
        if (map[r][c]) {
          if ((r == 0 || !map[r - 1][c]) && (c == 0 || !map[r][c - 1])) {
            count++;
          } else if (r > 0 && map[r - 1][c] && c > 0 && map[r][c - 1] && !map[r - 1][c - 1]) {
            count--;
          }
        }
      }
    }

    return count;
  }


}
