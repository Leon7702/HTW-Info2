package lab07;

import java.util.Scanner;

public class Postfix {

    public static boolean operand(char a) {
        return (a== '0' || a=='1' || a=='2' || a=='3' || a=='4' || a=='5' || a=='6'
                || a=='7'|| a=='8'|| a == '9');
    }

    public static int getPriority(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    public static double evaluate (String postfix) throws OverflowException, UnderflowException {
        StackAsList<String> s = new StackAsList<String>();

        for(int i=0; i< postfix.length(); i++) {
            if(operand(postfix.charAt(i))) {
                s.push(postfix.charAt(i)+"");
            } else {
                String rightSide = s.top();
                s.pop();
                String leftSide = s.top();
                s.pop();

                double result = 0;
                switch(postfix.charAt(i)) {
                    case '*':
                        result = Double.valueOf(leftSide) * Double.valueOf(rightSide);
                        break;
                    case '+':
                        result = Double.valueOf(leftSide) + Double.valueOf(rightSide);
                        break;
                    case '-':
                        result = Double.valueOf(leftSide) - Double.valueOf(rightSide);
                        break;
                    case '/':
                        result = Double.valueOf(leftSide) / Double.valueOf(rightSide);
                        break;
                    case '^':
                        result =  Math.pow(Double.valueOf(leftSide), Double.valueOf(rightSide));
                        break;
                }

                s.push(result + "");
            }
        }
        return Double.valueOf(s.top());
    }

    public static String infixToPostfix(String infix) throws OverflowException, UnderflowException, NonSenseException {

        StringBuilder sb = new StringBuilder();
        StackAsList<Character> stack = new StackAsList<Character>();

        for (int i = 0; i < infix.length(); i++) {
            char current = infix.charAt(i);

            if (operand(current)) {
                sb.append(current);
            } else if (current == '(') {
                stack.push(current);
            } else if (current == ')') {
                while (!stack.isEmpty() && stack.top() != '(') {
                    sb.append(stack.top());
                    stack.pop();
                }

                if (stack.isEmpty() || stack.top() != '(') {
                    throw new NonSenseException("Wrong input :(.");
                }

                stack.pop();
            } else {
                while (!stack.isEmpty() && getPriority(current) <= getPriority(stack.top())) {
                    sb.append(stack.top());
                    stack.pop();
                }
                stack.push(current);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.top() == '(') {
                throw new NonSenseException("Wrong input :(.");
            }
            sb.append(stack.top());
            stack.pop();
        }

        return sb.toString();
    }

    public static void calcInput() throws OverflowException, UnderflowException, NonSenseException {
        Scanner inputReader = new Scanner(System.in);
        String input = null;
        System.out.println("Enter an infix expression with no spaces: ");
        input = inputReader.next();
        String pfx;
        pfx = infixToPostfix(input);
        System.out.println(evaluate(pfx));

    }


    public static void main(String args[]) throws OverflowException, UnderflowException, NonSenseException {

//		calcInput();
//		String s = "a+b^c*d^e^f-g-h/(i+j)";
//		System.out.println(infixToPostfix(s));




    }
}
