// Implement the following operations of a stack using queues.
//
// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// empty() -- Return whether the stack is empty.
//     Notes:
// You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
//     Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
//     You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

/**
 * Initialize your data structure here.
 */
var MyStack = function() {
    this.data = new Array();
};

/**
 * Push element x onto stack.
 * @param {number} x
 * @return {void}
 */
MyStack.prototype.push = function(x) {
    this.data.push(x)
};

/**
 * Removes the element on top of the stack and returns that element.
 * @return {number}
 */
MyStack.prototype.pop = function() {
    return this.data.pop();
};

/**
 * Get the top element.
 * @return {number}
 */
MyStack.prototype.top = function() {
    return this.data[this.data.length - 1]
};

/**
 * Returns whether the stack is empty.
 * @return {boolean}
 */
MyStack.prototype.empty = function() {
    return this.data.length == 0;
};

var obj = new MyStack()
obj.push(1)
obj.push(1454)
obj.push(111)

var param_2 = obj.pop()
var param_3 = obj.top()
var param_4 = obj.empty()

console.log(param_2)
console.log(param_3)
console.log(param_4)