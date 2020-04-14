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

    /**
     * https://leetcode-cn.com/problems/add-two-numbers-ii/
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val h1 = ListNode(0)
        h1.next = l1
        val h2 = ListNode(0)
        h2.next = l2

        val reverseList: (ListNode) -> Unit = {
            var current = it.next
            it.next = null
            while (current != null) {
                val pointer = current
                current = current.next
                if (it.next == null) {
                    it.next = pointer
                    pointer.next = null
                } else {
                    pointer.next = it.next
                    it.next = pointer
                }
            }
        }
        reverseList(h1)
        reverseList(h2)
        var n1 = h1.next
        var n2 = h2.next
        val ans = ListNode(0)
        var cur: ListNode? = null
        var carry = 0
        while (n1 != null && n2 != null) {
            val n = n1.`val` + n2.`val` + carry
            carry = n / 10
            if (ans.next == null) {
                ans.next = ListNode(n % 10)
                cur = ans.next
            } else {
                cur!!.next = ListNode(n % 10)
                cur = cur.next
            }
            n1 = n1.next
            n2 = n2.next
        }
        while (n1 != null) {
            cur!!.next = ListNode((n1.`val` + carry) % 10)
            carry = (n1.`val` + carry) / 10
            cur = cur.next
            n1 = n1.next
        }
        while (n2 != null) {
            cur!!.next = ListNode((n2.`val` + carry) % 10)
            carry = (n2.`val` + carry) / 10
            cur = cur.next
            n2 = n2.next
        }
        if (carry > 0) {
            cur!!.next = ListNode(carry)
        }
        reverseList(ans)
        return ans.next
    }

    /**
     * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     */
    fun deleteNode(head: ListNode?, `val`: Int): ListNode? {
        val ans = ListNode(0)
        ans.next = head
        var before = ans
        var current = ans.next
        while (current != null) {
            if (current.`val` == `val`) {
                before.next = current.next
                current.next = null
                break
            }
            before = current
            current = current.next
        }
        return ans.next
    }

    /**
     * https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
     */
    fun getDecimalValue(head: ListNode?): Int {
        var len = 0
        var cur = head
        var ans = 0
        while (cur != null) {
            len++
            cur = cur.next
        }
        cur = head
        var carry = (1).shl(len - 1)
        while (cur != null) {
            ans += carry * cur.`val`
            carry = carry.shr(1)
            cur = cur.next
        }
        return ans
    }
}