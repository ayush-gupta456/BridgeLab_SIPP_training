import java.util.HashSet;
import java.util.TreeSet;

public class DataStructureSearchComparison {
    public static boolean searchInArray(int[] arr, int target) {
        for (int val : arr) if (val == target) return true;
        return false;
    }

    public static boolean searchInHashSet(HashSet<Integer> set, int target) {
        return set.contains(target);
    }

    public static boolean searchInTreeSet(TreeSet<Integer> set, int target) {
        return set.contains(target);
    }

    public static void main(String[] args) {
        int N = 1_000_000;
        int[] array = new int[N];
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            array[i] = i;
            hashSet.add(i);
            treeSet.add(i);
        }

        int target = N - 1;

        long start = System.nanoTime();
        boolean a = searchInArray(array, target);
        long end = System.nanoTime();
        System.out.println("Array Search: " + a + ", Time: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        boolean h = searchInHashSet(hashSet, target);
        end = System.nanoTime();
        System.out.println("HashSet Search: " + h + ", Time: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        boolean t = searchInTreeSet(treeSet, target);
        end = System.nanoTime();
        System.out.println("TreeSet Search: " + t + ", Time: " + (end - start) / 1_000_000.0 + " ms");
    }
}
