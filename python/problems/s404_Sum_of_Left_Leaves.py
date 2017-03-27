
class Solution(object):
    def sumOfLeftLeaves(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        def search(root, pos):
            if root is None:
                return 0
            if root.left is None and root.right is None:
                if pos == 1:
                    return root.val
                elif pos == 3:
                    return 0
            return search(root.left, 1) + search(root.right, 3)
        return search(root, 2)