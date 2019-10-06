package data.structure;

import java.util.*;

public class MultiNodeTree {

    /**
     * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/submissions/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelNodes = new ArrayList<>(queue.size());
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                levelNodes.add(n.val);
                queue.addAll(n.children);
            }
            result.add(levelNodes);
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            Node n = stack.pop();
            list.add(n.val);
            if (n.children != null) {
                for (int i = n.children.size() - 1; i >= 0; i--) {
                    stack.push(n.children.get(i));
                }
            }
        }
        return list;
    }

    /**
     * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            Node n = stack.pop();
            list.add(0, n.val);
            for (int i = 0; n.children != null && i < n.children.size(); i++) {
                stack.push(n.children.get(i));
            }
        }
        return list;
    }


}
