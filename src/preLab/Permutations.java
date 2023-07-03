package preLab;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static List<String> generatePermutations(String string) {
        List<String> results = new ArrayList<>();
        generatePermutationsHelper(string, "", string.length(), results);
        return results;
    }

    private static void generatePermutationsHelper(String string, String currentPermutation, int length, List<String> results) {
        if (currentPermutation.length() == length) {
            results.add(currentPermutation);
            return;
        }

        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);

            // Skip if the character is already used in the current permutation
            if (currentPermutation.indexOf(ch) != -1)
                continue;

            String remaining = string.substring(0, i) + string.substring(i + 1);
            generatePermutationsHelper(remaining, currentPermutation + ch, length, results);
        }
    }

    public static void main(String[] args) {
        String input = "abc";
        List<String> permutations = generatePermutations(input);

        System.out.println("Permutations of " + input + ":");
        for (String permutation : permutations) {
            System.out.println(permutation);
        }
    }
}

