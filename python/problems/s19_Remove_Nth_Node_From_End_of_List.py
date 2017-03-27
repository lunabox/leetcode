
class Solution:
    # @return a ListNode
    def removeNthFromEnd(self, head, n):
        count = 0
        cur = head
        while cur != None:
            count += 1
            cur = cur.next
        removePos = count - n
        cur = head
        if removePos == 0:
            return head.next
        count = 0
        while cur != None:
            count += 1
            if count == removePos:
                nextNode = cur.next
                cur.next = nextNode.next
                nextNode.next = None
                break
            cur = cur.next
        return head