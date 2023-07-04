package preLab;

import java.util.ArrayList;
import java.util.List;

public class Binomial {

    public static List<String> generateKToppings(String toppings, int k) {
        List<String> kToppings = new ArrayList<>();
        generateKToppingsHelper(toppings, k, 0, new StringBuilder(), kToppings);
        return kToppings;
    }

    private static void generateKToppingsHelper(String toppings, int k, int startIndex, StringBuilder currentTopping, List<String> kToppings) {
        if (currentTopping.length() == k) {
            kToppings.add(currentTopping.toString());
            return;
        }

        for (int i = startIndex; i < toppings.length(); i++) {
            currentTopping.append(toppings.charAt(i));
            generateKToppingsHelper(toppings, k, i + 1, currentTopping, kToppings);
            currentTopping.deleteCharAt(currentTopping.length() - 1);
        }
    }

    public static void main(String[] args) {
        String toppings = "AAJV";
        int k = 2;

        List<String> kToppings = generateKToppings(toppings, k);

        System.out.println("All " + k + "-Toppings:");
        for (String topping : kToppings) {
            System.out.println(topping);
        }
    }
}
