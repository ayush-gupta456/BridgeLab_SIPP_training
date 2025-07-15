public class StringConcatPerformance {
    public static String concatWithString(int n) {
        String result = "";
        for (int i = 0; i < n; i++) result += "a";
        return result;
    }

    public static String concatWithBuilder(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("a");
        return sb.toString();
    }

    public static String concatWithBuffer(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("a");
        return sb.toString();
    }

    public static void main(String[] args) {
        int N = 10_000;

        long start = System.nanoTime();
        concatWithString(N);
        long end = System.nanoTime();
        System.out.println("String: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        concatWithBuilder(N);
        end = System.nanoTime();
        System.out.println("StringBuilder: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        concatWithBuffer(N);
        end = System.nanoTime();
        System.out.println("StringBuffer: " + (end - start) / 1_000_000.0 + " ms");
    }
}