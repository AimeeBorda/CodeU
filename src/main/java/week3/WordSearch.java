package week3;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
public class WordSearch {

  /*
   * Iteratively try to find longer words
   * 1. initializeStack = get all valid starting points on the grid (valid prefixes of length 1)
   * 2. Iteration: while the stack is not empty, try to append the next character to a valid
   * prefix in the stack.
   *
   * Time Complexity: Initialization + (number of iterations * cost of each iteration)
   *                  O(|grid|*|words|) + (O(|paths|) * O(|words|))
    *                  O(|paths|*|words|)
   *
   *      where |grid| = rows*cols is the size of the grid and
   *            |Words| = size of the dictionary / number of words in the dictionary
   */
  public Set<String> findWords(char[][] grid, Dictionary dictionary) {

    if (grid == null || dictionary == null || grid.length == 0) {
      return new HashSet<>();
    }

    Set<String> words = new HashSet<>();
    Stack<Path> prefixes = initializeStack(dictionary, grid);

    while (!prefixes.isEmpty()) {
      Path p = prefixes.pop();                       //O(1)

      getUnvisitedAdjCells(p, grid)                  //O(1)
          .forEach(c -> {                            //O(1) at worst it is 8 iterations
            if (dictionary.isPrefix(c.word)) {       //O(|words|)

              //by definition a word is a prefix as well
              if (dictionary.isWord(c.word)) {      //O(|words|)
                words.add(c.word);                  //O(1)
              }

              prefixes.push(c);                     //O(1)
            }
          });
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
  List<Path> getUnvisitedAdjCells(Path path, char[][] grid) {

    if (path == null) {
      return null;
    }

    List<Path> coordinates = new ArrayList<>();

    int rows = grid.length;
    int cols = grid[0].length;

    for (int dr = -1; dr <= 1; dr++) {
      for (int dc = -1; dc <= 1; dc++) {

        int r = path.x + dr;
        int c = path.y + dc;
        int l = r * cols + c;

        if (r >= 0 && r < rows && c >= 0 && c < cols && !path.locs.get(l)) {
          coordinates.add(
              new Path(r, c, path.word + grid[r][c], (BitSet) path.locs.clone(), l));
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
  Stack<Path> initializeStack(Dictionary dict, char[][] grid) {

    int cols = grid[0].length;
    int rows = grid.length;

    Stack<Path> letters = new Stack<>();

    //empty string is a prefix of all words by default
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        String prefix = String.valueOf(grid[r][c]);

        if (dict.isPrefix(prefix)) {
          letters.push(new Path(r, c, prefix, new BitSet(rows * cols), r * cols + c));
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