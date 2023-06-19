package lab07;

public class Main {

    public static void main(String[] args) throws UnderflowException, OverflowException {

        String s = "1";
        String t = "2";
        String r = "3";

        StackAsList<String> stack = new StackAsList<String>();
        stack.push("hello");
        stack.push("bye");
        System.out.println(stack.toString());


    }

}
