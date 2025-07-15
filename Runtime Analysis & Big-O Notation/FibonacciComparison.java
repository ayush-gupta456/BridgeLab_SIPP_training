public class FibonacciComparison {
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static void main(String[] args) {
        int N = 30;

        long start = System.nanoTime();
        int rec = fibonacciRecursive(N);
        long end = System.nanoTime();
        System.out.println("Recursive: Result=" + rec + ", Time=" + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        int itr = fibonacciIterative(N);
        end = System.nanoTime();
        System.out.println("Iterative: Result=" + itr + ", Time=" + (end - start) / 1_000_000.0 + " ms");
    }
}
