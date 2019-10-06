package data.structure

import TreeNode
import java.util.*
import kotlin.collections.ArrayList

/**
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数
 */
class BSTIterator(root: TreeNode?) {
    private val nodeList = ArrayList<Int>()
    private var index = 0

    init {
        var p = root
        val stack = LinkedList<TreeNode>()
        while (p != null || stack.isNotEmpty()) {
            while (p != null) {
                stack.addFirst(p)
                p = p.left
            }
            val node = stack.pollFirst()
            nodeList.add(node.`val`)
            p = node.right
        }
    }

    /** @return the next smallest number */
    fun next(): Int {
        return nodeList[index++]
    }

    /** @return whether we have a next smallest number */
    fun hasNext(): Boolean {
        return index < nodeList.size
    }
}