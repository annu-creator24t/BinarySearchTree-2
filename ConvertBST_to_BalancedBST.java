import java.util.*;

public class ConvertBST_to_BalancedBST {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    static void inorderTraversal(Node root, List<Integer> inorder) {
        if (root == null) return;
        inorderTraversal(root.left, inorder);
        inorder.add(root.data);
        inorderTraversal(root.right, inorder);
    }

    static Node buildBalancedBST(List<Integer> inorder, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        Node root = new Node(inorder.get(mid));

        root.left = buildBalancedBST(inorder, start, mid - 1);
        root.right = buildBalancedBST(inorder, mid + 1, end);

        return root;
    }

    static Node convertToBalancedBST(Node root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        return buildBalancedBST(inorder, 0, inorder.size() - 1);
    }

    // Print preorder to check the balanced tree structure
    static void preorderPrint(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorderPrint(root.left);
        preorderPrint(root.right);
    }

    public static void main(String[] args) {
        /*
               Unbalanced BST
                    10
                      \
                       20
                         \
                          30
        */

        Node root = new Node(10);
        root.right = new Node(20);
        root.right.right = new Node(30);

        Node balancedRoot = convertToBalancedBST(root);

        System.out.println("Preorder of Balanced BST:");
        preorderPrint(balancedRoot);
    }
}
