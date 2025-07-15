import java.util.Arrays;

public class LinearVsBinarySearch {
    public static boolean linearSearch(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) return true;
        }
        return false;
    }

    public static boolean binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] data = new int[1_000_000];
        for (int i = 0; i < data.length; i++) data[i] = i;

        int target = 999_999;

        long start = System.nanoTime();
        boolean foundLinear = linearSearch(data, target);
        long end = System.nanoTime();
        System.out.println("Linear Search: " + foundLinear + ", Time: " + (end - start) / 1_000_000.0 + " ms");

        Arrays.sort(data);
        start = System.nanoTime();
        boolean foundBinary = binarySearch(data, target);
        end = System.nanoTime();
        System.out.println("Binary Search: " + foundBinary + ", Time: " + (end - start) / 1_000_000.0 + " ms");
    }
}