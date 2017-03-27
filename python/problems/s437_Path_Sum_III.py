
class Solution(object):
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: int
        """
        if root is None:
            return 0
        def dfs(r, s):
            if r is None:
                return 0
            count = 0
            if r.val == s:
                count += 1
            count += dfs(r.left, s - r.val)
            count += dfs(r.right, s - r.val)
            return count
        return dfs(root, sum) + self.pathSum(root.left, sum) + self.pathSum(root.right, sum)