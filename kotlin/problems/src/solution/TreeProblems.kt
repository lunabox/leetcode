package solution

import data.structure.TreeNode
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

class TreeProblems {
    /**
     * 由数组创建二叉树
     */
    fun createTree(element: List<Int?>): TreeNode? {
        if (element.isEmpty()) {
            return null
        }
        return createNode(element, 0)
    }

    private fun createNode(element: List<Int?>, index: Int): TreeNode? {
        if (index >= 0 && index < element.size && element[index] != null) {
            val root = TreeNode(element[index]!!)
            root.left = createNode(element, index * 2 + 1)
            root.right = createNode(element, index * 2 + 2)
            return root
        }
        return null
    }

    fun printTree(root: TreeNode?) {
        val queue = ArrayBlockingQueue<TreeNode?>(20)
        queue.add(root)

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            if (node != null) {
                println("${node.`val`} ")
                if (node.left != null) {
                    queue.add(node.left)
                }
                if (node.right != null) {
                    queue.add(node.right)
                }
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     */
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        if (root == null) {
            return result
        }
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val list = ArrayList<Int>()
            repeat(queue.size) {
                val s = queue.removeFirst()
                list.add(s.`val`)
                // add child tree node
                if (s.left != null) {
                    queue.addLast(s.left)
                }
                if (s.right != null) {
                    queue.addLast(s.right)
                }
            }
            result.add(0, list)
        }
        return result
    }

    /**
     *
     */
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        val result = ArrayList<Double>()
        if (root == null) {
            return result.toDoubleArray()
        }
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            var sum: Long = 0
            val count = queue.size
            repeat(count) {
                val node = queue.removeFirst()
                sum += node.`val`
                if (node.left != null) {
                    queue.addLast(node.left)
                }
                if (node.right != null) {
                    queue.addLast(node.right)
                }
            }
            result.add(sum / count.toDouble())
        }
        return result.toDoubleArray()
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）
     */
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        if (root == null) {
            return result
        }
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val list = ArrayList<Int>()
            repeat(queue.size) {
                val s = queue.removeFirst()
                list.add(s.`val`)
                // add child tree node
                if (s.left != null) {
                    queue.addLast(s.left)
                }
                if (s.right != null) {
                    queue.addLast(s.right)
                }
            }
            result.add(list)
        }
        return result
    }

    private var xpar = 0
    private var ypar = 0
    private var xdep = 0
    private var ydep = 0
    /**
     * https://leetcode-cn.com/problems/cousins-in-binary-tree/
     */
    fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
        if (root == null) {
            return false
        }
        dfs(root.left, 1, x, y, root.`val`)
        dfs(root.right, 1, x, y, root.`val`)
        return xdep == ydep && xpar != ypar
    }

    private fun dfs(node: TreeNode?, dep: Int, x: Int, y: Int, par: Int) {
        if (node == null) {
            return
        }
        if (node.`val` == x) {
            xdep = dep
            xpar = par
        }
        if (node.`val` == y) {
            ydep = dep
            ypar = par
        }
        dfs(node.left, dep + 1, x, y, node.`val`)
        dfs(node.right, dep + 1, x, y, node.`val`)
    }

    /**
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
     */
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return null
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     * 给定一个二叉树，返回它的 前序 遍历，使用迭代法
     */
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val list = ArrayList<Int>()
        if (root == null) {
            return list
        }
        val stack = LinkedList<TreeNode>()
        stack.addFirst(root)
        while (stack.isNotEmpty()) {
            val node = stack.pollFirst()
            list.add(node.`val`)
            if (node.right != null) {
                stack.addFirst(node.right)
            }
            if (node.left != null) {
                stack.addFirst(node.left)
            }
        }
        return list
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * 迭代法，中序遍历
     */
    fun inorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) {
            return emptyList()
        }
        val list = ArrayList<Int>()
        val stack = LinkedList<TreeNode>()
        var p: TreeNode? = root
        while (p != null || stack.isNotEmpty()) {
            while (p != null) {
                stack.addFirst(p)
                p = p.left
            }
            val n = stack.pollFirst()
            list.add(n.`val`)
            p = n.right
        }
        return list
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
     * 迭代法的后续遍历
     */
    fun postorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) {
            return emptyList()
        }
        val result = ArrayList<Int>()
        val stack = LinkedList<TreeNode>()
        stack.addFirst(root)
        while (stack.isNotEmpty()) {
            val node = stack.pollFirst()
            result.add(0, node.`val`)
            node.left?.let {
                stack.addFirst(it)
            }
            node.right?.let {
                stack.addFirst(it)
            }
        }
        return result
    }

    /**
     * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
     */
    fun minDiffInBST(root: TreeNode?): Int {
        var last = Int.MAX_VALUE
        var minValue = Int.MAX_VALUE
        val stack = LinkedList<TreeNode>()
        var p: TreeNode? = root
        while (p != null || stack.isNotEmpty()) {
            while (p != null) {
                stack.addFirst(p)
                p = p.left
            }
            val n = stack.pollFirst()
            if (last != Int.MAX_VALUE) {
                minValue = min(minValue, n.`val` - last)
            }
            last = n.`val`
            p = n.right
        }
        return minValue
    }

    var preNode: TreeNode? = null
    var minValue = Int.MAX_VALUE
    /**
     * 递归实现
     */
    fun minDiffInBSTStack(root: TreeNode?): Int {
        dfs(root)
        return minValue
    }

    private fun dfs(root: TreeNode?) {
        root?.let {
            dfs(it.left)
            if (preNode != null) {
                minValue = min(minValue, it.`val` - preNode!!.`val`)
            }
            preNode = root
            dfs(it.right)
        }
    }

    var last = Long.MIN_VALUE

    /**
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     */
    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) {
            return true
        }
        if (isValidBST(root.left)) {
            if (root.`val` > last) {
                last = root.`val`.toLong()
                return isValidBST(root.right)
            }
        }
        return false
    }

    /**
     * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）
     */
    fun findMode(root: TreeNode?): IntArray {
        val map = HashMap<Int, Int>()
        val result = ArrayList<Int>()
        val stack = LinkedList<TreeNode>()
        var p = root
        while (p != null || stack.isNotEmpty()) {
            while (p != null) {
                stack.addFirst(p)
                p = p.left
            }
            val node = stack.pollFirst()
            map[node.`val`] = if (node.`val` in map.keys) map[node.`val`]!! + 1 else 1
            p = node.right
        }
        val maxCount = map.values.max()
        map.forEach { (t, u) ->
            if (u == maxCount) {
                result.add(t)
            }
        }
        return result.toIntArray()
    }

    /**
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
     */
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        val stack = LinkedList<TreeNode>()
        var p = root
        var count = 0
        while (p != null || stack.isNotEmpty()) {
            while (p != null) {
                stack.addFirst(p)
                p = p.left
            }
            val node = stack.pollFirst()
            if (++count == k) {
                return node.`val`
            }
            p = node.right
        }
        return 0
    }

    /**
     * https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
     */
    fun findSecondMinimumValue(root: TreeNode?): Int {
        return search(root, root!!.`val`)
    }

    private fun search(root: TreeNode?, value: Int): Int {
        if (root == null) {
            return -1
        }
        if (root.`val` > value) {
            return root.`val`
        }

        val left = search(root.left, value)
        val right = search(root.right, value)
        if (left > value && right > value) {
            return min(left, right)
        }
        return max(left, right)
    }
}