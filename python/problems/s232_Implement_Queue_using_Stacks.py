class MyQueue(object):
    queue = []
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.queue = []

    def push(self, x):
        """
        Push element x to the back of queue.
        :type x: int
        :rtype: void
        """
        self.queue.insert(0, x)
        

    def pop(self):
        """
        Removes the element from in front of queue and returns that element.
        :rtype: int
        """
        if len(self.queue) > 0:
            return self.queue.pop(len(self.queue) - 1)
        return None
    
    def peek(self):
        """
        Get the front element.
        :rtype: int
        """
        if len(self.queue) > 0:
            return self.queue[len(self.queue) - 1]
        return None
    
    def empty(self):
        """
        Returns whether the queue is empty.
        :rtype: bool
        """
        return len(self.queue) == 0

# Your MyQueue object will be instantiated and called as such:
obj = MyQueue()
obj.push(1)

print obj.pop()
# print obj.peek()
print obj.empty()

