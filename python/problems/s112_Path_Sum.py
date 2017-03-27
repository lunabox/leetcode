
class Solution(object):
    def hasPathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: bool
        """
        def search(root, s):
            if root is None:
                return False
            if root.left is None and root.right is None:
                return s == sum
            left_s = s
            if root.left is not None:
                left_s += root.left.val
            right_s = s
            if root.right is not None:
                right_s += root.right.val
            return search(root.left, left_s) or search(root.right, right_s)
        if root is None:
            return False
        return search(root, root.val)