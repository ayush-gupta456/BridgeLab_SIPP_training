import java.util.function.Predicate;

public class TemperatureAlert {
    public static void main(String[] args) {
        Predicate<Double> isHigh = t -> t > 37.5;
        System.out.println(isHigh.test(36.5));
        System.out.println(isHigh.test(38.0));
    }
}
