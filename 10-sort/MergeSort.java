import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int [] arr = {110, 1, 100, 45,10, 1000};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort (int [] arr) {
        // base case
        if (arr.length < 2) {
            return;
        }

        // divide this arr into half
        int mid = arr.length / 2;
        int [] left = new int[mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }

        int [] right = new int[arr.length-mid];
        for (int i = mid; i < arr.length; i++) {
            right[i-mid] = arr[i];
        }

        // sort each half
        sort(left);
        sort(right);

        // merge the result
        merge(left, right, arr);
    }

    public static void merge (int [] left, int [] right, int [] res){
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                res[k++] = left[i++];
            } else {
                res[k++] = right[j++];
            }
        }

       while (i < left.length) {
            res[k++] = left[i++];
       }

        while (j < right.length) {
            res[k++] = right[j++];
        }
    }
}
