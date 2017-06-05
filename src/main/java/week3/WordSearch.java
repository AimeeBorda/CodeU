package week3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


  public Set<String> findWords(char[][] grid, Dictionary dictionary) {

    int oldSize = 0;
    Set<String> words = new HashSet<>();
    Map<String, List<Coordinate>> prefixes = initializeMap(grid);

    while (prefixes.size() != oldSize) {
      oldSize = prefixes.size();

      HashMap<String, List<Coordinate>> temp = new HashMap<>();

      prefixes.forEach((p, cs) ->
          temp.putAll(
              cs.stream()
                  .flatMap(c -> getValidateAdjacentCordinates(c, grid))
                  .filter(c -> {
                    String newPrefix = p + grid[c.x][c.y];

                    if (dictionary.isWord(newPrefix)) {
                      words.add(newPrefix);
                    }

                    if (dictionary.isPrefix(newPrefix)) {
                      System.out.println(newPrefix);
                    }
                    return dictionary.isPrefix(newPrefix);
                  })
                  .collect(Collectors.groupingBy(c -> p + grid[c.x][c.y]))));

      prefixes = temp;
    }

    return words;
  }


  /*
   * Method returns all valid adjacent coordinates of c w.r.t grid
   */
  private Stream<Coordinate> getValidateAdjacentCordinates(Coordinate c, char[][] grid) {
    List<Coordinate> coordinates = new ArrayList<>();

    for (int x = -1; x <= 1; x++) {
      for (int y = -1; y <= 1; y++) {

        Coordinate n = c.coordinateAt(x, y);
        if (n.x >= 0 && n.x < grid.length
            && n.y >= 0 && n.y < grid[0].length
            && (x != 0 || y != 0)) {
          coordinates.add(n);
        }

      }
    }

    return coordinates.stream();
  }

  private HashMap<String, List<Coordinate>> initializeMap(char[][] grid) {

    //empty string is a prefix of all words by default
    List<Coordinate> coordinates = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        coordinates.add(new Coordinate(r, c));
      }
    }

    return new HashMap<String, List<Coordinate>>() {{
      put("", coordinates);
    }};

  }

  private class Coordinate {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public Coordinate coordinateAt(int dx, int dy) {
      return new Coordinate(this.x + dx, this.y + dy);
    }
  }
}
