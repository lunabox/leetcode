# coding:utf8
from problems.s110_Balanced_Binary_Tree import Solution
from problems.treenode import TreeNode


if __name__ == '__main__':
    s = Solution()
    
    t1 = TreeNode(1)
#     t1.left = TreeNode(9)
#     t1.left.left = TreeNode(15)
    t1.right = TreeNode(2)
    t1.right.right = TreeNode(3)
    
    print s.isBalanced(t1)
