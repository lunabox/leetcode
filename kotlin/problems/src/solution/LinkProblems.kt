package solution

import data.structure.ListNode

class LinkProblems {

    /**
     * https://leetcode-cn.com/problems/merge-k-sorted-lists/
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        var currentNode = Array<ListNode?>(lists.size) { null }
        var result: ListNode? = null
        var current: ListNode? = null
        lists.forEachIndexed { index, listNode ->
            currentNode[index] = listNode
        }
        currentNode = currentNode.filterNotNull().toTypedArray()
        while (currentNode.isNotEmpty()) {
            var m = currentNode[0]
            var minIndex = 0
            currentNode.forEachIndexed { index, listNode ->
                if (listNode!!.`val` < m!!.`val`) {
                    m = listNode
                    minIndex = index
                }
            }
            if (current == null) {
                result = m
                current = result
            } else {
                current.next = m
                current = current.next
            }
            currentNode[minIndex] = currentNode[minIndex]?.next
            currentNode = currentNode.filterNotNull().toTypedArray()
            current?.next = null
        }
        return result
    }
}