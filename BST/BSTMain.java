package BST;

// By: Raley Wilkin
// Date: 2024-05-15
// Description: Main class to test BST

public class BSTMain {

    // Pre Condition: None
    // Post Condition: tests the BST class
    public static void main(String[] args) {
        BST tree = new BST();

        // Insert nodes
        tree.insert(50);
        System.out.println(tree.toString()); // Should print 50
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        System.out.println(tree.toString()); // Should print 50, 30, 70, 20, 40, --, --
        tree.insert(60);
        tree.insert(80);
        tree.insert(80); // Duplicate, should do nothing
        System.out.println(tree.toString()); // Should print 50, 30, 70, 20, 40, 60, 80 - no duplicates

        // Search for nodes
        System.out.println("Search 40: " + tree.search(40)); // Should return true
        System.out.println("Search 90: " + tree.search(90)); // Should return false

        // Remove nodes
        System.out.println("Remove 20: " + tree.remove(20)); // Should return 20 //Leaf version
        System.out.println("Remove 50: " + tree.remove(50)); // Should return 50 //Node with 2 children (root) - 30, 70
        System.out.println(tree.toString()); // Should print 60, 30, 70, --, 40, --, 80
        tree.insert(20);
        tree.insert(35); 
        System.out.println("Current root: " + tree.getRoot().toInt()); // Should print 60 // New root after previous removals - 30, 70
        System.out.println(tree.toString()); // Should print 60, 30, 70, 20, 40, --, 80, --, --, 35, --, --, --, --, --
        System.out.println("Remove 30: " + tree.remove(30)); // Should return 30 //Node with 2 children - 20, 40
        System.out.println(tree.toString()); // Should print Should print 60, 35, 80, 20, 40, --, 80
        System.out.println("Remove 70: " + tree.remove(70)); // Should return 70 // Node with 1 child - 80
        System.out.println(tree.toString()); // Should print 60, 35, 80, 20, 40, --, --
        System.out.println("Remove 90: " + tree.remove(90)); // Should return -1
        System.out.println(tree.toString()); // Should print 60, 35, 80, 20, 40, --, --
        tree.insert(70);
        tree.insert(100);
        System.out.println(tree.toString()); // Should print perfect tree
        System.out.println("Current root: " + tree.getRoot().toInt()); // Should still print 60
    }
}

