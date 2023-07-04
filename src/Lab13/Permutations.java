package Lab13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    public static List<String> generatePermutations(String string) {
        List<String> results = new ArrayList<>();
        char[] characters = string.toCharArray();
        Arrays.sort(characters); // Sort the characters to handle duplicates
        boolean[] used = new boolean[string.length()];
        generatePermutationsHelper(characters, new StringBuilder(), used, results);
        return results;
    }

    private static void generatePermutationsHelper(char[] characters, StringBuilder currentPermutation, boolean[] used, List<String> results) {
        if (currentPermutation.length() == characters.length) {
            results.add(currentPermutation.toString());
            return;
        }

        for (int i = 0; i < characters.length; i++) {
            // Skip if the character is already used in the current permutation
            if (used[i])
                continue;

            // Skip if the character is the same as the previous character and it has not been used yet
            if (i > 0 && characters[i] == characters[i - 1] && !used[i - 1])
                continue;

            used[i] = true;
            currentPermutation.append(characters[i]);
            generatePermutationsHelper(characters, currentPermutation, used, results);
            currentPermutation.deleteCharAt(currentPermutation.length() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        String input = "aab";
        List<String> permutations = generatePermutations(input);

        System.out.println("Permutations of " + input + ":");
        for (String permutation : permutations) {
            System.out.println(permutation);
        }
    }
}
