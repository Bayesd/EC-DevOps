import java.util.Scanner;

public class Calculator
{
    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Select mathemamtical operation or type 'q' to quit:");
            String operation = sc.nextLine();
            switch (operation) {
                case "q":
                    isRunning = false;
                    break;
                case "+":
                    try {
                        System.out.println("Enter first number: ");
                        double num1 = sc.nextDouble();
                        System.out.println("Enter second number: ");
                        double num2 = sc.nextDouble();
                        double addResult = addition(num1, num2);
                        System.out.println("Result: " + num1 + " + " + num2 + " = " + addResult);
                        } catch (Exception e) {
                         System.out.println("Something went wrong.");
                        }
                    break;
                case "-":
                    try{
                        System.out.println("Enter first number: ");
                        double x = sc.nextDouble();
                        System.out.println("Enter second number: ");
                        double y = sc.nextDouble();
                        double subResult = subtraction(x, y);
                        System.out.println("Result: " + x + " - " + y + " = " + subResult);
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                    break;
                case "*":
                    try {
                        System.out.println("Enter first number: ");
                        double a = sc.nextDouble();
                        System.out.println("Enter second number: ");
                        double b = sc.nextDouble();
                        double mulResult = multiplication(a, b);
                        System.out.println("Result: " + a + " x " + b + " = " + mulResult);
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                    break;
                case "/":
                    try {
                        System.out.println("Enter first number: ");
                        double c = sc.nextDouble();
                        System.out.println("Enter second number: ");
                        double d = sc.nextDouble();
                        double divResult = division(c, d);
                        System.out.println("Result: " + c + " / " + d + " = " + divResult);
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                    break;
                default:
                    System.out.println("Select one of the operators avaliable by typing +, - , * or / and press enter");
                    break;
            }
        }

       /*

     public static int intOrDouble (double result)
            if ((result % 1) == 0) {
            int intResult = (int) result;
            System.out.println(intResult);
        } else { 
            System.out.println(result);
        }*/
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
