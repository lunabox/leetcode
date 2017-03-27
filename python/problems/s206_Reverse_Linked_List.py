from problems.listnode import ListNode


class Solution:
    # @param {ListNode} head
    # @return {ListNode}
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