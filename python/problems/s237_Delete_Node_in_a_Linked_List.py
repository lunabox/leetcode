
class Solution:
    # @param {ListNode} node
    # @return {void} Do not return anything, modify node in-place instead.
    def deleteNode(self, node):
        if node is None:
            return
        node.val = node.next.val
        node.next = node.next.next