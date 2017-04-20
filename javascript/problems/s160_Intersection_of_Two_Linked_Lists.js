// Write a program to find the node at which the intersection of two singly linked lists begins.
// For example, the following two linked lists:
//
//     A:          a1 → a2
//                         ↘
//                           c1 → c2 → c3
//                         ↗
//     B:     b1 → b2 → b3
// begin to intersect at node c1.
//
// Notes:
//
// If the two linked lists have no intersection at all, return null.
// The linked lists must retain their original structure after the function returns.
// You may assume there are no cycles anywhere in the entire linked structure.
// Your code should preferably run in O(n) time and use only O(1) memory.

/**
 * Definition for singly-linked list.
 */
function ListNode(val) {
    this.val = val;
    this.next = null;
}

/**
 * @param {ListNode} headA
 * @param {ListNode} headB
 * @return {ListNode}
 */
var getIntersectionNode = function (headA, headB) {
    // 遍历A
    var countA = 0;
    var pointerA = headA;
    while (pointerA != null) {
        pointerA = pointerA.next;
        countA++;
    }
    // 遍历B
    var countB = 0;
    var pointerB = headB;
    while (pointerB != null) {
        pointerB = pointerB.next;
        countB++;
    }
    pointerA = headA;
    pointerB = headB;
    if (countA > countB) {
        n = 0;
        while (pointerA != null && n < countA - countB) {
            pointerA = pointerA.next;
            n++;
        }
    } else {
        pointerB = headB;
        n = 0;
        while (pointerB != null && n < countB - countA) {
            pointerB = pointerB.next;
            n++;
        }
    }

    while (pointerA != null && pointerB != null) {
        if (pointerA == pointerB) {
            return pointerA;
        }
        pointerA = pointerA.next;
        pointerB = pointerB.next;
    }
    return null
};


// Test case
var a1 = new ListNode(1)
var c1 = new ListNode(2)
var c2 = new ListNode(3)
var b1 = new ListNode(4)
var b2 = new ListNode(5)

c1.next = c2

a1.next = c1

b1.next = b2
b2.next = c1

console.log(getIntersectionNode(a1, b1))