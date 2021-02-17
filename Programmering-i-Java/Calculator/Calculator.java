import java.util.Scanner;

public class Calculator
{
    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose mathematical operation or type 'q' to quit:");
            String operation = sc.nextLine();
            switch (operation) {
                case "q": case "quit": case "Q": case "Quit":
                    isRunning = false;
                    sc.close();
                    break;
                case "+": case "add": case "addition": case "plus": case "Add": case "Addition": case "Plus":
                     try {
                        System.out.println("Enter first number: ");
                        double num1 = sc.nextDouble();
                        System.out.println("Enter second number: ");
                        double num2 = sc.nextDouble();
                        double addResult = addition(num1, num2);
                        if (addResult < 2147483647 && addResult % 1 == 0) {
                            int intAddResult = (int) addResult;
                            System.out.println("Result: " + intAddResult);
                        } else {
                            System.out.println("Result: " + addResult);
                        }
                        } catch (Exception e) {
                         System.out.println("Something went wrong.");
                        }
                    break;
                case "-": case "minus": case "Minus": case "subtraction": case "Subtraction": case "take": case "Take":
                    try{
                        System.out.println("Enter first number: ");
                        double x = sc.nextDouble();
                        System.out.println("Enter second number: ");
                        double y = sc.nextDouble();
                        double subResult = subtraction(x, y);
                        if (subResult < 2147483647 && subResult % 1 == 0) {
                            int intSubResult = (int) subResult;
                            System.out.println("Result: " + intSubResult);
                        } else {
                            System.out.println("Result: " + subResult);
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                    break;
                case "*": case "x": case "Multiply": case "multiply": case "Multiplication": case "multiplication": case "times": case "Times":
                    try {
                        System.out.println("Enter first number: ");
                        double a = sc.nextDouble();
                        System.out.println("Enter second number: ");
                        double b = sc.nextDouble();
                        double mulResult = multiplication(a, b);
                        if (mulResult < 2147483647 && mulResult % 1 == 0) {
                            int intMulResult = (int) mulResult;
                            System.out.println("Result: " + intMulResult);
                        } else {
                            System.out.println("Result: " + mulResult);
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                    break;
                case "/": case "Divide": case "divide": case "Division": case "division": case "Div": case "div":
                    try {
                        System.out.println("Enter first number: ");
                        double c = sc.nextDouble();
                        System.out.println("Enter second number: ");
                        double d = sc.nextDouble();
                        double divResult = division(c, d);
                        if (divResult < 2147483647 && divResult % 1 == 0) {
                            int intDivResult = (int) divResult;
                            System.out.println("Result: " + intDivResult);
                        } else {
                            System.out.println("Result: " + divResult);
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                default:
                    System.out.println("Select one of the operators avaliable by typing +, - , * or / and press enter\n");
                    break;
            }
        } 
    }
     public static double addition(double num1, double num2) {
        double result = num1 + num2;
        return result;
    }
    public static double subtraction(double x, double y) {
        double result = x - y;
        return result;
    }

    public static double multiplication(double a, double b) {
        double result = a * b;
        return result;
    }

    public static double division(double c, double d) {
        double result = c / d;
        return result;
    }
}
