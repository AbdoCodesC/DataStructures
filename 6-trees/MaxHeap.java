/*
    heap - in place
 */

import java.util.ArrayList;

public class MaxHeap {

    public static void heapify (int [] nums) {
        int lastParentIndex = nums.length / 2 - 1;
        for (int i = lastParentIndex; i >= 0; i--) {
            heapify(nums, i);
        }
    }

    private static void heapify (int [] nums, int index) {
        int largerIndex = index;
        int leftIndex = index * 2 + 1;
        if (leftIndex < nums.length && nums[leftIndex] > nums[largerIndex]) {
            largerIndex = leftIndex;
        }
        int rightIndex = index * 2 + 2;
        if (rightIndex < nums.length && nums[rightIndex] > nums[largerIndex]) {
            largerIndex = rightIndex;
        }

        if (index == largerIndex) return;

        swap(nums, index, largerIndex);
        heapify(nums, largerIndex);
    }

    private static void swap (int [] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static int kthLargestItem (int [] nums, int k) {
        if (k < 1 || k > nums.length) throw new IllegalArgumentException();

        Heap heap = new Heap();
        for (int num: nums)
            heap.insert(num);

        for (int i = 0; i < k - 1; i++)
            heap.remove();

        return heap.max();
    }

}
