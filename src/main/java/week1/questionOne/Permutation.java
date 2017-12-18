package week1.questionOne;


import java.util.HashMap;

class Permutation {

  /*
   * Assumptions: 1) All Characters fall within BMP,
   *              2) permutations are case-insensitive,
   *              3) each character occurs at most Integer.MAX_VALUE (2^32)
   * Space Complexity: O(1) (regardless of length of string the hash map will have at most 2^16 elements)
   * Time Complexity: O(N) where N is the length of the strings
   */
  public boolean arePermutations(String firstStr, String secondStr) {

    if (firstStr == null || secondStr == null || firstStr.length() != secondStr.length()) {
      return false;
    }
      HashMap<Character, Integer> occurrences = new HashMap<>();

      for (Character c : firstStr.toLowerCase().toCharArray()) {
        occurrences.put(c, 1 + occurrences.getOrDefault(c, 0));
      }

      for (Character c : secondStr.toLowerCase().toCharArray()) {
        Integer prevFreq = occurrences.put(c, occurrences.getOrDefault(c, 0) - 1);
        if (prevFreq == null || prevFreq == 0) {
          return false;
        }
      }

      /*This point is reached iff
       * 1) strings are of the same length (otherwise return in if branch is executed)
       * 2) All characters in the second string occur with the same frequency as the first string
       *    (otherwise return in second for loop is executed)
       *
       * Both condition implies that the strings are permutations of each other
       */
      return true;
    }

}
