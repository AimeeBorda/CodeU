package week4.flood;


import java.util.HashSet;
import java.util.Set;
import week4.IslandCountI;

public class IslandCount implements IslandCountI {

  /*
   * We go through each cell, if it is set (it is an island),
   * we increase the count and reset all the tiles in that island
   *
   * Time Complexity: O(nm)
   * Space Complexity: O(1)
   */
  public int countIslands(boolean[][] map) {

    if (map == null || map.length == 0) {
      return 0;
    }

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

  /* sets all adjacent tiles of an island to false */
  private void grayOutIsland(boolean[][] map, int r, int c) {
    Set<Tile> tiles = new HashSet<>();
    int[] dx = new int[]{0, 0, -1, 1};
    int[] dy = new int[]{-1, 1, 0, 0};

    tiles.add(new Tile(r, c));
    while (!tiles.isEmpty()) {
      Tile next = next(tiles);
      if (getAndReset(map, next)) {
        for (int i = 0; i < dx.length; i++) {
          int newX = next.x + dx[i];
          int newY = next.y + dy[i];

          if (newX >= 0 && newX < map.length
              && newY >= 0 && newY < map[0].length
              && map[newX][newY]) {
            tiles.add(new Tile(newX, newY));
          }
        }
      }
    }
  }

  private Tile next(Set<Tile> tiles) {
    Tile next = tiles.iterator().next();
    tiles.remove(next);
    return next;
  }

  private boolean getAndReset(boolean[][] map, Tile next) {
    boolean b = map[next.x][next.y];
    map[next.x][next.y] = false;
    return b;
  }

  private class Tile {

    int x;
    int y;

    Tile(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      return this.x == ((Tile) o).x && this.y == ((Tile) o).y;
    }
  }
}