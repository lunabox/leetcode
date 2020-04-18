package solution;

import data.structure.ListNode;

public class JavaSolution {
    /**
     * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        n = n - ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n = n + (n >>> 8);
        n = n + (n >>> 16);
        return n & 0x3f;
    }

    /**
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int sizeA = listLength(headA);
        int sizeB = listLength(headB);
        ListNode curA = headA;
        ListNode curB = headB;
        if (sizeA > sizeB) {
            int step = sizeA - sizeB;
            while (step > 0) {
                curA = curA.getNext();
                step--;
            }
        } else if (sizeB > sizeA) {
            int step = sizeB - sizeA;
            while (step > 0) {
                curB = curB.getNext();
                step--;
            }
        }
        while (curA != null && curB != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.getNext();
            curB = curB.getNext();
        }
        return null;
    }

    private int listLength(ListNode head) {
        int size = 0;
        ListNode current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
}
