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
    public boolean arePermutations(String firstStr, String secondStr){

        if(firstStr ==null || secondStr ==null || firstStr.length() != secondStr.length()){
            return false;
        }else{
            HashMap<Character, Integer> occurrences = new HashMap<>();

            for(Character c : firstStr.toLowerCase().toCharArray()){
                occurrences.put(c ,1 + occurrences.getOrDefault(c,0));
            }

            for(Character c : secondStr.toLowerCase().toCharArray()){
                if(occurrences.computeIfPresent(c, (k, v) -> v > 0 ? v - 1 : null) == null)
                    return false;
            }

            return true;
        }
    }
}
