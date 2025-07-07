package SlidingWindowMaximum;

import java.util.*;

public class SlidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        int idx = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1)
                deque.poll();
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast();
            deque.offer(i);
            if (i >= k - 1)
                result[idx++] = nums[deque.peek()];
        }
        return result;
    }
}