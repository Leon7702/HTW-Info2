package lecture;

public class BinarySearch {


    public static int binarySearch(int[] arr, int key) {
        if (arr[arr.length / 2] == key) {
            return arr.length / 2;
        } else if (arr[arr.length / 2] < key) {
            for (int i = arr.length / 2; i < arr.length; i++) {
                if (arr[i] == key) return i;
            }
        } else if (arr[arr.length / 2] > key) {
            for (int i = 0; i < arr.length / 2; i++) {
                if (arr[i] == key) return i;
            }
        }
        return -1;
    }

    public static void main(String[] a){
        int[] a1= {10,20,30,50,70,90};
        int key = 10;
        System.out.println(key + " is found at index: " + binarySearch(a1, key));
    }
}

