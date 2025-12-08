package BST;

// By: Raley Wilkin
// Date: 2024-05-15
// Description: Binary Search Tree (BST) class

public class BST {
    Node root;

    // Constructor
    public BST()
    {
         root = null;
    }

    // Pre Condition: root is the root of the subtree
    // Post Condition: inserts a key into the BST
    private void insert(int key, Node root){
        // If tree is empty, set the root
        if (this.root == null) {
            this.root = new Node(key);
            return;
        }
        // Insert into correct subtree, creating child nodes when needed
        if (key < root.key) {
            if (root.left == null) {
                root.left = new Node(key);
            } else {
                insert(key, root.left);
            }
        } else if (key > root.key) {
            if (root.right == null) {
                root.right = new Node(key);
            } else {
                insert(key, root.right);
            }
        }
        // duplicates: do nothing
    }

    // Pre Condition: None
    // Post Condition: inserts a key into the BST
    public void insert(int key) {
        insert(key, this.root);
    }

    // Pre Condition: None
    // Post Condition: returns the key if found, or -1 if not found
    private boolean search(int key, Node root){
        if(root == null)
        return false;
        if(key < root.key)
        return search(key, root.left);
        if(key > root.key)
        return search(key, root.right);
        return true;
    }

    // Pre Condition: None
    // Post Condition: returns the key if found, or -1 if not found
    boolean search(int key) {
        return search(key, root);
    }

    // Pre Condition: None
    // Post Condition: removes a node with the given key and returns the key, or -1 if not found
    int remove(int key) {
        return remove(key, root);
    }

    // Pre Condition: None
    // Post Condition: removes a node with the given key and returns the key, or -1 if not found
    private int remove(int key, Node root){
        // Ensure the key exists in the tree first
        if (search(key, this.root) == false) {
            return -1;
        }
        // Remove the key from the tree and update the root reference
        this.root = removeNode(this.root, key);
        return key;
    }

    // Pre Condition: node is the root of the subtree, key is the key to remove
    // Post Condition: recursively remove a node and return the (possibly new) subtree root
    private Node removeNode(Node node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = removeNode(node.left, key);
        } else if (key > node.key) {
            node.right = removeNode(node.right, key);
        } else {
            // node to be deleted
            if (node.left == null && node.right == null) {
                return null; // leaf
            } else if (node.left == null) {
                return node.right; // one child (right)
            } else if (node.right == null) {
                return node.left; // one child (left)
            } else {
                // two children: find inorder successor (smallest in right subtree)
                Node succ = minValueNode(node.right);
                node.key = succ.key;
                node.right = removeNode(node.right, succ.key);
            }
        }
        return node;
    }

    // Pre Condition: node is the root of the subtree
    // Post Condition: find the node with minimum key in a subtree
    private Node minValueNode(Node node) {
        Node current = node;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Pre Condition: None
    // Post Condition: returns a string representation of the BST in level-order, centered
    public String toString(){
        if (root == null) return "";
        
        // Collect all levels with proper null tracking
        java.util.List<java.util.List<Node>> levels = new java.util.ArrayList<>();
        java.util.List<Node> currentLevel = new java.util.ArrayList<>();
        currentLevel.add(root);
        levels.add(new java.util.ArrayList<>(currentLevel));
        
        while (true) {
            java.util.List<Node> nextLevel = new java.util.ArrayList<>();
            boolean hasNonNull = false;
            
            for (Node node : currentLevel) {
                Node left = (node != null) ? node.left : null;
                Node right = (node != null) ? node.right : null;
                
                nextLevel.add(left);
                nextLevel.add(right);
                
                if (left != null || right != null) {
                    hasNonNull = true;
                }
            }
            
            // Stop if next level has no non-null nodes
            if (!hasNonNull) break;
            
            levels.add(nextLevel);
            currentLevel = nextLevel;
        }
        
        // Convert to strings and center
        java.util.List<StringBuilder> levelStrs = new java.util.ArrayList<>();
        for (java.util.List<Node> level : levels) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level.size(); i++) {
                if (i > 0) sb.append(", ");
                Node node = level.get(i);
                sb.append(node == null ? "--" : node.key);
            }
            levelStrs.add(sb);
        }
        
        // Find max width
        int maxWidth = levelStrs.get(levelStrs.size() - 1).length();
        
        // Build centered output
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < levelStrs.size(); i++) {
            String levelStr = levelStrs.get(i).toString();
            int padding = (maxWidth - levelStr.length()) / 2;
            
            if (i > 0) result.append("\n");
            for (int p = 0; p < padding; p++) {
                result.append(" ");
            }
            result.append(levelStr);
        }
        
        return result.toString();
    }


    // Pre Condition: None
    // Post Condition: returns the root node
    public Node getRoot() {
        return this.root;
    }

}