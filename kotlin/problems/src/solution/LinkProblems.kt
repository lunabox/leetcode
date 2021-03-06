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

    /**
     *
     */
    fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {
        val h1 = ListNode(0)
        h1.next = l1
        val h2 = ListNode(0)
        h2.next = l2

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
        return ans.next
    }

    /**
     * https://leetcode-cn.com/problems/partition-list/
     */
    fun partition(head: ListNode?, x: Int): ListNode? {
        val littleList = ListNode(0)
        val bigList = ListNode(0)
        var current = head
        var littleCurrent = littleList
        var bigCurrent = bigList
        while (current != null) {
            val temp = current
            current = current.next
            temp.next = null
            if (temp.`val` < x) {
                littleCurrent.next = temp
                littleCurrent = littleCurrent.next!!
            } else {
                bigCurrent.next = temp
                bigCurrent = bigCurrent.next!!
            }
        }
        littleCurrent.next = bigList.next
        return littleList.next
    }

    /**
     * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
     */
    fun reverseList(head: ListNode?): ListNode? {
        val ans: ListNode = ListNode(0)
        var current = head
        while (current != null) {
            val temp = current
            current = current.next
            temp.next = ans.next
            ans.next = temp
        }
        return ans.next
    }

    /**
     * https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
     */
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var cur1 = l1
        var cur2 = l2
        val ans = ListNode(0)
        var cur: ListNode = ans
        while (cur1 != null || cur2 != null) {
            if (cur2 == null || (cur1 != null && cur1.`val` <= cur2.`val`)) {
                cur.next = cur1
                cur1 = cur1!!.next
                cur = cur.next!!
                cur.next = null
            } else if (cur1 == null || cur1.`val` > cur2.`val`) {
                cur.next = cur2
                cur2 = cur2.next
                cur = cur.next!!
                cur.next = null
            }
        }
        return ans.next
    }

    /**
    <<<<<<< HEAD
     * https://leetcode-cn.com/problems/palindrome-linked-list-lcci/
     */
    fun isPalindrome(head: ListNode?): Boolean {
        var cur = head
        var listLength = 0
        while (cur != null) {
            listLength++
            cur = cur.next
        }
        cur = head
        var count = listLength / 2
        val left = ListNode(0)
        while (count > 0 && cur != null) {
            count--
            val temp = cur
            cur = cur.next
            temp.next = left.next
            left.next = temp
        }
        var curLeft = left.next
        var curRight = cur
        if (listLength % 2 == 1) {
            curRight = cur!!.next
        }
        while (curLeft != null && curRight != null) {
            if (curLeft.`val` != curRight.`val`) {
                return false
            }
            curLeft = curLeft.next
            curRight = curRight.next
        }
        return true
    }

    /**
     * https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
     */
    fun kthToLast(head: ListNode?, k: Int): Int {
        var cur = head
        var listLength = 0
        while (cur != null) {
            listLength++
            cur = cur.next
        }
        val pos = listLength - k + 1
        cur = head
        listLength = 0
        while (cur != null) {
            listLength++
            if (listLength == pos) {
                return cur.`val`
            }
            cur = cur.next
        }
        return 0
    }

    /**
     * https://leetcode-cn.com/problems/middle-of-the-linked-list/
     */
    fun middleNode(head: ListNode?): ListNode? {
        var listLength = 0
        var current = head
        while (current != null) {
            listLength++
            current = current.next
        }
        val middle = listLength / 2 + 1
        current = head
        listLength = 0
        while (current != null) {
            listLength++
            if (listLength == middle) {
                return current
            }
            current = current.next
        }
        return null
    }

    /**
     * https://leetcode-cn.com/problems/linked-list-components/
     */
    fun numComponents(head: ListNode?, G: IntArray): Int {
        var ans = 0
        var current = head
        var inList = false
        while (current != null) {
            if (current.`val` in G) {
                if (!inList) {
                    inList = true
                    ans++
                }
            } else {
                inList = false
            }
            current = current.next
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
     */
    fun removeDuplicateNodes(head: ListNode?): ListNode? {
        val buffer = HashSet<Int>()
        var current = head
        var before: ListNode? = null
        while (current != null) {
            if (current.`val` in buffer) {
                before!!.next = current.next
                current = before
            } else {
                buffer.add(current.`val`)
            }
            before = current
            current = current.next
        }
        return head
    }
}