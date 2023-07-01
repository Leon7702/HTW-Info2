package lab12;
public class Prime {

    public static void main(String[] args) {

        int n = 4;

        if(isPrime(n)) {
            System.out.println(n + " is a Prime.");
        }else {
            System.out.println(n + " is not a Prime.");
        }
    }

    private static boolean isPrime(int number) {

        int div = 2;
        while (div < number) {

            if (number % div == 0) {

                return false;
            }
            div++;
        }

        return true;
    }
}

