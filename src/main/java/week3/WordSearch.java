package week3;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordSearch {


  private int rows;
  private int cols;
  /*
   * Given a grid of letters and a dictionary, find all the words from the dictionary that can be
   * formed in the grid.The rules for forming a word:
   *    ● You can start at any position.
   *    ● You can move to one of the 8 adjacent cells (horizontally/vertically/diagonally).
   *    ● You can't visit the same cell twice in the same word.Assume you have a dictionary class
   *      with these two methods:
   *    ● isWord(string): Returns whether the given string is a valid word.
   *    ● isPrefix(string): Returns whether the given string is a prefix of at least one word in
   *      the dictionary.
   *
   * Your function receives the number of rows, number of columns, a 2-dimensional array of
   * characters (of the native char data type), and the dictionary. You should return the set of all
   * words found.
   */


  /*
   * Iteratively try to find longer words
   * initializeMap = get all valid starting points on the grid (valid prefixes of length 1)
   *
   * Iteration: while there are longer words found, try to append the next character to the valid
   * prefixes in the set.
   */
  public Set<String> findWords(char[][] grid, Dictionary dictionary) {

    if (grid == null || dictionary == null) {
      return new HashSet<>();
    }

    Set<String> words = new HashSet<>();
    Map<String, List<Path>> prefixes = initializeMap(dictionary, grid);

    while (prefixes.size() > 0) {
      Map<String, List<Path>> newPrefixes = new HashMap<>();

      prefixes.forEach((p, paths) ->
          newPrefixes.putAll(
              paths
                  .stream()
                  .flatMap(c -> getUnvisitedAdjCells(c).stream())
                  .filter(c -> {
                    String newPrefix = p + grid[c.x][c.y];

                    if (dictionary.isWord(newPrefix)) {
                      words.add(newPrefix);
                    }

                    return dictionary.isPrefix(newPrefix);
                  })
                  .collect(Collectors.groupingBy(c -> p + grid[c.x][c.y]))
          )
      );

      prefixes = newPrefixes;
    }

    return words;
  }

  private void setRowsAndCols(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return;
    }

    rows = grid.length;
    cols = grid[0].length;
  }


  /*
   * Method returns all valid adjacent coordinates of c w.r.t grid and current path
   *
   * All Paths returned:
   *  1) Append a new cell to the end of path c
   *  2) New cell has not been visited
   *  3) New cell is within the grid
   *  3) New cell is adjacent to the last cell in path c
   */
  List<Path> getUnvisitedAdjCells(Path c) {

    if (c == null) {
      return null;
    }

    List<Path> coordinates = new ArrayList<>();

    for (int x = -1; x <= 1; x++) {
      for (int y = -1; y <= 1; y++) {
        int newX = c.x + x;
        int newY = c.y + y;
        if (newX >= 0 && newX < rows
            && newY >= 0 && newY < cols
            && !c.locs.get(newY * rows + newX)) {

          coordinates.add(new Path(newX, newY, (BitSet) c.locs.clone()));
        }

      }
    }

    return coordinates;
  }

  /*
   * Initialize the Map with all paths/prefixes of length 1.
   * Each entry
   * 1) the key is found in the grid and isPrefix to some word in the dictionary
   * 2) the entry all locations of the key
   */
  Map<String, List<Path>> initializeMap(Dictionary dict, char[][] grid) {

    if (dict == null || grid == null) {
      return null;
    }

    setRowsAndCols(grid);

    Map<String, List<Path>> letters = new HashMap<>();

    //empty string is a prefix of all words by default
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        String prefix = String.valueOf(grid[r][c]);

        if (dict.isPrefix(prefix)) {
          letters.putIfAbsent(prefix, new ArrayList<>());
          letters.get(prefix).add(new Path(r, c));
        }
      }
    }

    return letters;
  }

  private class Path {

    public int x;
    public int y;
    public BitSet locs = new BitSet(rows * cols);

    public Path(int x, int y) {
      this.x = x;
      this.y = y;
      this.locs.set(y * rows + x);
    }

    public Path(int x, int y, BitSet locs) {
      this.locs = locs;
      this.x = x;
      this.y = y;
      this.locs.set(y * rows + x);
    }


    @Override
    public String toString() {
      return x + " " + y + " " + locs.toString();
    }


  }
}
