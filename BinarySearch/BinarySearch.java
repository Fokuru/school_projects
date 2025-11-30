
public class BinarySearch {
    public static void main (String[] args) {
    
        Integer[] data1 = {0, 2, 5, 8, 10, 38, 50, 56, 59, 62, 64};

        System.out.println(binarySearch(data1, 0, data1.length, 2));
        System.out.println(binarySearch(data1, 0, data1.length, 11));
        System.out.println(binarySearch(data1, 0, data1.length, 64));
        System.out.println(binarySearch(data1, 0, data1.length, 0));
    
    }

        
    public static <T extends Comparable<T>> boolean binarySearch(T[] data, int min, int max, T target) {
        if (min >= max) {
            System.out.println("hit end with "+min+" "+max);
            return false;
        }
        System.out.println("looking at "+data[(max+min)/2]);
        if (data[(max+min)/2].compareTo(target) == 0) {
            return true;
        }  else if(data[(max+min)/2].compareTo(target) > 0) {
            max = (max+min)/2;
            return binarySearch(data, min, max, target);
        } else if (data[(max+min)/2].compareTo(target) < 0) {
            min = (max+min)/2 + 1;
            return binarySearch(data, min, max, target);
        }else {
            System.out.println("hit end with "+min+" "+max);
            return false;
        }
    }
}