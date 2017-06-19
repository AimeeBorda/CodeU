package week4;


public class IslandCount {

  /*
   * We go through each cell, if cell is part of an island then
   * 1) if it is the top-left corner of an island we increment the counter
   * 2) If the island is in the shape of vertically mirrored L then we decrement the counter
   *    on reaching cell (1,1) to make up for the fact that we have two top left corners (0,1) (1,0)
   *
   * Time Complexity: O(n*m)
   * Space Complexity: O(1)
   */
  public static int countIslands(boolean[][] map) {

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
