interface Payment {
    void pay(double amount);
}

class UPI implements Payment {
    public void pay(double amount) { System.out.println("Paid " + amount + " via UPI"); }
}

class CreditCard implements Payment {
    public void pay(double amount) { System.out.println("Paid " + amount + " via Credit Card"); }
}

class Wallet implements Payment {
    public void pay(double amount) { System.out.println("Paid " + amount + " via Wallet"); }
}

public class DigitalPayment {
    public static void main(String[] args) {
        Payment p1 = new UPI();
        Payment p2 = new CreditCard();
        Payment p3 = new Wallet();
        p1.pay(1000);
        p2.pay(2000);
        p3.pay(500);
    }
}
