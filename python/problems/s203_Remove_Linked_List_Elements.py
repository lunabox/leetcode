from problems.listnode import ListNode


class Solution:
    # @param {ListNode} head
    # @param {integer} val
    # @return {ListNode}
    def removeElements(self, head, val):
        cur = newHead = ListNode(0)
        newHead.next = head
        while cur is not None:
            temp = cur.next
            if temp is not None and temp.val == val:
                cur.next = temp.next
            else:
                cur = cur.next
        return newHead.next