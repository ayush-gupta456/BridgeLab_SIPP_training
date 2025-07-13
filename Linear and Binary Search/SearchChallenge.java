package searchproblems;

import java.util.*;

public class SearchChallenge {
    public static int findFirstMissingPositive(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (num > 0) set.add(num);
        }
        for (int i = 1; i <= arr.length + 1; i++) {
            if (!set.contains(i)) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}