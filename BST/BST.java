package BST;

// By: Raley Wilkin
// Date: 12-12-2025
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

    // Pre Condition: None
    // Post Condition: Rotates the tree such that the subRoot is replaced with it's right 
    // child with subRoot becoming the left child of the new subRoot. prev now points to the new subRoot.
    public boolean rotateLeft(int node) {
        Node target = findNode(this.root, node);
        if (target == null) return false;
        Node parent = findParent(this.root, node);
        rotateLeft(target, parent);
        return true;
    }

    // Pre Condition: None
    // Post Condition: finds the node with the given key
    private Node findNode(Node root, int key) {
        if (root == null) return null;
        if (key == root.key) return root;
        if (key < root.key) return findNode(root.left, key);
        return findNode(root.right, key);
    }

    // Pre Condition: None
    // Post Condition: finds the parent of the node with the given key
    private Node findParent(Node root, int key) {
        if (root == null) return null;
        if ((root.left != null && root.left.key == key) || 
            (root.right != null && root.right.key == key)) {
            return root;
        }
        if (key < root.key) return findParent(root.left, key);
        return findParent(root.right, key);
    }

    // Pre Condition: None
    // Post Condition: Rotates the tree such that the subRoot is replaced with it's left 
    // child with subRoot becoming the right child of the new subRoot. prev now points to the new subRoot.
    public boolean rotateRight(int node) {
        Node target = findNode(this.root, node);
        if (target == null) return false;
        Node parent = findParent(this.root, node);
        rotateRight(target, parent);
        return true;
    }

    // Pre Condition: None
    // Post Condition: Rotates the tree such that the subRoot is replaced with it's right 
    // child with subRoot becoming the left child of the new subRoot. prev now points to the new subRoot.

    private void rotateLeft(Node subRoot, Node prev) {
        if (subRoot == null || subRoot.right == null) return;

        Node newRoot = subRoot.right;
        // Move newRoot's left subtree to subRoot's right
        subRoot.right = newRoot.left;
        // Make subRoot the left child of newRoot
        newRoot.left = subRoot;

        // Attach newRoot to parent (prev) or update tree root
        if (prev == null) {
            this.root = newRoot;
        } else if (prev.left == subRoot) {
            prev.left = newRoot;
        } else if (prev.right == subRoot) {
            prev.right = newRoot;
        }
    }

    

    // Pre Condition: None
    // Post Condition: Rotates the tree such that the subRoot is replaced with it's left 
    // child with subRoot becoming the right child of the new subRoot. prev now points to the new subRoot.
    private void rotateRight(Node subRoot, Node prev){
        if (subRoot == null || subRoot.left == null) return;

        Node newRoot = subRoot.left;
        // Move newRoot's right subtree to subRoot's left
        subRoot.left = newRoot.right;
        // Make subRoot the right child of newRoot
        newRoot.right = subRoot;

        // Attach newRoot to parent (prev) or update tree root
        if (prev == null) {
            this.root = newRoot;
        } else if (prev.left == subRoot) {
            prev.left = newRoot;
        } else if (prev.right == subRoot) {
            prev.right = newRoot;
        }
    }

    // Pre Condition: None
    // Post Condition: Returns the height of the node. If node doesn't exist, return 0.
    private int height(Node node){
        if(node == null){
            return 0;
        }
        return getDepth(this.root, node, 0);
    }

    private int getDepth(Node current, Node target, int depth){
        if(current == null) return 0;
        if(current == target) return depth;
        int leftDepth = getDepth(current.left, target, depth + 1);
        if(leftDepth != 0) return leftDepth;
        return getDepth(current.right, target, depth + 1);
    }

    // Pre Condition: None
    // Post Condition: Returns the balance of the node. If node doesn't exist, return 0.
    private int balance(Node node){
        if(node == null){
            return 0;
        }
        
        Node left = node.left;
        Node right = node.right;

        while (left != null && left.left != null){
            left = left.left;
        }
        while (right != null && right.right != null){
            right = right.right;
        }

        System.out.println(((left == null) ? "DNE" : left.key) + " - " + ((right == null) ? "DNE" : right.key) + " --- " + getDepth(node, left, 0) + " - " + getDepth(node, right, 0));

        return getDepth(node, left, 0) - getDepth(node, right, 0);
    }

    // Pre Condition: None
    // Post Condition: Returns the height of the node. If node doesn't exist, return -1.
    public int getHeight(int key) {
        Node target = findNode(this.root, key);
        if(target == null) return 0;
        return height(target);
    }

    // Pre Condition: None
    // Post Condition: Returns the balance of the node. If node doesn't exist, return -1.
    public int getBalance(int key){
        Node target = findNode(this.root, key);
        if(target == null) return 0;
        return balance(target);
    }

}