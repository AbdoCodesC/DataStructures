import java.util.Arrays;

/*

    it bubbles up, it doesnt stop till it reaches the place it needs to be at

 */

public class BubbleSort {
    public static void main(String[] args) {
        int [] arr = {110, 1, 100, 45,10, 1000};
        System.out.println(sort(arr));
        System.out.println(Arrays.toString(arr));
    }

    public static boolean sort (int [] arr) { // o(n^2)
        boolean isSorted;
        for (int i = 0; i < arr.length; i++) {
            isSorted = true;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) return true;
        }
        return false;
    }
}
