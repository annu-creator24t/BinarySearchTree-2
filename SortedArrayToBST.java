class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class SortedArrayToBST {

    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBST(int[] nums, int start, int end) {
        if (start > end) return null;

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = buildBST(nums, start, mid - 1);
        root.right = buildBST(nums, mid + 1, end);

        return root;
    }

    // ✅ Preorder Traversal (Root -> Left -> Right)
    public static void preorderTraversal(TreeNode root) {
        if (root == null) return;

        System.out.print(root.val + " ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    public static void main(String[] args) {
        int[] sortedArray = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(sortedArray);

        System.out.print("Preorder Traversal of Balanced BST: ");
        preorderTraversal(root);
    }
}
