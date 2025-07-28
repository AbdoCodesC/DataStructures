/*
  Ram - byte -> 8 bits
  int 4byte
  char 1byte

  Lookup O(1)
  Insert O(n)
  Remove O(n)
*/

import java.util.Arrays;

class Array {
    int count = 0;
    int [] arr;
    public Array (int size) {
        this.arr = new int [size];
    }

    @Override
    public String toString(){
        return Arrays.toString(Arrays.copyOfRange(arr, 0, count));
    }

    private void grow(){
        int [] newArr = new int[count*2];
        for (int i = 0; i < count; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
        System.out.println("array doubled.");
    }
    /// /////////////////////////////////////
    public void insertBegin (int value) {
        if (count == arr.length) {
            grow();
        }
        for (int i = arr.length-2; i >= 0; i--) {
            arr[i+1] = arr[i];
        }
        arr[0] = value;
        count++;
    }

    public void insertEnd (int value) { // default arr
        if (count == arr.length) {
            grow();
        }
        arr[count++] = value;
    }

    public void insertMiddle (int index, int value) {
        if (count == arr.length) {
            grow();
        }
        if (index < 0 || index > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            insertBegin(value);
            return;
        }
        if (index == count) {
            insertEnd(value);
            return;
        }
        for (int i = arr.length-2; i >= index; i--) {
            arr[i+1] = arr[i];
        }
        arr[index] = value;
        count++;
    }
    /// /////////////////////////////////////
    public void removeFirst(){
        for (int i = 0; i < arr.length-1; i++) {
            arr[i] = arr[i+1];
        }
        count--;
    }

    public void removeEnd(){
        arr[count--] = 0;
    }

    public void remove (int index) {
        if (index < 0 || index > arr.length) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {removeFirst();return;}
        if (index == count) {removeEnd();return;}

        for (int i = index; i < arr.length-1; i++) {
            arr[i] = arr[i+1];
        }
        count--;
    }
    //////////////////////////////////////////////////
    public int indexOf(int value) {
        for (int i = 0; i < count; i++) {
            if (value == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Array arr = new Array(5);
        arr.insertBegin(1);
        arr.insertBegin(2);
        arr.insertBegin(3);
        arr.remove(1);
        System.out.println(arr);
    }
}