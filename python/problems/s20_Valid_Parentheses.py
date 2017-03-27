class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        stack = []
        for ch in s:
            size = len(stack)
            if size == 0:
                stack.append(ch)
            elif ch == ')' and stack[size - 1] == '(':
                stack.pop()
            elif ch == ']' and stack[size - 1] == '[':
                stack.pop()
            elif ch == '}' and stack[size - 1] == '{':
                stack.pop()
            else:
                stack.append(ch)
        return len(stack) == 0