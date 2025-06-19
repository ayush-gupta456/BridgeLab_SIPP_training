import java.util.Scanner;
import java.util.Random;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int low = 1, high = 100;
        while (true) {
            int guess = (low + high) / 2;
            System.out.println("Is your number " + guess + "? (high/low/correct)");
            String feedback = sc.next();
            if (feedback.equals("correct")) break;
            else if (feedback.equals("high")) high = guess - 1;
            else if (feedback.equals("low")) low = guess + 1;
        }
    }
}