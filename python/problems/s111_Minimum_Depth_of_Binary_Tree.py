#coding:utf8
'''
Created on 2017年3月27日

@author: wanlipeng
'''
class Solution(object):
    def minDepth(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root is None:
            return 0
        if root.left is None and root.right is None: 
            return 1 #寻找叶子节点，返回高度1
        ld = self.minDepth(root.left)
        rd = self.minDepth(root.right)
        if ld > 0 and rd > 0: #两个子树都不为空，找到最小的一个子树
            return min([ld, rd]) + 1
        return max([ld, rd]) + 1 # 执行到此，有个子树为空，取最小得到9=0，不符合，所以取最大
        