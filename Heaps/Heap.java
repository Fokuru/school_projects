public class Heap {

//the actual storage structure for your heap

private int[] arr;
 

//constructor for your heap

//feel free to make one that takes in an array if you prefer for your testing purposes.

//note that we will not be inserting more than 100 elements into our array so you need not worry about re-sizing

//the array

// Pre: root is an integer
// Post: creates a heap with the given root, otherwise empty
public Heap(int root) {

    arr = new int[1];
    arr[0] = root;

}

// Pre: arr is an array of integers
// Post: creates a heap with the given array as the underlying storage
public Heap(int[] arr) {
    this.arr = arr;
}

 

//create this function to add elements to your heap

//all heap properties must be preserved

// 5 points

// Pre: toAdd is an integer
// Post: adds the given element to the heap and maintains heap properties
public void add(int toAdd) {
    if (toAdd <= 0) {
        System.out.println("Can't add " + toAdd + ", sorry!");
        return;
    }

    System.out.println("Adding " + toAdd + " to " + this.toString());

    int[] newArr = new int[arr.length + 1];
    for (int i = 0; i < arr.length; i++) {
        newArr[i] = arr[i];
    }
    arr = newArr;

    
    int index = arr.length - 1;
    arr[index] = toAdd;

    siftUp(index);
    
 

}

//remove the largest element of the heap (the root) and re-heapafy

//5 points

// Pre: none
// Post: removes the maximum element from the heap and maintains heap properties
public void removeMax() {
    if (arr.length == 0) {
        return;
    }
    arr[0] = arr[arr.length - 1];
    int[] newArr = new int[arr.length - 1];
    for (int i = 0; i < newArr.length; i++) {
        newArr[i] = arr[i];
    }
    arr = newArr;

    siftDown(0);
}


 

//this should check and alter the tree after an item is inserted

//3 points

// Pre: index is an integer
// Post: sifts up the element at the given index to maintain heap properties
private void siftUp(int index) {
    if (index == 0) {
        return;
    }
    int parentIndex = (index - 1) / 2;
    if (arr[index] > arr[parentIndex]) {
        int temp = arr[index];
        arr[index] = arr[parentIndex];
        arr[parentIndex] = temp;
        siftUp(parentIndex);
    }
}

 

//this should check and alter the tree after an item is deleted.

//3 points

// Pre: index is an integer
// Post: sifts down the element at the given index to maintain heap properties
private void siftDown(int index) {
    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = 2 * index + 2;
    int largestIndex = index;

    if (leftChildIndex < arr.length && arr[leftChildIndex] > arr[largestIndex]) {
        largestIndex = leftChildIndex;
    }
    if (rightChildIndex < arr.length && arr[rightChildIndex] > arr[largestIndex]) {
        largestIndex = rightChildIndex;
    }
    if (largestIndex != index) {
        int temp = arr[index];
        arr[index] = arr[largestIndex];
        arr[largestIndex] = temp;
        siftDown(largestIndex);
    }
}

 

//4 points for syntax conventions.

// Pre: none
// Post: returns a string representation of the heap
public String toString() {
    String ab = "";
    
    for (int i = 0; i < arr.length; i++) {
        ab += arr[i] + " ";
    }
    return ab;
 

}

public int getTop() {
    if (arr.length == 0) {
        return -1;
    }

    return arr[0];
}
}