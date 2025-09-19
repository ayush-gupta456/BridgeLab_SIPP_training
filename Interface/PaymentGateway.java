interface PaymentProcessor {
    void process(double amount);
    default void refund(double amount) {
        System.out.println("Refund " + amount + " processed");
    }
}

class Paytm implements PaymentProcessor {
    public void process(double amount) { System.out.println("Paytm payment " + amount); }
}

class PhonePe implements PaymentProcessor {
    public void process(double amount) { System.out.println("PhonePe payment " + amount); }
}

public class PaymentGateway {
    public static void main(String[] args) {
        PaymentProcessor p = new Paytm();
        p.process(1000);
        p.refund(500);
    }
}
