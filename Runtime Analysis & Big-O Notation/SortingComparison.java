import java.util.Arrays;

public class SortingComparison {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void main(String[] args) {
        int size = 10_000;
        int[] data1 = new int[size];
        int[] data2 = new int[size];
        int[] data3 = new int[size];
        for (int i = 0; i < size; i++) data1[i] = data2[i] = data3[i] = size - i;

        long start = System.nanoTime();
        bubbleSort(data1);
        long end = System.nanoTime();
        System.out.println("Bubble Sort: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        mergeSort(data2);
        end = System.nanoTime();
        System.out.println("Merge Sort: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        Arrays.sort(data3);
        end = System.nanoTime();
        System.out.println("Quick Sort (Arrays.sort): " + (end - start) / 1_000_000.0 + " ms");
    }
}