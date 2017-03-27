
class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        result = list()
        def search(r, line):
            if r is None:
                return None
            if r.left is None and r.right is None:
                result.append(line)
            if r.left is not None:
                rl = r.left
                search(rl, line + '->' + str(rl.val))
                
            if r.right is not None:
                rr = r.right
                search(rr, line + '->' + str(rr.val))
                
        if root is not None:
            search(root, str(root.val))
        return result