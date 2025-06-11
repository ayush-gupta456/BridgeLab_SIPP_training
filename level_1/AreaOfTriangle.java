import java.util.Scanner;
class AreaOfTriangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double base = input.nextDouble();
        double height = input.nextDouble();
        double areaInInch = 0.5 * base * height;
        double areaInCm = areaInInch * 6.4516;
        System.out.println("Area of triangle is " + areaInInch + " square inches and " + areaInCm + " square centimeters");
    }
}