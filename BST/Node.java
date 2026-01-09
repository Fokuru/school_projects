package BST;

// By: Raley Wilkin
// Date: 1-9-2026
// Description: Node class for BST

public class Node{
    int key;
    Node left, right;

    // Constructor
    public Node(int item)
    {
        key = item;
        left = right = null;
    }
    
    // Pre Condition: None
    // Post Condition: returns key as String
    public String toInt(){
        return Integer.toString(key);
    }

    // Pre Condition: None
    // Post Condition: returns left child
    public Node left(){
        return left;
    }

    // Pre Condition: None
    // Post Condition: returns right child
    public Node right(){
        return right;
    }
}
