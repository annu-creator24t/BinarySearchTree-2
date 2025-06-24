public class SizeOfLargestBSTinBT {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    static class Info {
        boolean isBST;
        int size;
        int min;
        int max;

        Info(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    static int maxBSTSize = 0;

    public static Info largestBST(Node root) {
        if (root == null) return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        Info left = largestBST(root.left);
        Info right = largestBST(root.right);

        if (left.isBST && right.isBST && root.data > left.max && root.data < right.min) {
            int size = left.size + right.size + 1;
            maxBSTSize = Math.max(maxBSTSize, size);

            int min = Math.min(root.data, left.min);
            int max = Math.max(root.data, right.max);

            return new Info(true, size, min, max);
        }

        return new Info(false, 0, 0, 0); 
    }

    public static int findLargestBST(Node root) {
        maxBSTSize = 0;
        largestBST(root);
        return maxBSTSize;
    }

    public static void main(String[] args) {
        /*
                50
               /  \
             30    60
            / \    / \
          5  20  45  70
                       / \
                     65  80
        The largest BST is:
               60
              /  \
            45    70
                 / \
               65  80
        Size = 5
        */
        Node root = new Node(50);
        root.left = new Node(30);
        root.right = new Node(60);
        root.left.left = new Node(5);
        root.left.right = new Node(20);
        root.right.left = new Node(45);
        root.right.right = new Node(70);
        root.right.right.left = new Node(65);
        root.right.right.right = new Node(80);

        System.out.println("Size of the Largest BST in the Binary Tree: " + findLargestBST(root));
    }
}
