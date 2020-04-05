package data.structure

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    /**
     * 打印链表
     */
    fun printList() {
        var cur: ListNode? = this
        while (cur != null) {
            print("${cur.`val`} ")
            cur = cur.next
        }
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
