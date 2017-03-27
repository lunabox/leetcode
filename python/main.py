# coding:utf8
from problems.listnode import ListNode
from problems.s234_Palindrome_Linked_List import Solution


def createLinkList(nums):
    head = ListNode(nums[0])
    pointer = head
    for i in range(1, len(nums)):
        pointer.next = ListNode(nums[i])
        pointer = pointer.next
    return head
    
if __name__ == '__main__':
    s = Solution()
    
#     t1 = TreeNode(1)
#     t1.left = TreeNode(9)
#     t1.left.left = TreeNode(15)
#     t1.right = TreeNode(2)
#     t1.right.right = TreeNode(3)
    head = createLinkList([1, 2, 3, 6, 3, 2, 1])
    print s.isPalindrome(head)
    
