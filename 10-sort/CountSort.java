import java.util.Arrays;

public class CountSort {
    public static void main(String[] args) {

        int [] arr = {110, 1, 100, 45,10, 1000};
        sort(arr, 1000);
        System.out.println(Arrays.toString(arr));

    }

    public static void sort (int [] arr, int max) {
        int [] counts = new int [max+1];

        for (int item: arr) {
            counts[item]++;
        }

        int k = 0;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < counts[i]; j++) {
                arr[k++] = i;
            }
        }

    }
}
