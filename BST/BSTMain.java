package BST;

// By: Raley Wilkin
// Date: 12-16-2025
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
        tree.insert(190);
        System.out.println(tree.toString()); // Should print tree with 75 added
        tree.remove(80);
        System.out.println(tree.toString()); // Should print tree with 80 removed

        tree.remove(20);
        tree.remove(40);
        tree.remove(70);
        tree.remove(190);
        System.out.println(tree.toString()); // Should print tree with several nodes removed

        tree.insert(200);
        tree.insert(250);
        tree.insert(300);
        System.out.println(tree.toString()); // Should print tree with more nodes added
        System.out.println("Height of 195: " + tree.getHeight(195)); // Should print 3
        System.out.println("Balance of 195: " + tree.getBalance(195)); // Should print -3
        System.out.println("Height of 250: " + tree.getHeight(250)); // Should print 1
        System.out.println("Balance of 250: " + tree.getBalance(250)); // Should print -1
        System.out.println("Height of 60: " + tree.getHeight(60)); // Should print 5
        System.out.println("Balance of 60: " + tree.getBalance(60)); // Should print -4
        System.out.println("Height of 300: " + tree.getHeight(300)); // Should print 0
        System.out.println("Height of 20: " + tree.getHeight(20)); // Should print -1
        System.out.println(tree.toString());

        tree.insert(350);
        tree.insert(400);
        tree.insert(450);
        tree.insert(500);
        System.out.println(tree.toString()); 
        tree.insert(550);
        tree.insert(600);
        tree.insert(650);
        tree.insert(700);
        System.out.println(tree.toString()); 

        tree.insert(30);
        tree.insert(28);
        tree.insert(25);
        tree.insert(22);
        System.out.println(tree.toString()); 
        tree.insert(20);
        tree.insert(18);
        tree.insert(15);
        tree.insert(10);
        System.out.println(tree.toString()); 

        tree.insert(8);
        tree.insert(5);
        tree.insert(2);
        tree.insert(0);
        System.out.println(tree.toString()); 
    }
}

