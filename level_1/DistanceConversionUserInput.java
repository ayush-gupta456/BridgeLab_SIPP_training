import java.util.Scanner;
class DistanceConversionUserInput {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double km;
        km = input.nextDouble();
        double miles = km / 1.6;
        System.out.println("The total miles is " + miles + " mile for the given " + km + " km");
    }
}