# coding:utf8
'''
Created on 2017年3月27日

@author: wanlipeng
'''

class Solution(object):
    def isBalanced(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        def dep(r):
            if r is None:
                return 0
            ldep = dep(r.left)
            rdep = dep(r.right)
            if ldep < 0 or rdep < 0 or abs(ldep - rdep) > 1:
                return -1
            return max([ldep, rdep]) + 1
        if root is None:
            return True
        ld = dep(root.left)
        rd = dep(root.right)
        if ld < 0 or rd < 0 or abs(ld - rd) > 1:
            return False
        return True
