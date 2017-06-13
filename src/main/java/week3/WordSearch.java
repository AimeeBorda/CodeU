package week3;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {

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
   * initializeSet = get all valid starting points on the grid (valid prefixes of length 1)
   *
   * Iteration: while there are longer words found, try to append the next character to the valid
   * prefixes in the set.
   *
   * Time Complexity: Initialization + (number of iterations * cost of each iteration)
   *                  O(|grid|*Words) + (O(|grid|) * O(|grid|*|grid| * |words|))
    *                  O(|grid|^3*|words|)
   *
   *      where |grid| = rows*cols is the size of the grid and
   *            |Words| = size of the dictionary / number of words in the dictionary
   *
   *  Intuitively, at worst the whole grid forms a word and thus we iterate over the while loop
   *  |grid| times.
   *
   *  In each iteration, we can have all the grid being a prefix of some word, and the processing of
   *  each path involves O(1) to get adjacent cells and O(|words|) to validate newPrefix
   */
  public Set<String> findWords(char[][] grid, Dictionary dictionary) {

    if (grid == null || dictionary == null || grid.length == 0) {
      return new HashSet<>();
    }

    Set<String> words = new HashSet<>();
    Set<Path> prefixes = initializeSet(dictionary, grid);

    while (prefixes.size() > 0) {
      Set<Path> temp = new HashSet<>();

      prefixes.forEach(p ->
          getUnvisitedAdjCells(p, grid)
              .forEach(c -> {
                if (dictionary.isWord(c.word)) {
                  words.add(c.word);
                }

                if (dictionary.isPrefix(c.word)) {
                  temp.add(c);
                }
              }));

      prefixes = temp;
    }

    return words;
  }

  /*
   * Method returns all valid adjacent coordinates of c w.r.t grid and current path
   *
   * All Paths returned:
   *  1) Append a new cell to the end of path c
   *  2) New cell has not been visited
   *  3) New cell is within the grid
   *  3) New cell is adjacent to the last cell in path c
   *
   *  N.B not test independently because of Path
   *
   *  Time Complexity : O(1) for loops do a constant number of iterations and
   *  all operations either run in constant time (if, bitset check) or are negligible
   *  (coordinates.add as arraylist will at most have size 8)
   */
  List<Path> getUnvisitedAdjCells(Path c, char[][] grid) {

    if (c == null) {
      return null;
    }

    List<Path> coordinates = new ArrayList<>();

    int rows = grid.length;
    int cols = grid[0].length;

    for (int x = -1; x <= 1; x++) {
      for (int y = -1; y <= 1; y++) {

        int newX = c.x + x;
        int newY = c.y + y;

        if (newX >= 0 && newX < rows
            && newY >= 0 && newY < grid[0].length
            && !c.locs.get(newX * cols + newY)) {

          coordinates.add(new Path(
              newX,
              newY,
              c.word + grid[newX][newY],
              (BitSet) c.locs.clone(),
              newX * cols + newY));
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
   *
   * Time Complexity: O(|grid|*|words|)
    *   where |grid| is the size of your grid (rows*cols) / time complexity of the nested for loop
    *         |words| is the size of your dictionary / time complexity of isPrefix
   */
  Set<Path> initializeSet(Dictionary dict, char[][] grid) {

    int cols = grid[0].length;
    int rows = grid.length;

    Set<Path> letters = new HashSet<>();

    //empty string is a prefix of all words by default
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        String prefix = String.valueOf(grid[r][c]);

        if (dict.isPrefix(prefix)) {
          letters.add(new Path(r, c, prefix, new BitSet(rows * cols), r * cols + c));
        }
      }
    }

    return letters;
  }

  private static class Path {

    int x;
    int y;
    String word;
    BitSet locs;

    Path(int x, int y, String word, BitSet locs, int l) {
      this.locs = locs;
      this.x = x;
      this.y = y;
      this.word = word;

      this.locs.set(l);
    }
  }
}