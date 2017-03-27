#coding:utf8


class Solution(object):
    def getMinimumDifference(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        que = []
        def midSearch(root):
            if root is None:
                return
            midSearch(root.left)
            que.append(root.val)
            midSearch(root.right)
        midSearch(root)
        print que
        minDis = 0xFFFFFFFF
        for i in range(len(que) - 1):
            if abs(que[i] - que[i + 1]) < minDis:
                minDis = abs(que[i] - que[i + 1])
        return minDis