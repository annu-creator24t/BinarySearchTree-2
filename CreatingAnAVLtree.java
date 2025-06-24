public class CreatingAnAVLtree {

    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    static int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) {
            System.out.println("Left Left Case - Right rotate subtree rooted with " + node.key);
            return rightRotate(node);
        }

        if (balance < -1 && key > node.right.key) {
            System.out.println("Right Right Case - Left rotate subtree rooted with " + node.key);
            return leftRotate(node);
        }

        if (balance > 1 && key > node.left.key) {
            System.out.println("Left Right Case - Left rotate subtree rooted with " + node.left.key +
                               " then right rotate subtree rooted with " + node.key);
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            System.out.println("Right Left Case - Right rotate subtree rooted with " + node.right.key +
                               " then left rotate subtree rooted with " + node.key);
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    static void preorderWithBalance(Node root) {
        if (root != null) {
            int balance = getBalance(root);
            System.out.println("Node: " + root.key + ", Balance Factor: " + balance);
            preorderWithBalance(root.left);
            preorderWithBalance(root.right);
        }
    }

    public static void main(String[] args) {
        Node root = null;
        int[] keys = {20, 10, 30, 5, 15, 25, 40, 45, 35};

        for (int key : keys) {
            root = insert(root, key);
        }

        System.out.println("\nPreorder traversal with balance factors:");
        preorderWithBalance(root);
    }
}
