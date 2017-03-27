#coding:utf8
'''
Created on 2017年3月27日

@author: wanlipeng
'''
from problems.listnode import ListNode


class Solution(object):
    def reverseList(self, head):
        if head is None:
            return None
        newHead = ListNode(0)
        newHead.next = head
        curNode = head.next
        head.next = None
        while curNode is not None:
            temp = curNode.next
            curNode.next = newHead.next
            newHead.next = curNode
            curNode = temp
        return newHead.next
    
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if head is None or head.next is None:
            return True
        slow = head
        fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        if fast: #如果fast后面还有，则说明是奇数个节点
            slow.next = self.reverseList(slow.next)
            slow = slow.next
        else: #偶数个
            slow = self.reverseList(slow)
        while slow:
            if head.val != slow.val:
                return False
            slow = slow.next
            head = head.next
        return True
            
            