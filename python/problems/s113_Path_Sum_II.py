
class Solution(object):
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: List[List[int]]
        """
        result = []
        def search(r, s, line):
            if r is None:
                return None
            if r.left is None and r.right is None:
                if s == sum:
                    result.append(line)
            if r.left is not None:
                rl = r.left
                search(rl, rl.val + s, line + [rl.val])
                
            if r.right is not None:
                rr = r.right
                search(rr, rr.val + s, line + [rr.val])
                
        if root is not None:
            search(root, root.val, [root.val])
        return result
        