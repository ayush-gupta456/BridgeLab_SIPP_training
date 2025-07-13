package searchproblems;

public class SearchMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / cols, col = mid % cols;
            int midVal = matrix[row][col];

            if (midVal == target) return true;
            else if (target < midVal) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
}