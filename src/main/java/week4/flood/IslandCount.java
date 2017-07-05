package week4.flood;


import java.util.Set;
import java.util.TreeSet;
import week4.IslandCountI;

public class IslandCount implements IslandCountI {

  /*
   * We go through each cell, if it is set (it is an island),
   * we increase the count and reset all the tiles in that island
   *
   * Time Complexity: O(nm)
   * Space Complexity: O(nm)
   */
  public int countIslands(boolean[][] map) {

    if (map == null || map.length == 0) {
      return 0;
    }

    boolean[][] visited = new boolean[map.length][map[0].length];
    int count = 0;
    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[r].length; c++) {
        if (map[r][c] && !visited[r][c]) {
          count++;
          grayOutIsland(map, visited, r, c);
        }
      }
    }
    return count;
  }

  /* sets all adjacent tiles of an island to false */
  private void grayOutIsland(boolean[][] map, boolean[][] visited, int r, int c) {
    Set<Tile> tiles = new TreeSet<>();
    tiles.add(new Tile(r, c));
    while (!tiles.isEmpty()) {
      Tile next = tiles.iterator().next();

      if (map[next.r][next.c] && !visited[next.r][next.c]) {
        visited[next.r][next.c] = true;

        if (next.r > 0 && !visited[next.r - 1][next.c]) {
          tiles.add(new Tile(next.r - 1, next.c));
        }

        if (next.r < map.length - 1 && !visited[next.r + 1][next.c]) {
          tiles.add(new Tile(next.r + 1, next.c));
        }

        if (next.c > 0 && !visited[next.r][next.c - 1]) {
          tiles.add(new Tile(next.r, next.c - 1));
        }

        if (next.c < map[r].length - 1 && !visited[next.r][next.c + 1]) {
          tiles.add(new Tile(next.r, next.c + 1));
        }
      }

      tiles.remove(next);
    }
  }

  private class Tile implements Comparable {

    int r;
    int c;

    Tile(int r, int c) {
      this.r = r;
      this.c = c;
    }

    @Override
    public int compareTo(Object o) {
      //assumption no nulls
      Tile t1 = (Tile) o;

      if (t1.r == this.r) {
        return this.c - t1.c;
      } else {
        return this.r - t1.r;
      }
    }
  }
}