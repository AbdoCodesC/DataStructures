import java.util.Arrays;

/*
    Select the minimum value then put it in the beginning
    best O(n^2)
    worst O(n^2)
 */
public class SelectionSort {
    public static void main(String[] args) {
        int [] arr = {110, 1, 100, 45,10, 1000};
        sortRec(arr, 0 , arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort (int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++){
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    public static void sortRec (int [] arr, int l, int h) {
        if (l >= h) return; // if l >= h then return

        int minIndex = l; // minIndex <- l
        for (int i = l+1; i < h; i++ ) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        swap(arr, minIndex, l);
        sortRec(arr, l + 1, h);
    }

    public static void swap (int [] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
