import java.util.*;

public class Merge2BST {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    static void inorder(Node root, int[] arr, int[] index) {
        if (root == null) return;
        inorder(root.left, arr, index);
        arr[index[0]++] = root.data;
        inorder(root.right, arr, index);
    }


    static int[] mergeArrays(int[] a, int[] b, int size1, int size2) {
        int[] merged = new int[size1 + size2];
        int i = 0, j = 0, k = 0;

        while (i < size1 && j < size2) {
            if (a[i] < b[j]) merged[k++] = a[i++];
            else merged[k++] = b[j++];
        }

        while (i < size1) merged[k++] = a[i++];
        while (j < size2) merged[k++] = b[j++];

        return merged;
    }

    static Node sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        Node root = new Node(arr[mid]);

        root.left = sortedArrayToBST(arr, start, mid - 1);
        root.right = sortedArrayToBST(arr, mid + 1, end);

        return root;
    }

 
    static int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    static Node mergeBSTs(Node root1, Node root2) {
        int n1 = countNodes(root1);
        int n2 = countNodes(root2);

        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        inorder(root1, arr1, new int[]{0});
        inorder(root2, arr2, new int[]{0});

        int[] merged = mergeArrays(arr1, arr2, n1, n2);

        return sortedArrayToBST(merged, 0, merged.length - 1);
    }

    static void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args) {
        Node root1 = new Node(3);
        root1.left = new Node(1);
        root1.right = new Node(5);

        Node root2 = new Node(8);
        root2.left = new Node(6);
        root2.right = new Node(10);

        Node merged = mergeBSTs(root1, root2);

        System.out.println("Preorder of Merged Balanced BST:");
        preorder(merged);
    }
}
