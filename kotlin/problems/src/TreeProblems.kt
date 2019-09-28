import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import kotlin.collections.ArrayList

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


}