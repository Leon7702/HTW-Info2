package Lab13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Binomial {

    public static Bag<String> generateSubStrings(String s, int k) {
        Bag<String> subStrings = new Bag<>();
        generateSubStringHelper(s, k, 0, new StringBuilder(), subStrings);
        return subStrings;
    }

    private static void generateSubStringHelper(String s, int k, int startIndex, StringBuilder currentString, Bag<String> subStrings) {
        if (currentString.length() == k) {
            subStrings.add(currentString.toString());
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            currentString.append(s.charAt(i));
            generateSubStringHelper(s, k, i + 1, currentString, subStrings);
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }

    public static Bag<String> generateAllSubStrings(String s) {
        Bag<String> subStrings = new Bag<>();
        Bag<String> subStringsSorted = new Bag<>();

        String normalizedWord = normalizeWord(s).toUpperCase();
        subStrings.add(normalizedWord);

        for (int i = s.length() - 1; i > 1; i--) {
            Bag<String> bag = generateSubStrings(s, i);
            subStrings.addAll(bag);
        }

        for (String string : subStrings) {
            String sortedString = normalizeWord(string).toUpperCase();

            if (!subStringsSorted.contains(sortedString)) {
                subStringsSorted.add(sortedString);
            }
        }

        return subStringsSorted;
    }


    private static String normalizeWord(String input) {
        // Normalize a word by converting it to lowercase and sorting its characters
        char[] charArray = input.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public static void main(String[] args) {

        Bag<String> subStrings = generateAllSubStrings("JAVA");

        System.out.println("All substrings:");
        for (String string : subStrings) {
            System.out.println(string);
        }
    }
}
