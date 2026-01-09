package BST;

import java.util.ArrayList;

// By: Raley Wilkin
// Date: 1-9-2026
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
    private void insert(int key, Node root, ArrayList<Node> nodes){
        // If tree is empty, set the root
        if (this.root == null) {
            this.root = new Node(key);
            return;
        }
        // Insert into correct subtree, creating child nodes when needed
        if (key < root.key) {
            if (root.left == null) {
                root.left = new Node(key);
                nodes.add(root.left);
                rebalance(nodes);
            } else {
                nodes.add(root.left);
                insert(key, root.left, nodes);
            }
        } else if (key > root.key) {
            if (root.right == null) {
                root.right = new Node(key);
                nodes.add(root.right);
                rebalance(nodes);
            } else {
                nodes.add(root.right);
                insert(key, root.right, nodes);
            }
        }
        // duplicates: do nothing
    }

    // Pre Condition: None
    // Post Condition: inserts a key into the BST
    public void insert(int key) {
        insert(key, this.root, new java.util.ArrayList<>());
    }

    private void rebalance(ArrayList<Node> nodes) {
        // Keep rebalancing until the entire path and tree is balanced
        boolean changed = true;
        while (changed) {
            changed = false;
            // Check nodes in the insertion path
            for (int i = nodes.size() - 1; i >= 0; i--) {
                Node current = findNode(this.root, nodes.get(i).key);
                if (current == null) continue;
                
                if (performBalance(current)) {
                    changed = true;
                }
            }
            // Also check the root and its ancestors
            Node current = this.root;
            while (current != null) {
                if (performBalance(current)) {
                    changed = true;
                }
                // Move up the tree by finding parent
                Node parent = (current.key == this.root.key) ? null : findParent(this.root, current.key);
                current = parent;
            }
        }
    }
    
    private boolean performBalance(Node current) {
        int bal = balance(current);
        if (bal > 1) {
            // Left heavy
            if (balance(current.left) < 0) {
                // Left-Right case
                rotateLeft(current.left, findParent(this.root, current.left.key));
            }
            // Left-Left case
            rotateRight(current, findParent(this.root, current.key));
            return true;
        } else if (bal < -1) {
            // Right heavy
            if (balance(current.right) > 0) {
                // Right-Left case
                rotateRight(current.right, findParent(this.root, current.right.key));
            }
            // Right-Right case
            rotateLeft(current, findParent(this.root, current.key));
            return true;
        }
        return false;
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
        ArrayList<Node> path = new ArrayList<>();
        buildPath(this.root, key, path);
        if (path.isEmpty()) {
            return -1;
        }
        this.root = removeNode(this.root, key);
        rebalance(path);
        return key;
    }

    // Pre Condition: None
    // Post Condition: builds the path to a node with the given key
    private boolean buildPath(Node node, int key, ArrayList<Node> path) {
        if (node == null) return false;
        
        path.add(node);
        if (node.key == key) {
            return true;
        }
        
        if (key < node.key) {
            if (buildPath(node.left, key, path)) {
                return true;
            }
        } else {
            if (buildPath(node.right, key, path)) {
                return true;
            }
        }
        
        path.remove(path.size() - 1);
        return false;
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
    // Post Condition: Returns the height of the subtree rooted at node (leaf = 0). If node is null, return -1.
    private int height(Node node){
        if(node == null){
            return -1;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // Pre Condition: None
    // Post Condition: Returns the balance of the node (height of left subtree - height of right subtree). If node doesn't exist, return 0.
    private int balance(Node node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Pre Condition: None
    // Post Condition: Returns the height of the node. If node doesn't exist, return -1.
    public int getHeight(int key) {
        Node target = findNode(this.root, key);
        if(target == null) return -1;
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