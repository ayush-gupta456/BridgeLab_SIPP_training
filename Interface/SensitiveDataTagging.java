interface Sensitive {}

class UserData implements Sensitive {
    String info;
    UserData(String i) { info = i; }
}

public class SensitiveDataTagging {
    public static void main(String[] args) {
        UserData u = new UserData("Secret123");
        if (u instanceof Sensitive) {
            System.out.println("Data is sensitive, apply encryption");
        }
    }
}
