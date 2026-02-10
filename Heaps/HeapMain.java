

public class HeapMain {
    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.add(5);
        heap.add(3);
        heap.add(8);
        heap.add(1);
        System.out.println(heap.toString()); // Should print the heap in array form

        Heap heap2 = new Heap(new int[]{10, 9, 8, 7, 6});
        System.out.println(heap2.toString()); // Should print the heap in array form

        heap.add(12);
        System.out.println(heap.toString()); // Should print the heap in array form
        System.out.println("Top element: " + heap.getTop());

        heap.removeMax();
        System.out.println(heap.toString()); // Should print the heap in array form
        System.out.println("Top element: " + heap.getTop());

        heap.add(9);
        System.out.println(heap.toString()); // Should print the heap in array form
        System.out.println("Top element: " + heap.getTop());
        heap.add(-10);
        heap.add(0);
        System.out.println(heap.toString());
    }
}