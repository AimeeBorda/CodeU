package week4;


public class IslandCount {

  /*
   * We go through each cell, if it is set (it is an island),
   * we increase the count and reset all the tiles in that island
   *
   * Time Complexity:
   * Space Complexity: O(1)
   */
  public static int countIslands(boolean[][] map) {

    int count = 0;
    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[r].length; c++) {
        if (map[r][c]) {
          count++;
          grayOutIsland(map, r, c);
        }
      }
    }
    return count;
  }

  /* sets all adjacent tiles of an island to false
   *
   * Time Complexity:
   */
  private static void grayOutIsland(boolean[][] map, int r, int c) {
    if (r >= 0 && r < map.length && c >= 0 && c < map[0].length && map[r][c]) {
      map[r][c] = false;

      grayOutIsland(map, r - 1, c);
      grayOutIsland(map, r + 1, c);
      grayOutIsland(map, r, c - 1);
      grayOutIsland(map, r, c + 1);
    }
  }
}
